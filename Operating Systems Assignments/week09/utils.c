#include "utils.h"

char flipChar(char c)
{
  if ('a' <= c && c <= 'z')              // character is a valid lower case letter
    return 'a'-c + 'z';                   // 'a'-c is offset, 26 letter in alphabet. i.e. if offset is -3, result is 24
  else if ('A' <= c && c <= 'Z')              // character is valid upper case
    return ('A'-c + 'Z');
  else if ('0' <= c && c <= '9')              // character is a valid number
    return ('0'-c + '9');                   // 10 number form 0 to 9    

  return c;                              // leave the caracter, if not LC or UC or number
}


float approxEqual(float number, float reference, float tolerance)
{
  return (reference - tolerance <=  number && number <= reference + tolerance);
}
  
