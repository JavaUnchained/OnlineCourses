#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>
#include<eh.h>

// �������� ��� ������������� /Eha � ������ /EHsc
//������������� ����������� ���������� � ���������� ����� �, ��������� ������� translator;

using namespace std;
void handler(unsigned int n, _EXCEPTION_POINTERS* ptr);

int main()
{
	puts("in main.");
	//��������� ���������
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