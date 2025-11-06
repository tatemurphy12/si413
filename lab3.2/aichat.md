USER

How do I work with string variables in llvm

GEMINI

Working with strings in LLVM Intermediate Representation (IR) boils down to handling pointers to characters, just like in C. LLVM IR doesn't have a high-level string type, so you work with it as an array of bytes (i8) and, most commonly, a pointer to the first byte (i8*).

There are three main ways to create and manage strings, depending on your needs.

1. Global String Constants (e.g., "Hello, World!")
This is the simplest way to handle a string literal that is known at compile time. You define it as a global constant variable.

What it is: A null-terminated array of characters ([N x i8]) stored in the read-only data section of your program.

How to create it: You define a global constant. The c prefix before the string indicates it's a constant string. Don't forget the null terminator (\00).

Code snippet

; Define a global, constant string named ".str"
@.str = private unnamed_addr constant [14 x i8] c"Hello, World!\0A\00"
;                                                 (12 chars + \n + \0 = 14 bytes)
How to use it:

You can't use the [14 x i8] array directly with functions like printf. You need a pointer to its first element (i8*). You get this pointer using the getelementptr (GEP) instruction.

Code snippet

; To get an i8* pointer to the first character (index 0, 0):
%ptr_to_hello = getelementptr inbounds [14 x i8], ptr @.str, i64 0, i64 0

; %ptr_to_hello is now an i8* (which is the same as ptr)
; that you can pass to a C function like puts or printf.
What is GEP? The getelementptr instruction is LLVM's way of calculating addresses. It does not read from or write to memory.

getelementptr inbounds [14 x i8], ptr @.str, i64 0, i64 0

[14 x i8], ptr @.str: The base pointer and the type it points to.

i64 0: The first index "follows" the @.str pointer. It's almost always 0.

i64 0: The second index selects the element within the [14 x i8] array. i64 0 gives a pointer to the 0th element (the 'H').

2. Local String Variables (Stack-Allocated)
This is for fixed-size, mutable string buffers inside a function, similar to char myStr[50]; in C.

What it is: A fixed-size block of memory reserved on the function's stack frame.

How to create it: Use the alloca instruction in the function's entry block.

Code snippet

define void @my_function() {
entry:
  ; Allocate space for a 50-byte buffer on the stack.
  ; %my_buffer is a pointer to the start of that array: ptr
  %my_buffer = alloca [50 x i8], align 1
How to use it:

The alloca instruction gives you a ptr (which is an [50 x i8]*). Just like with global strings, you often need to "decay" this to an i8* pointer to the first element to use it with C functions.

Code snippet

  ; Get a pointer to the first element (index 0, 0)
  ; %ptr_to_buffer is now an i8*
  %ptr_to_buffer = getelementptr inbounds [50 x i8], ptr %my_buffer, i64 0, i64 0

  ; Now you can use %ptr_to_buffer with C functions
  ; to write data *into* this stack buffer.
  ...
  ret void
}
3. Dynamic String Variables (Heap-Allocated)
This is for strings where you don't know the size at compile time, like std::string in C++ or the result of malloc in C. LLVM IR does not manage this for you; you must manage the memory by calling C library functions like malloc, free, and realloc.

Step 1: Declare the C Library Functions
First, you must tell LLVM about the functions you want to call. You do this with the declare keyword.

Code snippet

; Declare malloc: takes an integer (size), returns a pointer (i8*)
declare ptr @malloc(i64 %size)

; Declare free: takes a pointer (i8*), returns nothing (void)
declare void @free(ptr %ptr)

; Declare strcpy: takes dest (i8*), src (i8*), returns dest (i8*)
declare ptr @strcpy(ptr %dest, ptr %src)
Step 2: Call the Functions
Now you can use the call instruction to allocate, manipulate, and free your string.

Code snippet

; (Assuming @.str from example 1 is available)

