//************************************************************************
// 420-B10  Java Assignment #2
//
// Class Name: 		Format String
// Programmer Name:	Anne Hamilton
// Date Written:	21/10/2002
// Description:
/** It is a class to format String
 */
//**********************************************************************

public class ReportFormat
{

  public static String leftJustify(String s, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer(s);

    for (int i = 1; i <= fieldSize - s.length(); ++i)
      resultStr.append(" ");

    return resultStr.toString();
  } // leftJustify(String, int)

  public static String leftJustify(char c, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer(String.valueOf(c));

    for (int i = 1; i <= fieldSize - 1; ++i)
      resultStr.append(" ");

    return resultStr.toString();
  } // leftJustify(String, char)

  public static String leftJustify(double n, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

    int spacesNeeded = fieldSize - String.valueOf(n).length();
    resultStr.append(n);
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");

    return resultStr.toString();
  } // leftJustify(double, int)

  public static String leftJustify(int n, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

   int spacesNeeded = fieldSize - String.valueOf(n).length();
   resultStr.append(n);
   for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");

    return resultStr.toString();
  } // leftJustify(int, int)

  public static String rightJustify(String s, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

    int spacesNeeded = fieldSize - s.length();
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(s);

    return resultStr.toString();
  } // rightJustify(String, int)


  public static String rightJustify(char c, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();
    int spacesNeeded = fieldSize - 1;
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(String.valueOf(c));

    return resultStr.toString();
  } // rightJustify(char, int)

  public static String rightJustify(double n, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

    int spacesNeeded = fieldSize - String.valueOf(n).length();
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(n);

    return resultStr.toString();
  } // rightJustify(double, int)

  public static String rightJustify(int n, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

    int spacesNeeded = fieldSize - String.valueOf(n).length();
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(n);

    return resultStr.toString();
  } // rightJustify(int, int)

  public static String centre(String s, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();

    int spacesNeeded = (fieldSize - String.valueOf(s).length())/2;
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(s);
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    if ((fieldSize - String.valueOf(s).length()) % 2 == 1)
      resultStr.append(" ");

    return resultStr.toString();
  } // centre(String, int)

  public static String centre(char c, int fieldSize)
  {
    StringBuffer resultStr = new StringBuffer();
    int spacesNeeded = (fieldSize - 1)/2;
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    resultStr.append(String.valueOf(c));
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(" ");
    if ((fieldSize - 1) % 2 == 1)
      resultStr.append(" ");

    return resultStr.toString();
  } // rightJustify(char, int)
  
  public static String surround(String s, int fieldSize, char ch)
  {
    StringBuffer resultStr = new StringBuffer();
    int spacesNeeded = (fieldSize - 1)/2;
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(ch);
    resultStr.append(s);
    for (int i = 1; i <= spacesNeeded; ++i)
      resultStr.append(ch);
    if ((fieldSize - 1) % 2 == 1)
      resultStr.append(ch);

    return resultStr.toString();
  }
} // ReportFormat class
