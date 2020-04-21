#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>


int main()
{
	puts("Iinitial a and b .");

	//puts("");
	
	__try {
		puts("In primary try .");

		int a = 0;
		int b = 1;
		__try {
			puts("In second try .");
			puts("do zero divide");
			a = b / a;
		}
		__except (GetExceptionCode() ==EXCEPTION_INT_DIVIDE_BY_ZERO)
		{
			puts("except");
		}
	}
	__finally
	{
		puts("in finally block");
		puts("End programm");
		return 0;
	}

}