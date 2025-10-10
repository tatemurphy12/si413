package si413;

import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import org.antlr.v4.runtime.TokenStream;

/** Interpreter for ARRR.
 * The tokens and grammar come from src/main/resource/si413/tokenSpec.txt
 * and src/main/antlr4/si413/ParseRules.g4 respectively.
 */
public class Interpreter {

    /** Methods in this class will execute statements.
     * Return type is Void because statements do not return anything.
     * Note that this is Void and not void, so we still have to return null
     * in each function. (This is a consequence of Java generics.)
     */
    private class StatementVisitor extends ParseRulesBaseVisitor<Void> {
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
            String str = strVisitor.visit(ctx.str_expr());
            strDict.put(ctx.ID().getText(), str);
            return null;
        }

        @Override
        public Void visitAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx) {
            Boolean b = boolVisitor.visit(ctx.bool_expr());
            boolDict.put(ctx.ID().getText(), b);
            return null;
        }

        @Override
        public Void visitPrintStrStmt(ParseRules.PrintStrStmtContext ctx) {
            String str = strVisitor.visit(ctx.str_expr());
            System.out.println(str);
            return null;
        }

        @Override
        public Void visitPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx) {
            Boolean b = boolVisitor.visit(ctx.bool_expr());
            String str = b ? "Aye" : "Nay";
	    System.out.println(str);
            return null;
        }

    }

    /** Methods in this class will execute expressions and return the result.
     */
    private class StringExpressionVisitor extends ParseRulesBaseVisitor<String> {
        @Override
        public String visitStringPar(ParseRules.StringParContext ctx) {
          return visit(ctx.str_expr());
        }

        @Override
        public String visitConcat(ParseRules.ConcatContext ctx) {
          String s1 = visit(ctx.str_expr(0));
          String s2 = visit(ctx.str_expr(1));
          return s1 + s2;
        }

        @Override
        public String visitReverse(ParseRules.ReverseContext ctx) {
          String str = visit(ctx.str_expr());
          StringBuffer sb = new StringBuffer(str);
          sb.reverse();
          return sb.toString();
        }

        @Override
        public String visitInput(ParseRules.InputContext ctx) {
		String input;
          if (sc.hasNextLine())
		  input = sc.nextLine();
	  else
		  input = "";
          return input;
        }

        @Override
        public String visitStringLit(ParseRules.StringLitContext ctx) {
          String str = ctx.STR_LIT().getText();
	  String strBetter = str.replaceAll("(~\\.*~)(.*?)(\\1)", "$2");
	  return strBetter;
        }

        @Override
        public String visitStrID(ParseRules.StrIDContext ctx) {
          String key = ctx.ID().getText();
          String str = strDict.get(key);
	  if (str == null)
		  Errors.error("var not defined");
	  return str;
        }

    }

    private class BoolExpressionVisitor extends ParseRulesBaseVisitor<Boolean> {
        @Override
        public Boolean visitBoolPar(ParseRules.BoolParContext ctx) {
            return visit(ctx.bool_expr());
        }

        @Override
        public Boolean visitAndOr(ParseRules.AndOrContext ctx) {
		String op = ctx.LOGIC_OP().getText();
		if (op.equals("Chantey"))
		{
			return new Boolean(visit(ctx.bool_expr(0)) && visit(ctx.bool_expr(1)));
		}
		else
		{
			return new Boolean(visit(ctx.bool_expr(0)) || visit(ctx.bool_expr(1)));
		}
        }

        @Override
        public Boolean visitNot(ParseRules.NotContext ctx) {
            return new Boolean(!visit(ctx.bool_expr()));
        }

        @Override
        public Boolean visitStringContain(ParseRules.StringContainContext ctx) {
		String s1 = strVisitor.visit(ctx.str_expr(0));
		String s2 = strVisitor.visit(ctx.str_expr(1));
		boolean b = s1.contains(s2);
		return new Boolean(b);
        }

        @Override
        public Boolean visitStringCompare(ParseRules.StringCompareContext ctx) {
		String op = ctx.STR_CMP().getText();
		String s1 = strVisitor.visit(ctx.str_expr(0));
		String s2 = strVisitor.visit(ctx.str_expr(1));
		boolean b;
		if (op.equals("Cog"))
			b = s1.compareTo(s2) == 0;
		else
			b = s1.compareTo(s2) < 0;
		return new Boolean(b);
        }

        @Override
        public Boolean visitBoolLit(ParseRules.BoolLitContext ctx) {
		String burger = ctx.BOOL_LIT().getText();
		if (burger.equals("Aye"))
			return new Boolean(true);
		else
            		return new Boolean(false);
        }

        @Override
        public Boolean visitBoolID(ParseRules.BoolIDContext ctx) {
		String key = ctx.ID().getText();
		boolean burgers = boolDict.get(key);
		if (new Boolean(burgers) == null)
			Errors.error("var not defined");
          	return new Boolean(burgers);
        }

    }

    private Map<String, String> strDict = new HashMap<>();
    private Map<String, Boolean> boolDict = new HashMap<>();

    private Scanner stdin = new Scanner(System.in);
    private StatementVisitor svisitor = new StatementVisitor();
    private BoolExpressionVisitor boolVisitor = new BoolExpressionVisitor();
    private StringExpressionVisitor strVisitor = new StringExpressionVisitor();
    private Tokenizer tokenizer;
    private Scanner sc = new Scanner(System.in);

    public Interpreter() throws IOException {
        this.tokenizer = new Tokenizer(
            getClass().getResourceAsStream("tokenSpec.txt"),
            ParseRules.VOCABULARY
        );
    }

    public ParseRules.ProgContext parse(Path sourceFile) throws IOException {
        TokenStream tokenStream = tokenizer.streamFrom(sourceFile);
        ParseRules parser = new ParseRules(tokenStream);
        Errors.register(parser);
        return parser.prog();
    }

    public void execute(ParseRules.ProgContext parseTree) {
        // to execute the whole program, we just call visit() on the  root
        // node of the parse tree!
        svisitor.visit(parseTree);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            Errors.error("need 1 command-line arg: input source file");
        }
        Path sourceFile = Path.of(args[0]);
        Interpreter interp = new Interpreter();
        ParseRules.ProgContext parseTree = interp.parse(sourceFile);
        interp.execute(parseTree);
    }
}
