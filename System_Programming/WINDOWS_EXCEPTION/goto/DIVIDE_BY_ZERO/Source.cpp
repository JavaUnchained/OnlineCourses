#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>

//Обработать вложенные исключения;


using namespace std;

int main()
{
	puts("Iinitial a and b .");

	//puts("");

	__try {
		puts("In try .");
		puts("Divide by zero");
		int a = 0;
		int b = 1;
		a = b / a;
	}
	_except((GetExceptionCode() == EXCEPTION_INT_DIVIDE_BY_ZERO) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		//код обработчика исключений
		puts("In except");
		puts("Bypass exit with goto");
		goto goOut;
		exit(1);
		// ислючение деления на ноль обработанно
	}
	goOut://лейбл
	puts("end programm");
	return 0;
}
