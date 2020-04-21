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

//Получить информацию об исключении с помощью функции GetExceptionInformation; 
//сгенерировать исключение с помощью функции RaiseException;

EXCEPTION_POINTERS* xp;
static int xfilter(EXCEPTION_POINTERS* xp);

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: Take some infromation with GetExcaptionInformation and generetion exception with rise method for divide by zero.\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	__try {
		Log_F("In try. \n");
		Log_F("Generation exception with Rise  method.\n");
		Log_F("Rise exception settings: EXCEPTION_INT_DIVIDE_BY_ZERO, 0, 0, NULL \n");
		RaiseException(EXCEPTION_INT_DIVIDE_BY_ZERO, 0, 0, NULL);//принудительная генерация исключения
	}
	_except(xfilter(xp = GetExceptionInformation())) //значение фильтр
	{
		Log_F("In except. \n");
		//код обработчика исключений
		puts("in except"); // ислючение деления на ноль обработанно
	}
	puts("end programm");
	Log_F("End the programm. \n");
	Log_F("Closed the log file. \n");
	logfile.close();
	return 0;
}

static int xfilter(EXCEPTION_POINTERS* xp) {
	Log_F("In xfilter with seeting EXCEPTION_POINTERS* GetExceptionInformation. \n");
	puts("in filter.");
	EXCEPTION_RECORD* xr = xp->ExceptionRecord;
	Log_F("Compare xr value with any exception in swith-case construction. \n");
	
	switch (xr->ExceptionCode) {
	case EXCEPTION_INT_DIVIDE_BY_ZERO:
		Log_F("Case EXEPTION_INT_DIVIDE_BY_ZERO. \n");
		puts("Caught Zero divide as expected");
		Log_F("Return exception execute handler. \n");
		return EXCEPTION_EXECUTE_HANDLER;
	default:
		// give up
		Log_F(" EXCEPTION_CONTINUE_SEARCH. \n");
		return EXCEPTION_CONTINUE_SEARCH;
	};
}


void Log_F(std::string str)
{
	struct tm newtime;
	time_t now = time(0);
	localtime_s(&newtime, &now);

	logfile << " [" << newtime.tm_mday << "." << newtime.tm_mon + 1 << "." << newtime.tm_year + 1900 << " ";
	logfile << newtime.tm_hour << ":" << newtime.tm_min << ":" << newtime.tm_sec << "]> " << str;
}

