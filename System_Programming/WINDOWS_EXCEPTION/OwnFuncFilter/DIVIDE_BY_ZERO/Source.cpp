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

//Создать собственную функцию - фильтр;

LONG Filter(DWORD dwExceptionGode);
void Log_F(std::string str);
std::ofstream logfile;

int main()
{
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Start logfile. \n");
	Log_F("Question: creating own filter-function for invalid operation.\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	__try {
		Log_F("In try. \n");
		Log_F("Generation exception with Rise  method.\n");
		Log_F("Rise exception settings is EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL \n");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
	}
	__except (Filter(GetExceptionCode()))
	{
		Log_F("Filter in except catched invalid operation exception. \n");
		printf("%s", "srabotalo");
		//exit(1);
	}
	Log_F("End the programm. \n");
	Log_F("Closed the log file. \n");
	logfile.close();
	return 0;
}

LONG Filter(DWORD exCode)
{
	Log_F("In filter function. \n");
	Log_F("Start if-else contruction. \n");
	if (exCode == EXCEPTION_FLT_INVALID_OPERATION) {
		Log_F("Into if. That mean our exCode equalse EXCEPTION_FLT_INVALID_OPERATION \n");
		printf("%s%d%s", "Exeption ", exCode, " hex (invalid operation) succesfull recognized. Handling \n");
		Log_F("Return execute handler. \n");
		return EXCEPTION_EXECUTE_HANDLER;
	}
	else {
		Log_F("In else construction. \n");
		Log_F("exCode none equalse EXCEPTION_FLT_INVALID_OPERATION. \n");
		printf("%s%d%s", "Another error with code ", exCode, " hex was founded.\n Continue searching");
		Log_F("Retrun exception continue searching. \n");
		return EXCEPTION_CONTINUE_SEARCH;
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