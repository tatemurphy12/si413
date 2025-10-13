import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interp 
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

  public static String input(Scanner sin)
  {
		String str;
		if (sin.hasNext())
			 str = "/" + Interp.escapeSeq(sin.next()) + "/";
		else
		{
			str = "";
			//System.out.println("HERE");
			System.exit(7);
		}
		return str;
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
				//System.out.println("bad 87");
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
					  //System.out.println("bad 124");
					  System.exit(7);
				  }
					  
			  }
		  }
		  
	  }
	  return list;
  }

  public static void parser(ArrayList<String> lines)
  {
    Scanner sin = new Scanner(System.in);
    for (String line : lines)
    {
	  line = Interp.commentParser(line).strip();
	  if (line.length() == 0)
		continue;
	  
	  //System.out.println(line);
      //seperate the line by print statements
      String[] statements = line.split("p");
      for (String statement : statements)
      {
		//System.out.println(statement);
    	ArrayList<String> list = Interp.makeList(statement);
		//for (String l : list)
		//	System.out.println(l);
		for (int i = 0; i < list.size(); i++)
		{
			//System.out.println("String" + list.get(i));
			if(Interp.isString(list.get(i)))
			{
				continue;
			}
			else if (list.get(i).equals("i"))
			{
				list.set(i, Interp.escapeSeq(Interp.input(sin)));
			}
			else if (list.get(i).equals("ss"))
			{
				if (i < 2)
				{
					//System.out.println("bad 165");
					System.exit(7);
				}
				else
				{
					list.set(i, list.get(i-2).replaceAll("/$", "") + list.get(i-1).replaceAll("^/|/$", ""));
					list.remove(i-1);
					list.remove(i-2);
					i = i - 2;
				}
			}
			else if (list.get(i).equals("r"))
			{
				String reversed = Interp.reverse(list.get(i-1));
				list.set(i-1, reversed);
				list.remove(i);
				i--;
			}
			else
			{
				//System.out.println("bad 182");
				System.exit(7);
			}
		}
		System.out.println(list.get(0).replaceAll("^/|/$", ""));
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
            //System.out.println("Error reading file: " + e.getMessage());
            System.exit(7);
        }
    Interp.parser(lines);
  }
}
