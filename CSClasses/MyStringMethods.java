import java.util.StringTokenizer;

public class MyStringMethods
{
  public MyStringMethods()
  {
  }

  public static String toSentenceCase(String s)
  {
    String result = s.trim().toLowerCase();
    result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
    if (result.endsWith(".") || result.endsWith("!") ||
        result.endsWith("?"))
      return result;
    else
      return result + ".";
  } // toSentenceCase(String)

  public static String toTitleCase(String s)
  {
    StringBuffer result = new StringBuffer();
    StringTokenizer words = new StringTokenizer(s);
    while (words.hasMoreTokens())
    {
      String word = words.nextToken();
      result.append(Character.toUpperCase(word.charAt(0)) +
                   word.substring(1).toLowerCase() + " ");
    }
    return result.toString().trim();
  } // toSentenceCase(String)
} // MyStringMethods class
