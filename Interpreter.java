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

  public static void parser(ArrayList<String> lines)
  {
    for (String line : lines)
    {
	  line = Interpreter.commentParser(line);
	  if (line.length() == 0)
		continue;
	  System.out.println(line);
      //seperate the line by print statements
      String[] statements = line.split("p");
      for (String statement : statements)
      {
        //System.out.println(statement);
        //separate the statements into literals and commands
        String[] strings = statement.split("/");
        String commandString = strings[strings.length-1].strip();
        char[] commands = commandString.toCharArray();
        Stack<String> literals = new Stack<String>();
        for (int i = 1; i < strings.length-1; i++)
        {
			//System.out.println(strings[i]);
        	literals.push(strings[i]);
        }
        //now we have a char array for iterating through commands and a
        //Stack of String literals to apply the commands to 
        for (int j = 0; j < commands.length; j++)
        {
			if (commands[j] == 'r')
			{
				String str = literals.pop();
				str = Interpreter.reverse(str);
				literals.push(str);
			}
			else if (commands[j] == 's')
			{
				if (commands[j+1] != 's')
					System.exit(7);
				j++;
				String str1 = literals.pop();
				String str2 = literals.pop();
				literals.push(str2+str1);
			}
			else if (commands[j] == 'i')
			{
				Scanner sin = new Scanner(System.in);
				literals.push(sin.next());
			}
			else
			{
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
