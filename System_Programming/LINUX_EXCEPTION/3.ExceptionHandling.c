#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <syslog.h>
#include <sys/mman.h>

void genArythmEx() {
    const int one = 1;
    const int zero = 0;
    const int exception = one / zero;
}

void genBusExcep() {
    FILE *f = tmpfile();
    int *m = mmap(0, 4, PROT_WRITE, MAP_PRIVATE, fileno(f), 0);
    *m = 0;
}

void arytmeticSingalHandler(const int signalCode) {
    const char* message = "Arythmetic exception occured! Code: 0x";
    printf("%s%x\n", message, signalCode);
    openlog("ExceptionHandling", 0, LOG_USER);
    syslog(LOG_NOTICE, "%s%x\n", message, signalCode);
    closelog();
    exit(EXIT_FAILURE);
}

void busSignalHandler(const int signalCode) {
    const char* message = "Bus exception occured! Code: 0x";
    printf("%s%x\n", message, signalCode);
    openlog("ExceptionHadnling", 0, LOG_USER);
    syslog(LOG_NOTICE, "%s%x\n", message, signalCode);
    closelog();
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        return 0x1;
    }
    if (signal(SIGFPE, arytmeticSingalHandler) == SIG_ERR)
        return 0x2;
    if (signal(SIGBUS, busSignalHandler) == SIG_ERR)
        return 0x3;
    if (strcmp(argv[1], "0") == 0)
        genArythmEx();
    else if (strcmp(argv[1], "1") == 0)
        genBusExcep();

    return 0x0;
}
