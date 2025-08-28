import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Interpreter 
{
  public static void reversal(String str)
  {
  }

  public static void concatenation(String str1, String str2)
  {
  }

  public static String input()
  {
    return "";
  }

  public static void parser(ArrayList<String> lines)
  {
    for (String line : lines)
    {
      //seperate the line by print statements
      String[] statements = line.split("p");
      for (String statement : statements)
      {
        //System.out.println(statement);
        //separate the statements into literals and commands
        String[] strings = statement.split("/");
        String commandString = strings[strings.length-1].strip();
        char[] commands = commandString.toCharArray();
        ArrayList<String> literals = new ArrayList<String>();
        for (int i = 0; i < strings.length-1; i++)
        {
          literals.add(strings[i]);
          System.out.print(strings[i]);
        }
        //now we have a char array for iterating through commands and an
        //ArrayList of String literals to apply the commands to 
        for (int j = 0; j < commands.length; j++)
        {
          //System.out.println(commands[j]);
        }
        //System.out.print(literals.get(1));
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
