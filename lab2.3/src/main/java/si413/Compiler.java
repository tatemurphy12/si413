package si413;

import java.nio.file.Path;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

//idea:
//	let's try storing variables in name, value pairs in a Map
//	Will have to also store literals, maybe in a separate array
//	for ease of printing at the end. For local vars only, we can just
//	pass up the ptr


public class Compiler {
    private class StmtVisitor extends ParseRulesBaseVisitor<Void> {
        // TODO your visit methods for statements here!
@Override
        public Void visitRegularProg(ParseRules.RegularProgContext ctx) {
            visit(ctx.stmt());
            visit(ctx.prog());
            return null;
        }

        @Override
        public Void visitEmptyProg(ParseRules.EmptyProgContext ctx)
        {
          return null;
        }

        @Override
        public Void visitAssignStrStmt(ParseRules.AssignStrStmtContext ctx) {
			String val = strvisitor.visit(ctx.str_expr());
			String name = ctx.ID().getText();
            vars.put(name, val);
            return null;

        }

        @Override
        public Void visitAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx) {
            String val = boolvisitor.visit(ctx.bool_expr());
			String name = ctx.ID().getText();
            vars.put(name, val);
            return null;
        }

        @Override
        public Void visitPrintStrStmt(ParseRules.PrintStrStmtContext ctx) {
            String str = strvisitor.visit(ctx.str_expr());
            dest.println("call i32 @puts(i8* noundef " + str+ ")");
            return null;
        }

