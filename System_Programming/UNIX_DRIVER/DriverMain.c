#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<fcntl.h>
#include<string.h>
#include<unistd.h>
#include <linux/fs.h>

int main() {
	int fd;
	size_t cnt = 0;
    char bufferIn[50];
	char bufferOut[50];

	printf("We work with character device driver\n");
	fd = open("/dev/DriverUNIX", O_RDWR);
	if (fd == -1) {
		printf("open failed\n");
		return -1;
	}
	printf("Character driver open\n");
	
	cnt = read(fd, bufferOut, sizeof(bufferIn));
	printf("Character driver read %ld bytes\n", cnt);
	
	int i=0;
	while (i < cnt){
		printf("%c", bufferOut[i]);
		i++;
	}
	
	close(fd);
	printf("\nCharacter driver close\n");
	return 0;
}