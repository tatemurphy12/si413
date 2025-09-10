target triple = "x86_64-pc-linux-gnu"

%struct._IO_FILE = type { i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, %struct._IO_marker*, %struct._IO_FILE*, i32, i32, i64, i16, i8, [1 x i8], i8*, i64, %struct._IO_codecvt*, %struct._IO_wide_data*, %struct._IO_FILE*, i8*, i64, i32, [20 x i8] }
%struct._IO_marker = type opaque
%struct._IO_codecvt = type opaque
%struct._IO_wide_data = type opaque
@stdin = external global %struct._IO_FILE*, align 8
@.str = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
define dso_local i8* @input(i8* noundef %0, i32 noundef %1) #0 {
	%3 = alloca i8*, align 8
	%4 = alloca i8*, align 8
	%5 = alloca i32, align 4
	store i8* %0, i8** %4, align 8
	store i32 %1, i32* %5, align 4
	%6 = load i8*, i8** %4, align 8
	%7 = load i32, i32* %5, align 4
	%8 = load %struct._IO_FILE*, %struct._IO_FILE** @stdin, align 8
	%9 = call i8* @fgets(i8* noundef %6, i32 noundef %7, %struct._IO_FILE* noundef %8)
	%10 = icmp eq i8* %9, null
	br i1 %10, label %11, label %12
	11:                                               ; preds = %2
		store i8* null, i8** %3, align 8
		br label %18
	12:                                               ; preds = %2
		%13 = load i8*, i8** %4, align 8
		%14 = load i8*, i8** %4, align 8
		%15 = call i64 @strcspn(i8* noundef %14, i8* noundef getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i64 0, i64 0)) #4
		%16 = getelementptr inbounds i8, i8* %13, i64 %15
		store i8 0, i8* %16, align 1
		%17 = load i8*, i8** %4, align 8
		store i8* %17, i8** %3, align 8
		br label %18
	18:                                               ; preds = %12, %11
		%19 = load i8*, i8** %3, align 8
		ret i8* %19
}

declare i32 @puts(i32 noundef) #1

define i32 @main() {
	call i32 @puts(ptr @lit0)
	call i32 @puts(ptr @lit1)
	ret i32 0
}

@lit0= constant [6 x i8] c"aloha\00"
@lit1= constant [8 x i8] c"aloha 2\00"
