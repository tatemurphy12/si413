import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
	  //line = line.replaceAll("\\s", "");
	  //System.out.println(line);
	  
      //seperate the line by print statements
      String[] statements = line.split("p");
      for (String statement : statements)
      {
        //System.out.println(statement);
        //separate the statements into literals and commands
		//There are three main cases:
		//1. only an input command
		//2. one expression
		//3. two expressions
		//Because of how the split() cmd works, first element is always empty or is an input
		//Have to account for input before any string literal. Ex: i/hates//ice cream/ssssp
        String[] strings = statement.split("/");
      	Stack<String> literals = new Stack<String>();
		//for (String string : strings)
		//{
		//		System.out.println("String: " + string);
		//}
		String commandString;
		strings[0] = strings[0].replaceAll("\\s", "");
		if (strings[0].matches("i+"))
		{
			//System.out.println(strings[0]);
			for (int i = 0; i < strings[0].length(); i++)
			{
				//System.out.println("loop");
				literals.push(Interpreter.input());
			}
			strings[0] = "";
		}
		if (strings.length > 2)
		{
			commandString = strings[strings.length - 1].strip();
			for (int j = 1; j < strings.length-1; j++)
			{
					strings[j] = Interpreter.escapeSeq(strings[j]);
					literals.push(strings[j]);
			}
		}
		else if (strings.length == 2)
		{
			commandString = strings[0];
			literals.push(Interpreter.escapeSeq(strings[1]));
		}
		else
			commandString = strings[0];
		//System.out.println(commandString + "HERE");
        char[] commands = commandString.replaceAll("\\s", "").toCharArray();
        //now we have a char array for iterating through commands and a
        //Stack of String literals to apply the commands to 
        for (int j = 0; j < commands.length; j++)
        {
			//System.out.println(commands[j]);
			if (commands[j] == 'r')
			{
				String str = literals.pop();
				str = Interpreter.reverse(str);
				literals.push(str);
			}
			else if (commands[j] == 's')
			{
				if (commands[j+1] != 's')
				{
					//System.out.print("HERE2");
					System.exit(7);
				}
				j++;
				String str1 = literals.pop();
				String str2 = literals.pop();
				literals.push(str2+str1);
			}
			else if (commands[j] == 'i')
			{
					literals.push(Interpreter.input());
			}
			else
			{
				//System.out.print("HERE1");
				System.exit(7);
			}
        }
		System.out.println(literals.pop());
      }
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
