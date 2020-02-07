#include "utils.h"                                 // include " " to be able to use functions I created
#include <stdio.h>
#include <stdlib.h>
#include <string.h>                                // include to be able to call functions


int main(int argc, char* argv[])
{
  char c;
  FILE *from;
  FILE *to;
  int flipping = 69;                               // declaring flipping variable

  flipping = (strcmp(argv[1], "-f"));              // check if first arg is "-f". 0 if equal
  if (flipping != 0)                               // if not 0, first and second args are the files to use
   {     
      from = fopen(argv[1], "r");                 // open file entered in first arg
      to = fopen(argv[2], "w");                   // write into file entered in second arg
      
   }
  
  else if (flipping == 0)                              // if 0, second and third args are the files to use
   {
      from = fopen(argv[2], "r");
      to = fopen(argv[3], "w");
   }
  
      
  else if (from == NULL)                               // if "from" file not there
  {
    perror("file.txt");
    exit(1);
  }

  /* file exists, so start reading */
  while ((c = getc(from)) != EOF)
     if (flipping == 0 )                          // if char need to be flipped       
       {
       putc(flipChar(c), to);                     // write encripted character to the output file
     }
     else                                        // if not flipped, just write as is
      putc(c, to);

  fclose(from);
  fclose(to);

  exit(0);
}


