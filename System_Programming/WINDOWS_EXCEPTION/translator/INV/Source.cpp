#include <iostream>
#include <conio.h>
#include <Windows.h>
#include <excpt.h>
#include<tchar.h>
#include<cstring>
#include<ctime>
#include<time.h>
#include<chrono>
#include<fstream>
#include<eh.h>

// параметр для компилирования /Eha и убрать /EHsc
//Преобразовать структурное исключение в исключение языка С, используя функцию translator;


void Log_F(std::string str);
std::ofstream logfile;
void handler(unsigned int n, _EXCEPTION_POINTERS* ptr);

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using own traslattor in a C style. .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");

	//запускаем траслятор

	Log_F("Start translator like _set_se_translator(handler). \n");
	_set_se_translator(handler);

	try
	{
		Log_F("In c-style try. \n");
		puts("in try. in C style");
		Log_F("Rised flt invalid operation exception. \n");
		Log_F("RiseException settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
		puts("Rise invalid operation");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
	}
	catch (...)
	{
		Log_F("In c-style cath. \n");
		puts("in catch.");
	}
	
	puts("end programm");
	Log_F("End the programm. \n");
	Log_F("Closed the log file. \n");
	logfile.close();
	return 0;
}

#pragma unmanaged  
void handler(unsigned int n, _EXCEPTION_POINTERS* ptr)
{
	Log_F("In handler function. \n");
}

void Log_F(std::string str)
{
	struct tm newtime;
	time_t now = time(0);
	localtime_s(&newtime, &now);

	logfile << " [" << newtime.tm_mday << "." << newtime.tm_mon + 1 << "." << newtime.tm_year + 1900 << " ";
	logfile << newtime.tm_hour << ":" << newtime.tm_min << ":" << newtime.tm_sec << "]> " << str;
}
