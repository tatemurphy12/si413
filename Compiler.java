import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler
{
  public static ArrayList<String> literals = new ArrayList<String>();

  public static void preamble(PrintWriter p)
  {
	  ArrayList<String> lines = new ArrayList<>();
          String filePath = "helpers.ll"; // Make sure this file exists in the same directory as your program
          File file = new File(filePath);
          try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
              lines.add(scanner.nextLine());
            }
            scanner.close(); // It's good practice to close the scanner when you're done
          } 
          catch (FileNotFoundException e) {
              System.out.println("File not found: " + e.getMessage());
          }
          for (String line : lines)
          {
            p.println(line);
          }
	  //ask sir abt the unspecified pointer error
	  p.println("declare i32 @puts(i8* noundef) #1\n");
	  p.println("define i32 @main() {");
  }

  public static void closer(PrintWriter p)
  {
	  p.println("\tret i32 0");
	  p.println("}\n");
	  for (int i = 0; i < Compiler.literals.size(); i++)
	  {
		  p.print("@lit" + Integer.toString(i) + "= constant [");
		  p.print(Compiler.literals.get(i).length() + 1 + " x i8] c");
		  p.println("\"" + Compiler.literals.get(i) + "\\00\"");
	  }
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
				  if (chars[i] == 'i' || chars[i] == 'r' || chars[i] == 'p')
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

  public static void printString(PrintWriter p, String strPtr)
  {
	p.println("\tcall i32 @puts(i8* noundef %" + strPtr+ ")");
  }

  public static void parser(ArrayList<String> lines, PrintWriter p)
  {
	int localNum = 0;
    for (String line : lines)
    {
	  line = Compiler.commentParser(line).strip();
	  if (line.length() == 0)
		continue;
	  
	  //System.out.println(line);
      //seperate the line by print statements
      		//System.out.println(statement);
      //ArrayList<String> literals = new ArrayList<String>();
      ArrayList<String> list = Compiler.makeList(line);
      ArrayList<String> uniqueStrings = new ArrayList<String>();
		//for (String l : list)
		//	System.out.println(l);
      for (int i = 0; i < list.size(); i++)
      {
			//System.out.println("String" + list.get(i));
			if(Compiler.isString(list.get(i)))
			{
                Compiler.literals.add(list.get(i).replaceAll("^/|/$", ""));
                int size = list.get(i).length()+1;
                p.println("\t%strPtr" + Integer.toString(localNum) + " = getelementptr inbounds ["+ size + " x i8], [" + size+ " x i8]* @lit" + (Compiler.literals.size() - 1) + ", i64 0, i64 0");
			    uniqueStrings.add("%strPtr" + Integer.toString(localNum));
                localNum++;
			}
			else if (list.get(i).equals("i"))
			{
            	p.println("\t%str" + Integer.toString(localNum) + " = alloca [256 x i8], align 16");
                p.println("\t%strPtr" + Integer.toString(localNum) + " = getelementptr inbounds [256 x i8], [256 x i8]* %str" + Integer.toString(localNum) + ", i64 0, i64 0");
                p.println("\tcall i8* @input(i8* noundef %strPtr" + Integer.toString(localNum)+ ", i32 noundef 256)");
			   	uniqueStrings.add("%strPtr" + Integer.toString(localNum));
                localNum++;
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
					int size = uniqueStrings.size();
					String str1 = uniqueStrings.get(size-1);
					String str2 = uniqueStrings.get(size-2);
                    p.println("\t%strPtr" + Integer.toString(localNum) + " = call i8* @concatenate(i8* noundef " + str2 + ", i8* noundef " + str1 +")");
					uniqueStrings.add("%strPtr" + Integer.toString(localNum));
					uniqueStrings.remove(str1);
					uniqueStrings.remove(str2);
                    localNum++;
				}

			}
			else if (list.get(i).equals("r"))
			{
            	p.println("\t%strPtr" + Integer.toString(localNum) + " = call i8* @reverse(i8* noundef "+ uniqueStrings.get(uniqueStrings.size()-1) +")");
				uniqueStrings.remove(uniqueStrings.size()-1);
				uniqueStrings.add("%strPtr" + Integer.toString(localNum));
				localNum++;
			}
            else if (list.get(i).equals("p"))
            {
            	Compiler.printString(p, "strPtr" + Integer.toString(localNum-1));
            }
			else
			{
			    System.exit(7);
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
    	Compiler.parser(lines, writer);
	Compiler.closer(writer);
	writer.close();
  }
}
