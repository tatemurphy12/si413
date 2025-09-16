#include <stdio.h>
#include <stdlib.h>
#include "helpers.h"

int main()
{
	char str[] = "Hello World";
	printf("Original:  \"%s\"\n", str);
    	reverse(str);
    	printf("In-place:  \"%s\"\n", str);

	char buffer[100]; // We provide the memory
	const char* first = "Wow this is ";
	const char* second = "really freaking hard";
	concatenate(buffer, sizeof(buffer), first, second);
	printf("Safe concat result: \"%s\"\n", buffer);	
}
