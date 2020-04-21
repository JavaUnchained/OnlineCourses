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
#include<iostream>
#include<windows.h>
#include<string>
#include<excpt.h>

//Проверить корректность выхода из блока __try с помощью функции AbnormalTermination в финальном обработчике finally.
using namespace std;
void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using AbnormalTermination in finally for look at correct or not exiting programm .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	__try
	{
		Log_F("In primary try. \n");
		puts("In first try .");
		__try
		{
			Log_F("In second try. \n");
			puts("In second try .");
			puts("Rise invalid operation");
			Log_F("Rising flt invalid operation exception with RiseException methods. \n");
			Log_F("RiseException settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
			RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
		}
		__except (EXCEPTION_EXECUTE_HANDLER)
		{
			puts("In except .");
		}

	}
	__finally
	{          
		Log_F("In finally block. \n");
		puts("in finally block");
		cout << "Try is " << (AbnormalTermination() ? " normall." : " unnormal") << endl;
		Log_F("Compare AmbormalTermination value. \n");
		if (AbnormalTermination()) {
			Log_F("AbnormalTermination get true. All's normal \n");
		}
		else {
			Log_F("AbnormalTermination get false. Programm ending unnormal. \n");
		}

	}
	puts("end programm");
	Log_F("Ending the programm. \n");
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