; 1. Allocate memory on the heap (e.g., 50 bytes)
;    (Note: i64 is often used for sizes)
%buffer_size = i64 50
%heap_ptr = call ptr @malloc(i64 %buffer_size)

; 2. Get the pointer to our global "Hello, World!" string
%hello_ptr = getelementptr inbounds [14 x i8], ptr @.str, i64 0, i64 0

; 3. Copy "Hello, World!" into the new heap buffer
;    call ptr @strcpy(ptr %heap_ptr, ptr %hello_ptr)

; 4. ... use %heap_ptr for other operations ...

; 5. When done, free the memory
call void @free(ptr %heap_ptr)
Summary: Calling a C Function (like printf)
Putting it all together, here is a complete "Hello, World!" example. This is the most common string operation.

Code snippet

; 1. Define the global string constant
@.str = private unnamed_addr constant [14 x i8] c"Hello, World!\0A\00"

; 2. Declare the external 'printf' function
;    (i8* is the format string, '...' means it's variadic)
declare i32 @printf(ptr %format, ...)

; 3. Define your main function
define i32 @main() {
entry:
  ; 4. Get the i8* pointer to the global string
  %ptr_to_hello = getelementptr inbounds [14 x i8], ptr @.str, i64 0, i64 0

  ; 5. Call printf with the string pointer
  ;    The '...' arguments must be explicitly typed
  call i32 (ptr, ...) @printf(ptr %ptr_to_hello)

  ret i32 0
}
The key takeaway is that a "string" in LLVM is just an i8* pointer. How you get that pointer (from a global, alloca, or malloc) is what defines the type of string you're working with.

Would you like to see how to implement a more complex string structure, like one that stores its own length and capacity?

USER

After calling the alloca function for a ptr (my buffer) can I store the result of a function in it? ex : my buffer = strstr()

GEMINI

Yes, absolutely. But you can't re-assign the %my_buffer variable itself. Remember, LLVM is in Static Single Assignment (SSA) form, meaning a variable like %my_buffer is assigned a value once and can't be changed.

Instead, you store the result of strstr into the memory location that %my_buffer points to.

Your example my_buffer = strstr() implies that my_buffer is a pointer variable (like char* my_buffer; in C). To do this in LLVM, you must alloca space for a pointer, not for the string data itself.

How to Store a Pointer (C: char* my_buffer = ...)
This is the direct answer to your example. Here, we're creating a variable on the stack that will hold the i8* pointer returned by strstr.

The C code we're matching:

C

#include <string.h>
void my_func() {

char* my_buffer; // This is our stack variable

my_buffer = strstr("hello world", "world");

}

LLVM IR Implementation:

Code snippet

; --- Setup ---
; Define the strings we'll search
@.haystack = private constant [12 x i8] c"hello world\00"
@.needle = private constant [6 x i8] c"world\00"

; Declare the external strstr function
; It takes two i8* pointers and returns an i8* pointer
declare ptr @strstr(ptr %haystack, ptr %needle)

; --- Function ---
define void @my_func() {
entry:
  ; 1. Allocate stack space FOR ONE POINTER (ptr)
  ;    This is the C line: "char* my_buffer;"
  ;    %my_buffer_addr is a 'ptr*' (a pointer to a pointer)
  %my_buffer_addr = alloca ptr, align 8

  ; 2. Get i8* pointers to our global strings
  %haystack_ptr = getelementptr inbounds [12 x i8], ptr @.haystack, i64 0, i64 0
  %needle_ptr = getelementptr inbounds [6 x i8], ptr @.needle, i64 0, i64 0

  ; 3. Call strstr
  %result_from_strstr = call ptr @strstr(ptr %haystack_ptr, ptr %needle_ptr)

  ; 4. Store the result INTO the allocated stack space
  ;    This is the C line: "my_buffer = ..."
  ;    We store the value (%result_from_strstr) at the address (%my_buffer_addr)
  store ptr %result_from_strstr, ptr %my_buffer_addr

  ret void
}
