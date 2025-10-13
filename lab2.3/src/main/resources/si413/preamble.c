#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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


    if (str == NULL || *str == '\0') {
        return str;
    }

    // 'start' points to the first character of the string.
    char* start = str;
    // 'end' points to the last character of the string (before the null terminator).
    char* end = str + strlen(str) - 1;
    char temp; 

    while (end > start) {
        temp = *start;

        *start = *end;

        *end = temp;

        start++;
        end--;
    }

    return str;
}

char* concatenate(const char* str1, const char* str2) {
    size_t len1 = strlen(str1);
    size_t len2 = strlen(str2);
    size_t total_len = len1 + len2 + 1;

    char* new_string = (char*)malloc(total_len);

    if (new_string == NULL) {
        return NULL; 
    }

    strcpy(new_string, str1);
    strcat(new_string, str2);

    return new_string;
}

int string_compare(char* str1, char* str2)
{
	return strcmp(str1, str2);
}

int string_contains(char* str1, char* str2)
{
	if (strstr(str1, str2) != NULL)
		return 1;
	else
		return 0;
}

void print_aye_or_nay(bool *bool_ptr) {
    bool bool_val = *bool_ptr;
    if (bool_val)
        puts("Aye");
    else
        puts("Nay");
}
