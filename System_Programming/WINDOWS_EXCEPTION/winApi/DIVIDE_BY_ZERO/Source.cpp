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

void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("WinAPI question with divide by zero exception.\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	Log_F("Initializing: a = 1, b = 0. \n");
	int a = 1;
	int b = 0;

	__try {
		Log_F("In try. \n");
		Log_F("Generation exception with Rise  method.\n");
		Log_F("Divide a by b\n");
		a = a / b;
	}
	_except(EXCEPTION_EXECUTE_HANDLER)
	{
		Log_F("In except. \n");
		puts("Exception divide by zero catched. \n");
		Log_F("Excecution handling. \n");
	}
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