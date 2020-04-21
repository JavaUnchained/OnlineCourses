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

//Обработать вложенные исключения;
void Log_F(std::string str);
std::ofstream logfile;


int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using __finally in try-except construction .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	puts("In main");
	__try {
		__try {
			Log_F("In try. \n");
			puts("In try .");
			Log_F("Rising flt invalid operation exception with RiseException methods. \n");
			Log_F("RiseException settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
			puts("Rise invalid operation");
			RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
		}
		__except (GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION)
		{
			Log_F("In exception block. \n");
			Log_F("Exception exceution handling. \n");
		}
	}
	__finally
	{
		puts("in finally block");
		puts("end programm");
		Log_F("End the programm. \n");
		Log_F("Closed the log file. \n");
		logfile.close();
		return 0;
	}
}

void Log_F(std::string str)
{
	struct tm newtime;
	time_t now = time(0);
	localtime_s(&newtime, &now);

	logfile << " [" << newtime.tm_mday << "." << newtime.tm_mon + 1 << "." << newtime.tm_year + 1900 << " ";
	logfile << newtime.tm_hour << ":" << newtime.tm_min << ":" << newtime.tm_sec << "]> " << str;
}
