#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>
#include<eh.h>

// параметр для копилирования /Eha и убрать /EHsc
//Преобразовать структурное исключение в исключение языка С, используя функцию translator;

using namespace std;
void handler(unsigned int n, _EXCEPTION_POINTERS* ptr);

int main()
{
	puts("in main.");
	//запускаем траслятор
	_set_se_translator(handler);

	try
	{
		puts("in try. in C style");
		puts("generation divide by zero");
		RaiseException(EXCEPTION_INT_DIVIDE_BY_ZERO, 0, 0, NULL);
	}
	catch (...)
	{
		puts("in catch.");
	}
	puts("end programm");
	return 0;
}

#pragma unmanaged  
void handler(unsigned int n, _EXCEPTION_POINTERS* ptr)
{
	puts("in handler");
	throw EXCEPTION_INT_DIVIDE_BY_ZERO;
}