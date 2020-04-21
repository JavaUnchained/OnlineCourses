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


//using namespace std;
void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: handling nested exceptions .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	puts("In main.");
	__try {
		Log_F("In primary try. \n");
		puts("In primary try .");
		__try {
			Log_F("In second try . \n");
			puts("In second try");
			puts("Rise Invalid operation");
			Log_F("Rised flt invalid operation exception. Settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL \n");
			Log_F("Second except have trinity operator with GetExceptionCode. \n");
			Log_F("GetExceptionCode() != EXCEPTION_ARRAY_BOUNDS_EXCEEDED. \n");
			Log_F("EXCEPTION_CONTINUE_SEARCH. \n");
			RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
		}
		__except ((GetExceptionCode() == EXCEPTION_ARRAY_BOUNDS_EXCEEDED) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) {
			puts("In second except");
			Log_F("In second except block.\n");
			Log_F("Continue serching. \n");
		}
		Log_F("Return in primary try. \n");
		puts("In primary try .");
	}
	_except((GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		Log_F("In primary except . \n");
		Log_F("GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION . \n");
		Log_F("Exception invalid operation catched. \n");
		//код обработчика исключений
		puts("In primary except");
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

