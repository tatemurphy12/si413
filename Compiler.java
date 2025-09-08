import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler
{
  public static void preamble(PrintWriter p)
  {
	  p.println("target triple = \"x86_64-pc-linux-gnu\"\n");
	  //ask sir abt the unspecified pointer error
	  p.println("declare i32 @puts(i32 noundef) #1\n");
	  p.println("define i32 @main() {");
  }
  public static void closer(PrintWriter p)
  {
	  p.println("\tret i32 0");
	  p.println("}");
  }
  public static String commentParser(String line)
  {
	String regex = "~.*?~|~.*$";
	String lineNoComm = line.replaceAll(regex, "");
	return lineNoComm;	
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

  public static boolean isString(String s)
  {
	  return s.startsWith("/") && s.endsWith("/");
  }

  public static ArrayList<String> makeList(String statement)
  {
	  ArrayList<String> list = new ArrayList<String>();
	  char[] chars = statement.toCharArray();
	  StringBuilder sb = new StringBuilder();
	  boolean literal = false;
	  for (int i = 0; i < chars.length; i++)
	  {
		  //System.out.println(i);
		  if (chars[i] == ' ')
		  {
			  if (!literal)
			  	continue;
			  else
				  sb.append(chars[i]);
		  }
		  else if (chars[i] == '\\')
		  {
			  if (literal)
			  {
				  sb.append(chars[i+1]);
				  i++;
			  }
			  else
			  {
				System.out.println("bad 87");
				System.exit(7);
			  }
		  }	
		  else if (chars[i] == '/')
		  {
			  if (literal)
			  {
				  literal = false;
				  sb.append(chars[i]);
				  list.add(sb.toString());
				  sb.setLength(0);
			  }
			  else
			  {
				  literal = true;
				  sb.append(chars[i]);
			  }
		  }
		  else
		  {
			  if (literal)
			  {
				  sb.append(chars[i]);
			  }
			  else
			  {
				  if (chars[i] == 'i' || chars[i] == 'r')
				  {
					  list.add(String.valueOf(chars[i]));
				  }
				  else if (chars[i] == 's' && chars[i+1] == 's')
				  {
					  list.add("ss");
					  i++;
				  }
				  else
				  {
					  System.out.println("bad 124");
					  System.exit(7);
				  }
					  
			  }
		  }
		  
	  }
	  return list;
  }

  public static void parser(ArrayList<String> lines, PrintWriter writer)
  {
    for (String line : lines)
    {
	  line = Compiler.commentParser(line).strip();
	  if (line.length() == 0)
		continue;
	  
	  //System.out.println(line);
      //seperate the line by print statements
      String[] statements = line.split("p");
      for (String statement : statements)
      {
		//System.out.println(statement);
    	ArrayList<String> list = Compiler.makeList(statement);
		//for (String l : list)
		//	System.out.println(l);
		for (int i = 0; i < list.size(); i++)
		{
			//System.out.println("String" + list.get(i));
			if(Compiler.isString(list.get(i)))
			{
				continue;
			}
			else if (list.get(i).equals("i"))
			{
				;
			}
			else if (list.get(i).equals("ss"))
			{
				;
			}
			else if (list.get(i).equals("r"))
			{
				;
			}
			else
			{
				System.exit(7);
			}
		}
      }
    }
  }

public static void main(String[] args) throws IOException
  {

    String filename = args[0];
    String destFile = args[1];

    StringBuilder content = new StringBuilder();
	PrintWriter writer = new PrintWriter(new FileWriter(destFile));

    ArrayList<String> lines = new ArrayList<String>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                //System.out.println(line);
            }
        } catch (IOException e) {
            System.exit(7);
        }
	Compiler.preamble(writer);
	Compiler.closer(writer);
    Compiler.parser(lines, writer);
	writer.close();
  }
}
