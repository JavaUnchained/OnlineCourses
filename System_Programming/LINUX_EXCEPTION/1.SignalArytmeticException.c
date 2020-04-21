#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <sys/mman.h>
#include <fcntl.h>

void genArythmEx() {
    const int a = 1;
    const int b = 0;
    const int exception = a  / b;
}

int main(int argc, char *argv[]){
    puts("Start programm (Generation ArythmeticException"); 
    genArythmEx();
    return 0x0;
}