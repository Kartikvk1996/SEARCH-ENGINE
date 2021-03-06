#ifndef LOGGER_INCLUDED
#define LOGGER_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include <string>
using namespace std;

class Logger {

    FILE *fptr;

public:

    Logger();

    Logger(char *fileName, char *mode);

    void log(string type, string logent);

    void ilog(string logent);

    void elog(string logent);
};

#endif