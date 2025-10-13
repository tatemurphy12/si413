#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "helpers.h"

char* input(char* buffer, int size)
{
	if (fgets(buffer, size, stdin) == NULL)
	{
		return NULL;
	}
	buffer[strcspn(buffer, "\n")] = '\0';

	return buffer;
}

char* reverse(char* str) {


	// 1. Handle edge cases: If the string is NULL or empty, there's nothing to do.
    if (str == NULL || *str == '\0') {
        return str;
    }

    // 2. Create two pointers.
    // 'start' points to the first character of the string.
    char* start = str;
    // 'end' points to the last character of the string (before the null terminator).
    char* end = str + strlen(str) - 1;
    char temp; // A temporary variable for swapping.

    // 
    // 3. Loop until the pointers meet or cross in the middle.
    while (end > start) {
        // a. Store the character from the start.
        temp = *start;

        // b. Move the character from the end to the start's position.
        *start = *end;

        // c. Place the stored character (from the original start) at the end's position.
        *end = temp;

        // d. Move the pointers one step closer to the middle.
        start++;
        end--;
    }

    // 4. Return the pointer to the modified string.
    return str;
}

char* concatenate(const char* str1, const char* str2) {
    // 1. Calculate the total memory needed.
    // It's the length of str1 + length of str2 + 1 byte for the null terminator.
    size_t len1 = strlen(str1);
    size_t len2 = strlen(str2);
    size_t total_len = len1 + len2 + 1;

    // 2. Allocate the new memory block from the heap.
    // 
    char* new_string = (char*)malloc(total_len);

    // 3. IMPORTANT: Check if malloc succeeded.
    // If memory is exhausted, malloc returns NULL. We must handle this gracefully.
    if (new_string == NULL) {
        return NULL; // Signal failure to the caller.
    }

    // 4. Copy the strings into the new memory block.
    // strcpy is safe here because we calculated the exact size needed for str1.
    strcpy(new_string, str1);
    // strcat is safe here because we also allocated space for str2.
    strcat(new_string, str2);

    // 5. Return the pointer to the beginning of the new string.
    return new_string;
}