        @Override
        public Void visitPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx) {
            String b = boolvisitor.visit(ctx.bool_expr());
			dest.println("call void @print_aye_or_nay(ptr " + b + ")");
            return null;
        }
		
    }
	
    private class StringExpressionVisitor extends ParseRulesBaseVisitor<String> {
        @Override
        public String visitStringPar(ParseRules.StringParContext ctx) {
          return visit(ctx.str_expr());
        }

        @Override
        public String visitConcat(ParseRules.ConcatContext ctx) {
          String s1 = visit(ctx.str_expr(0));
          String s2 = visit(ctx.str_expr(1));
		  dest.println("%strPtr" + Integer.toString(numStrs) + " = call i8* @concatenate(i8* noundef " + s1 + ", i8* noundef " + s2 +")");
		  numStrs++;
		  return "%strPtr"+Integer.toString(numStrs-1);
 
        }

        @Override
        public String visitReverse(ParseRules.ReverseContext ctx) {
        	String str = visit(ctx.str_expr());
			dest.println("%strPtr" + Integer.toString(numStrs) + " = call i8* @reverse(i8* noundef "+ str +")");
 			numStrs++;
			return "%strPtr"+Integer.toString(numStrs-1);
        }

        @Override
        public String visitInput(ParseRules.InputContext ctx) {
			dest.println("%str" + Integer.toString(numStrs) + " = alloca [256 x i8], align 16");
            dest.println("%strPtr" + Integer.toString(numStrs) + " = getelementptr inbounds [256 x i8], [256 x i8]* %str" + Integer.toString(numStrs) + ", i64 0, i64 0");
            dest.println("call i8* @input(i8* noundef %strPtr" + Integer.toString(numStrs)+ ", i32 noundef 256)");
			numStrs++;
          	return "%strPtr"+Integer.toString(numStrs-1);
        }

        @Override
        public String visitStringLit(ParseRules.StringLitContext ctx) {
          	String str = ctx.STR_LIT().getText();
	  		String strBetter = str.replaceAll("(~\\.*~)(.*?)(\\1)", "$2");
			literals.add(strBetter);
            int size = strBetter.length()+1;
            dest.println("%strPtr" + Integer.toString(numStrs) + " = getelementptr inbounds ["+ size + " x i8], [" + size+ " x i8]* @lit" + (literals.size() - 1) + ", i64 0, i64 0");
			numStrs++;
	  		return "%strPtr" + Integer.toString(numStrs - 1);
        }

        @Override
        public String visitStrID(ParseRules.StrIDContext ctx) {
          	String key = ctx.ID().getText();
          	String str = vars.get(key);
	  		if (str == null)
		  		Errors.error("var not defined");
	  		return str;
        }

    }

    private class BoolExpressionVisitor extends ParseRulesBaseVisitor<String> {
        @Override
        public String visitBoolPar(ParseRules.BoolParContext ctx) {
            return visit(ctx.bool_expr());
        }

        @Override
        public String visitAndOr(ParseRules.AndOrContext ctx) {
			String op = ctx.LOGIC_OP().getText();
			String b1, b2;
			b1 = visit(ctx.bool_expr(0));
			b2 = visit(ctx.bool_expr(1));
			dest.println(b1 + Integer.toString(numBools) + "LD" + "= load i1, ptr " + b1 + ", align 1");
			dest.println(b2 + Integer.toString(numBools)+ "LD" + "= load i1, ptr " + b2 + ", align 1");
			dest.println("%boolPtr" + Integer.toString(numBools) + "= alloca i1, align 1");
			if (op.equals("Chantey"))
			{
				dest.println("%boolPtr" + Integer.toString(numBools) + "LD" + " = and i1 " + b1 + Integer.toString(numBools) + "LD, " + b2 + Integer.toString(numBools) + "LD");
			}
			else
			{
				dest.println("%boolPtr" + Integer.toString(numBools) + "LD" + " = or i1 " + b1 + Integer.toString(numBools) + "LD, " + b2 + Integer.toString(numBools) + "LD");
			}
			dest.println("store i1 %boolPtr" + Integer.toString(numBools) + "LD, ptr %boolPtr" + Integer.toString(numBools) + ", align 1");
			numBools++;
			return "%boolPtr"+Integer.toString(numBools-1);
        }

        @Override
        public String visitNot(ParseRules.NotContext ctx) {
         	String b = visit(ctx.bool_expr());
			dest.println(b + Integer.toString(numBools) + "LD = load i1, ptr " + b + ", align 1");
			dest.println("%boolPtr" + Integer.toString(numBools) + "LD = xor i1 " + b + Integer.toString(numBools) + "LD, 1");
			dest.println("%boolPtr" + Integer.toString(numBools) + "= alloca i1, align 1");
			dest.println("store i1 %boolPtr" + Integer.toString(numBools) + "LD, ptr %boolPtr" + Integer.toString(numBools) + ", align 1");
			numBools++;
			return "%boolPtr"+Integer.toString(numBools-1);
        }

        @Override
        public String visitStringContain(ParseRules.StringContainContext ctx) {
			String s1 = strvisitor.visit(ctx.str_expr(0));
			String s2 = strvisitor.visit(ctx.str_expr(1));
			dest.println("%conRes" + Integer.toString(numBools) + " = call i32 @string_contains(i8* noundef " + s1 + ", i8* noundef " + s2 + ")");
			dest.println("%boolPtr" + Integer.toString(numBools) + "  = alloca i1, align 1");
			dest.println("%boolPtr" + Integer.toString(numBools) + "LD = icmp ne i32 %conRes" + Integer.toString(numBools) + ", 0");
			dest.println("store i1 %boolPtr" + Integer.toString(numBools) + "LD, ptr %boolPtr" + Integer.toString(numBools) + ", align 1");
			numBools++;	
			return "%boolPtr"+Integer.toString(numBools-1);
        }

        @Override
        public String visitStringCompare(ParseRules.StringCompareContext ctx) {
			String op = ctx.STR_CMP().getText();
			String s1 = strvisitor.visit(ctx.str_expr(0));
			String s2 = strvisitor.visit(ctx.str_expr(1));
			dest.println("%comRes" + Integer.toString(numBools) + " = call i32 @string_compare(i8* noundef " + s1 + ", i8* noundef " + s2 + ")");
			numBools++;
			dest.println("%boolPtr" + Integer.toString(numBools) + " = alloca i1, align 1");
			if (op.equals("Cog"))
				dest.println("%boolPtr" + Integer.toString(numBools) + "LD = icmp eq i32 %comRes" + Integer.toString(numBools-1) + ", 0");
			else
				dest.println("%boolPtr" + Integer.toString(numBools) + "LD = icmp slt i32 %comRes" + Integer.toString(numBools-1) + ", 0");
			dest.println("store i1 %boolPtr" + Integer.toString(numBools) + "LD, ptr %boolPtr" + Integer.toString(numBools) + ", align 1");
			numBools++;
			return "%boolPtr"+Integer.toString(numBools-1);
        }

        @Override
        public String visitBoolLit(ParseRules.BoolLitContext ctx) {
			String burger = ctx.BOOL_LIT().getText();
			int val;
			if (burger.equals("Aye"))
				val = 1;
			else
            	val = 0;
			dest.println("%boolPtr" + Integer.toString(numBools) + " = alloca i1, align 1");
			dest.println("store i1 " + val + ", ptr %boolPtr" + Integer.toString(numBools) + ", align 1");
			numBools++;
			return "%boolPtr"+Integer.toString(numBools-1);
        }

        @Override
        public String visitBoolID(ParseRules.BoolIDContext ctx) {
			String key = ctx.ID().getText();
			String val = vars.get(key);
			if (val == null)
				Errors.error("var not defined");
        	return val;
        }

    }

    private StmtVisitor svisitor = new StmtVisitor();
    private StringExpressionVisitor strvisitor = new StringExpressionVisitor();
    private BoolExpressionVisitor boolvisitor = new BoolExpressionVisitor();
    private PrintWriter dest;
	private int numStrs = 0;
	private int numBools = 0;
	private Map<String, String> vars = new HashMap<>();
	private ArrayList<String> literals = new ArrayList<String>();	

    public Compiler(Path destFile) throws IOException {
        dest = new PrintWriter(destFile.toFile());
    }

    public void compile(ParseTree ptree) throws IOException {
        // copy contents of preamble.ll in the resources directory
        try (BufferedReader preamble = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("preamble.ll"))))
        {
            while (true) {
                String line = preamble.readLine();
                if (line == null) break;
                dest.println(line);
            }
        }
		//dest.println("declare i32 @puts(i8*)");
        dest.println("define i32 @main() {");

        // this calls all of your visit methods to walk the parse tree
        // note that the code emitted goes inside main()
        svisitor.visit(ptree);

        dest.println("  ret i32 0");
        dest.println("}\n");
	  	for (int i = 0; i < literals.size(); i++)
	  	{
			dest.print("@lit" + Integer.toString(i) + "= constant [");
			dest.print(literals.get(i).length() + 1 + " x i8] c");
			dest.println("\"" + literals.get(i) + "\\00\"");
	  	}

        dest.close();
    }

    public static TokenStream getTokens(Path sourceFile) throws IOException {
        return new Tokenizer(
            Compiler.class.getResourceAsStream("tokenSpec.txt"),
            ParseRules.VOCABULARY
        ).streamFrom(sourceFile);
    }

    public static ParseTree parse(TokenStream tokens) throws IOException {
        ParseRules parser = new ParseRules(tokens);
        Errors.register(parser);
        return parser.prog();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            Errors.error("need 2 command-line args: source_file dest_file");
        }
        Path sourceFile = Path.of(args[0]);
        Path destFile = Path.of(args[1]);
        TokenStream tokens = getTokens(sourceFile);
        ParseTree ptree = parse(tokens);
        new Compiler(destFile).compile(ptree);
    }
}
