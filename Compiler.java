import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler
{
  public static ArrayList<String> literals = new ArrayList<String>();

  public static void preamble(PrintWriter p)
  {
	  p.println("target triple = \"x86_64-pc-linux-gnu\"\n");
	  p.println("%struct._IO_FILE = type { i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, %struct._IO_marker*, %struct._IO_FILE*, i32, i32, i64, i16, i8, [1 x i8], i8*, i64, %struct._IO_codecvt*, %struct._IO_wide_data*, %struct._IO_FILE*, i8*, i64, i32, [20 x i8] }\n"+
			"%struct._IO_marker = type opaque\n"+
			"%struct._IO_codecvt = type opaque\n"+
			"%struct._IO_wide_data = type opaque\n"+
			"@stdin = external global %struct._IO_FILE*, align 8\n"+
			"@.str = private unnamed_addr constant [2 x i8] c\"\\0A\\00\", align 1");

	  p.println("define dso_local i8* @input(i8* noundef %0, i32 noundef %1) #0 {\n" + "\t%3 = alloca i8*, align 8\n"+
  			"\t%4 = alloca i8*, align 8\n"+
  			"\t%5 = alloca i32, align 4\n"+
  			"\tstore i8* %0, i8** %4, align 8\n"+
  			"\tstore i32 %1, i32* %5, align 4\n"+
  			"\t%6 = load i8*, i8** %4, align 8\n"+
  			"\t%7 = load i32, i32* %5, align 4\n"+
  			"\t%8 = load %struct._IO_FILE*, %struct._IO_FILE** @stdin, align 8\n"+
  			"\t%9 = call i8* @fgets(i8* noundef %6, i32 noundef %7, %struct._IO_FILE* noundef %8)\n"+
  			"\t%10 = icmp eq i8* %9, null\n"+
  			"\tbr i1 %10, label %11, label %12\n"+

			"\t11:                                               ; preds = %2\n"+
  				"\t\tstore i8* null, i8** %3, align 8\n"+
  				"\t\tbr label %18\n"+
			"\t12:                                               ; preds = %2\n"+
  				"\t\t%13 = load i8*, i8** %4, align 8\n"+
  				"\t\t%14 = load i8*, i8** %4, align 8\n"+
  				"\t\t%15 = call i64 @strcspn(i8* noundef %14, i8* noundef getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i64 0, i64 0)) #4\n"+
  				"\t\t%16 = getelementptr inbounds i8, i8* %13, i64 %15\n"+
  				"\t\tstore i8 0, i8* %16, align 1\n"+
  				"\t\t%17 = load i8*, i8** %4, align 8\n"+
  				"\t\tstore i8* %17, i8** %3, align 8\n"+
  				"\t\tbr label %18\n"+
			"\t18:                                               ; preds = %12, %11\n"+
  				"\t\t%19 = load i8*, i8** %3, align 8\n"+
  				"\t\tret i8* %19\n"+
				"}\n");

	  //ask sir abt the unspecified pointer error
	  p.println("declare i32 @puts(i32 noundef) #1\n");
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

  public static void printString(PrintWriter p, String str)
  {
	Compiler.literals.add(str.replaceAll("^/|/$", ""));
	p.println("\tcall i32 @puts(ptr @lit" + Integer.toString(Compiler.literals.size() -1) + ")");
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
	ArrayList<String> literals = new ArrayList<String>();
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
				//list.set(i, Interp.escapeSeq(Interp.input(sin)));
				//;

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
				System.exit(7);
			}
		}
		Compiler.printString(writer, list.get(0));
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
