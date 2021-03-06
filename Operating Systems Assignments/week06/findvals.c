#include "utils.h"                                 // include utils.h to get approxEqual method
#include <stdio.h>
#include <stdlib.h>
#include <string.h>                                // include to be able to call functions
#include <time.h>                                  // to include start and finish time  

float ref, tol;                                    // create reference and tolerance variables
char *refString = NULL;                                        // create char to read ref and tol input as char
char *tolString = NULL;                                       // create char to read ref and tol input as char

struct tm *local;                                  // structuring time format to local
time_t start, end;                                 // creating start and end time variables

int main(int argc, char* argv[])
{
  
  if (strcmp(argv[1], "-r") && strcmp(argv[3], "-t") )              // check if first arg is "-r" & third is "-t"
   {
     refString = argv[2];                                 // save input in ref char
     tolString = argv[4];                                // save input in tol char
   }
  
   else if (strcmp(argv[1], "-t") && strcmp(argv[3], "-r"))         // check if third arg is "-r" & third is "-r"
   {
     refString = argv[4];                                 // save input in ref char
     tolString = argv[2];                                // save input in tol char     
   }

  /*in case of incrrect command */
   else printf("Incorrect command");

  ref = strtof(refString, 0);                             // convert ref char to float
  tol = strtof(tolString, 0);                            // convert tol char to float
  if (tol < 0.0)
    tol *= -1;

  /*command ok, ready to read and compute */

  
 // read and print clock at start of program
  time(&start);                                                 // read and record the clock at start
  local = localtime(&start);                                    // structuring time to local time format
  printf("# Start time and date is: %s", asctime(local));


  
  /*code to request memory allocation copied from lab sheet*/
  int rct, cct;                         // define rct and cct (number of rows and columns needed)
  fscanf(stdin, "%d", &ref);             // int number of rows from the first location of the "input" file
  fscanf(scanf, "%d", &tol);             // int number of columns from the second location of the "input" file
  
  float **rows = (float**) malloc(rct * sizeof(float*) );
  if (rows == 0)
    {
      fprintf(stderr, "Couldn't allocate sufficient space. \n");
      exit(1);
    }

  int i;                                     // variable for number of rows
  for (i = 0; i < rct; i++)
    {
      float *row = (float*) malloc(cct * sizeof(float));
      if (row == 0)
	{
	  fprintf(stderr, "Couldn't allocate sufficeint row space.\n");
	  exit(1);
	}
      rows[i] = row;
    }

  /* reading through the input matrix and saving numbers in array*/

  int r, c;                                      // variable for number of columns
  for (r = 0; r < rct; r++)
    { for (c = 0; c < cct; c++)
	{
	  fscanf(stdin, "%f", &rows[c][r]);      // read numbers and save in array
	}
    }

  /* testing each element and printing them out with location info*/

  



  
  // test each element in loop and print out location and element with "hits"

  int count;
  for (r = 0; r < rct; r++)
    for (c = 0; c < cct; c++)
      if (approxEqual(ref, rows[r][c], tol) == 1)
	{
	  fprintf(stdout, "r=%d, c=%d: %.6f\n", r, c, rows[r][c]);
	  count++;
	}
  
  // print: Found _____ approximate matches.
  fprintf(stdout, "Found %d approxinmate matches", count);



  
  // print # End time and date:
  time(&end);                                                 // read and record the clock at finish
  local = localtime(&end);                                    // structuring time to local time format
  printf("# End time and date is: %s", asctime(local));

  
  exit(0);
  
} //main
