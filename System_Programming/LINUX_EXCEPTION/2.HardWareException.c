#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <sys/mman.h>
#include <fcntl.h>

void genBusExcep() {
    FILE *f = tmpfile();
    int *m = mmap(0, 4, PROT_WRITE, MAP_PRIVATE, fileno(f), 0);
    *m = 0;
}

int main(int argc, char *argv[]){
    puts("Start programm (Generation hardware exception like Misaligned address error"); 
    genBusExcep();
    return 0x0;
}
