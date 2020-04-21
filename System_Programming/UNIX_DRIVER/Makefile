CONFIG_MODULE_SIG=n
obj-m+=DriverUNIX.o
all:
	make -C/lib/modules/$(shell uname -r)/build M=$(PWD) modules
clean:
	make -C/lib/modules/$(shell uname -r)/build M=$(PWD) clean
create:
	sudo mknod -m 666 /dev/DriverUNIX c 123 0
