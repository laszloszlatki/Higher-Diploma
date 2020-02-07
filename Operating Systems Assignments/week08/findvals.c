#include "utils.h"                                 // include utils.h to get approxEqual method
#include <stdio.h>
#include <stdlib.h>
#include <string.h>                                // include to be able to call functions
#include <time.h>                                  // to include start and finish time
#include <sys/time.h>                              // to include gettomeofday() for CPU clock ticks


int main(int argc, char *argv[])
{
  
float ref, tol;                                    // create reference and tolerance variables
char *refString;                                   // create char to read ref and tol input as string
char *tolString;                                   // create char to read ref and tol input as string
char *verbose = NULL;                                              // create !!boolean!! value for verbose

 refString = tolString = NULL;

   
 int index;
 for (index = 1; index < argc; index++)            // loop though the input array
 {
    if (strcmp(argv[index], "-r") == 0)            // look for "-r" and set next index to the string value
      {
	refString = argv[index+1];
	index++;
      }
    if (strcmp(argv[index], "-t") == 0)            // look for "-r" and set next index to the string value
      {
	tolString = argv[index+1];
	index++;
      }
    if (strcmp(argv[index], "-v") == 0)                            // look for "-v" and set verbose to true
      {
	verbose = "true";
      }
  
 }

   
  /*command ok, ready to read and compute */

  
 // read and print clock at start of program

 /* creating time for # StartTime and # EndTime */
  struct tm *local;                                  // structuring time format to local
  time_t start, end;                                 // creating start and end time variables
  
  time(&start);                                         // read and record the clock at start
  local = localtime(&start);                            // structuring time to local time format
  printf("# Start time and date: %s", asctime(local));

  /* creating gettimeofday for elapsedTime  */

  struct timeval tim;
  gettimeofday(&tim, NULL);
  double t1 = tim.tv_sec + (tim.tv_usec/1000000.0);      //t1 is the value (in miliseconds) when execution starts
  
  
  ref = strtof(refString, 0);                            // convert ref string to float
  tol = strtof(tolString, 0);                            // convert tol string to float
  
  
  /*code to request memory allocation copied from lab sheet*/
  int rct, cct;                          // define rct and cct (number of rows and columns needed)
  fscanf(stdin, "%d", &rct);             // int number of rows from the first location of the "input" file
  fscanf(stdin, "%d", &cct);             // int number of columns from the second location of the "input" file
  
  float** rows = (float**) malloc(rct * sizeof(float *) );
  if (rows == 0)
    {
      fprintf(stderr, "Couldn't allocate sufficient space. \n");
      exit(1);
    }

  int i;                                     // variable for number of rows
  for (i = 0; i < rct; i++)
    {
      float* row = (float*) malloc(cct * sizeof(float));
      if (row == 0)
	{
	  fprintf(stderr, "Couldn't allocate sufficeint row space.\n");
	  exit(1);
	}
      rows[i] = row;
    }

  /* reading through the input matrix and saving numbers in array*/

  int r, c;                                      
  for (r = 0; r < rct; r++)
    { for (c = 0; c < cct; c++)
	{
	  fscanf(stdin, "%f", &rows[r][c]);      // read numbers and save in array
	}
    }

  /* testing each element and printing them out with location info*/

  int count = 0;
  for (r = 0; r < rct; r++)
    {
    for (c = 0; c < cct; c++)
      {
	if (approxEqual(rows[r][c], ref, tol) == 1)      
	{
	  if(verbose == "true")                            // add new if to check if verbose is set to true before printing
	    {
	  fprintf(stdout, "r=%d, c=%d: %.6f\n", r, c, rows[r][c]);
	    }
	  count++;
	}
      }
    }
  
  // print total hits: Found _____ approximate matches.
  fprintf(stdout, "Found %d approximate matches.\n", count);


  /*  Don't need to print this out in week08
  
  // print # End time and date:
  time(&end);                                                 // read and record the clock at finish
  local = localtime(&end);                                    // structuring time to local time format
  printf("# End time and date: %s", asctime(local));

  */

  gettimeofday(&tim, NULL);
  double t2 = tim.tv_sec + (tim.tv_usec/1000000.0);
  printf("Elapsed time: %.6lf(s)\n", t2-t1);
  exit(0);
  
} //main


/* information about gettimeofday() from   http://rabbit.eng.miami.edu/info/functions/time.html#gtod


 int gettimeofday(timeval *tp, NULL)
      include: <sys/time.h>
      Note In C programs (as opposed to C++) the word "struct" must
      appear before "timeval".
      Gets the time of day. The parameter must be a pointer to a previously
      declared timeval variable (or in C, a struct timeval variable). This
      struct type is also defined in <sys/time.h>. A timeval has two
      components, both ints. One (called tv_sec) is exactly the value that
      would be returned by time, the time in seconds since 1/1/1970.
      The other (called tv_usec) is the number of microseconds into that
      second. Don't be fooled: although the units are microseconds, the value
      is nothing like that accurate. On many systems, 10000 is added 100
      times per second.
         Example:

             timeval tim;
             gettimeofday(&tim, NULL);
             double t1=tim.tv_sec+(tim.tv_usec/1000000.0);

             do_something_long();

             gettimeofday(&tim, NULL);
             double t2=tim.tv_sec+(tim.tv_usec/1000000.0);
             printf("%.6lf seconds elapsed\n", t2-t1);

      The second parameter (NULL) used to be to retrieve the local time zone,
      but time zones are no-longer handled that way.


*/


/*
to run the program: compile utils.c, findval.c
                    connect utils and findvals
		    run
gcc -c utils.c
gcc -c findvals.c
gcc findvals.o utils.o -o findvals
./findvals -r 5.6 -t 23.0 <mat.100x50
./findvals -r 5.6 -t 23.0 -v <mat.100x50
./findvals -v -r 5.6 -t 23.0 <mat.100x50 >results


*/
