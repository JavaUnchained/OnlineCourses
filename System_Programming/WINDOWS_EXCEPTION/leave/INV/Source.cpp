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

//Выйти из блока __try с помощью оператора leave;

void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using leave operator .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	puts("Iinitial a = 10, b = 100,c =0 .");
	Log_F("Initial a = 10, b = 100, c = 0. \n");
	int a = 10;
	int b = 100;
	double c = 0;
	
	__try
	{
		puts("In primary try .");
		Log_F("In primary try. \n");
		__try {
			Log_F("In second try. \n");
			puts("In second try .");
			Log_F("Go in while loop. \n");
			puts("go in while");
			while (a--)
			{
				if (a == 1) {
					Log_F("In if construction. \n");
					Log_F("a == 1. \n");
					puts("in if.");
					Log_F("Out with __leave. \n");
					__leave;
				}
				else {
					Log_F("In else construction. \n");
					Log_F("Divison b by c. \n");
					Log_F("Printing a. \n");
					c = b / c;
					printf("%d%s", a, "\n");
				}
			}
		}
		__except (EXCEPTION_EXECUTE_HANDLER)
		{
			Log_F("In second except. \n");
			Log_F("Execution handling. \n");
			puts("in second except");
			//exit(1);
		}
		Log_F("In primary try. \n");
		Log_F("Rising flt invalid operation exception with RiseException methods. \n");
		Log_F("RiseException settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
	}
	_except((GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		Log_F("In primary except. \n");
		Log_F("Filter: GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION \n");
		Log_F("Exception execute handling. \n");
		//код обработчика исключений
		puts("In primary except");
		// ислючение деления на ноль обработанно
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
