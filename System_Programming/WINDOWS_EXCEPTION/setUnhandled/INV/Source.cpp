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


//Использовать функции UnhandleExceptionFilter 
//и Set UnhandleExceptionFilter для необработанных исключений;

void Log_F(std::string str);
std::ofstream logfile;

//void unhadledRecovering();

LONG WINAPI funcForSet(
	struct _EXCEPTION_POINTERS* ptr
)
{
	Log_F("In funcForSet. \n");
	puts("in funcForSet");
	Log_F("Incrementing a value. \n");
	Log_F("Unhandled exception occured. \n");
	puts("Unhandled exception occured");
	puts("end programm");
	Log_F("End the programm. \n");
	Log_F("Closed the log file. \n");
	logfile.close();
	return EXCEPTION_EXECUTE_HANDLER;
}

int main()
{
	puts("Start the programm. \n");
	logfile.open("log.txt", std::ofstream::out | std::ofstream::trunc);
	Log_F("Question: using UnhandleExceptionFilter and SetUnhandleExceptionFilter .\n");
	Log_F("Launch this programm. \n");
	Log_F("In main. \n");
	Log_F("Using SetUnhandledExceptionFilter with setting funcForSet. \n");
	SetUnhandledExceptionFilter(funcForSet);
	Log_F("Invalid operation checked. \n");
	puts("invalid operation cheked");
	Log_F("Rising float invalid operation exception. \n");
	RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);
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

/*
	//unhadledRecovering();
void unhadledRecovering() {
	Log_F("In unhadledRecovering(). \n");
	puts("in unhadledRecovering");
	Log_F("Set Unhadled Exception Filter to defult ( as system function). \n");
	puts("Set Unhadled Exception Filter to defult ( as system function) is null-settings");
	SetUnhandledExceptionFilter(NULL);
	__try {
		Log_F("In try. \n");
		puts("In try.");
		Log_F("Initialization of RiseException with EXCEPTION_FLT_INVALID_OPERATION with RiseException method. \n");
		puts("Initialization of RiseException with EXCEPTION_FLT_INVALID_OPERATION");
		RaiseException(EXCEPTION_FLT_INVALID_OPERATION, 0, 0, NULL);//принудительная генерация исключения
	}
	_except(UnhandledExceptionFilter(GetExceptionInformation())) //значение фильтр
	{
		Log_F("In except. \n");
		//код обработчика исключений
		puts("In except");
		Log_F("ExiteProcessing. \n");
		puts("ExitProcess");
		//ExitProcess(1);
	}
}
*/
