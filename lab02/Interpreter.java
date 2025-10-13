import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter 
{
  public static String reverse(String str)
  {
		if (str == null || str.isEmpty())
			return str;
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
  }

  public static String commentParser(String line)
  {
	String regex = "~.*?~|~.*$";
	String lineNoComm = line.replaceAll(regex, "");
	return lineNoComm;	
  }

  public static String input()
  {
		Scanner sin = new Scanner(System.in);
		return Interpreter.escapeSeq(sin.nextLine());
  }


  public static String escapeSeq(String str)
  {
		  if (str.isEmpty())
				  return str;

		  StringBuilder str2 = new StringBuilder();
		  for (int i = 0; i < str.length(); i++)
		  {
				  char c = str.charAt(i);
				  if (c == '\\' && i + 1 < str.length())
				  {
						  str2.append(str.charAt(i+1));
						  i++;
				  }
				  else
						  str2.append(c);
		  }

		  return str2.toString();
  }

  public static void parser(ArrayList<String> lines)
  {
    for (String line : lines)
    {
	  line = Interpreter.commentParser(line).strip();
	  if (line.length() == 0)
		continue;
	  
      //seperate the line by print statements
      String[] statements = line.split("p");
      Stack<String> stack = new Stack<String>();
      for (String statement : statements)
      {
          statement = statement.strip();
                
          String regex = "[^/]+";

          // A list to hold the matched substrings
          List<String> results = new ArrayList<>();

          // Compile the pattern and create a matcher
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(statement);

          // Loop through all matches found in the input string
          while (matcher.find()) 
          {
            // Add the matched group (the substring) to our list
            results.add(matcher.group());
          }
        for (String s : results)
		{
			System.out.println(s);
        	stack.push(s);
		}

	  	char[] commands;
		if (stack.size() > 1) {
			commands = stack.pop().replaceAll("\\s", "").toCharArray();	
      		for (int i = 0; i < commands.length; i++)
	  		{
				if (stack.peek() == "i")
				{
					String trash = stack.pop();
					stack.push(Interpreter.input());
				}
				if (commands[i] == 'r')
				{
					String str = stack.pop();
					str = Interpreter.reverse(str);
					stack.push(str);
				}
				else if (commands[i] == 's')
				{
					if (commands[i+1] != 's')
					{
						//System.out.print("HERE2");
						System.exit(7);
					}
					i++;
					String str1 = stack.pop();
					String str2 = stack.pop();
					stack.push(str2 + str1);
				}
				else if (commands[i] == 'i')
				{
					stack.push(Interpreter.input());
				}
				else if (commands[i] == ' ')
						continue;
				else
					System.exit(7);
          	}
		}
       }
		System.out.println(stack.pop());
      }
  }

public static void main(String[] args)
  {

    String filename = args[0];

    StringBuilder content = new StringBuilder();

    ArrayList<String> lines = new ArrayList<String>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                //System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
    Interpreter.parser(lines);
  }
}
