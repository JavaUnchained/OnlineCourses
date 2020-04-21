#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>

//Выйти из блока __try с помощью оператора leave;

using namespace std;

int main()
{

	__try 
	{
		puts("Iinitial a = 10, b = 100,c =0 .");
		int a = 10;
		int b = 100;
		double c = 0;
		puts("In primary try .");
		__try {
			puts("In second try .");
			puts("go in while");

			while (a--)
			{
				if (a == 1) {
					puts("in if.");
					a = 0;
					__leave;
				}
				else {
					c = b / c;
					printf("%d%s", a, "\n");
				}
			}
		}
		__except (EXCEPTION_EXECUTE_HANDLER)
		{
			puts("in second except");
			exit(1);
		}
		puts("Divide by zero in primary try");
		b = b / a;
	}
	_except((GetExceptionCode() == EXCEPTION_INT_DIVIDE_BY_ZERO) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		//код обработчика исключений
		puts("In primary except");
		// ислючение деления на ноль обработанно
	}
	puts("end programm");
	return 0;
}