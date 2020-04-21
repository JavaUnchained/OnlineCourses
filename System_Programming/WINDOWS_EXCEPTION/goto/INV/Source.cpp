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
//goto;


void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using goto operator .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	__try {
		Log_F("In try. \n");
		puts("In try .");
		Log_F("Rised invalid operation. \n");
		Log_F("Rise exception settings is: EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL. \n");
		puts("Rise invalid operation");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
		Log_F("GetExceptionCode() == EXEPTION_FLT_INVALID_OPERATION. \n");
		Log_F("Exception execute handalling. \n");
	}
	_except((GetExceptionCode() == EXCEPTION_FLT_INVALID_OPERATION) ? EXCEPTION_EXECUTE_HANDLER : EXCEPTION_CONTINUE_SEARCH) //значение фильтр
	{
		Log_F("In except. \n");
		Log_F("Invalid operation catched. \n");
		//код обработчика исключений
		puts("In except");
		puts("Bypass exit with goto");
		Log_F("Use goto from label. \n");
		goto label;
		exit(1);
		// ислючение деления на ноль обработанно
	}

label://лейбл
	Log_F("There was a stack rollout. \n");
	Log_F("About goto label. \n");
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