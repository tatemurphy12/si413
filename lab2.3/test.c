#include <stdio.h>
#include <stdbool.h>

void print_aye_or_nay(bool *bool_ptr) {
    bool bool_val = *bool_ptr;
    if (bool_val)
        puts("Aye");
    else
        puts("Nay");
}

