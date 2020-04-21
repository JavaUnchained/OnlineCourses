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
#include<string>
#include<sstream>

//Получить код исключения с помощью функции GetExceptionCode.
//используя функцию в выражении филтре

void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using GetExceptionCode used in filter for .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");

	__try {
		Log_F("In try. \n");
		puts("In try .");
		Log_F("Rised invalid operation. \n");
		Log_F("Rise exception settings is: EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
		puts("Rise invalid operation");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);

	}
	__except ((GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		Log_F("In except. \n");
		Log_F("GetExceptionCode() == EXEPTION_FLT_INVALID_OPERATION. \n");
		Log_F("Exception execute handalling. \n");
		//код обработчика исключений
		printf("%s", "Invalid operation exception cathed"); // ислючение деления на ноль обработанно
	}
	puts("end programm");
	Log_F("End the programm. \n");
	Log_F("Closed the log file. \n");
	logfile.close();
	return 0;
}

void Log_F(std::string str)
{
	struct tm newtime;
	time_t now = time(0);
	localtime_s(&newtime, &now);

	logfile << " [" << newtime.tm_mday << "." << newtime.tm_mon + 1 << "." << newtime.tm_year + 1900 << " ";
	logfile << newtime.tm_hour << ":" << newtime.tm_min << ":" << newtime.tm_sec << "]> " << str;
}