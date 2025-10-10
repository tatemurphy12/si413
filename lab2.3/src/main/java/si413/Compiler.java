package si413;

import java.nio.file.Path;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {
    private class StmtVisitor extends ParseRulesBaseVisitor<Void> {
        // TODO your visit methods for statements here!
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
        }

        @Override
        public String visitReverse(ParseRules.ReverseContext ctx) {
          String str = visit(ctx.str_expr());
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
		boolean b1, b2;
		if (op.equals("Chantey"))
		{
			b1 = visit(ctx.bool_expr(0));
			b2 = visit(ctx.bool_expr(1));

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

    private StmtVisitor svisitor = new StmtVisitor();
    private StringExpressionVisitor strvisitor = new StringExpressionVisitor();
    private BoolExpressionVisitor boolvisitor = new BoolExpressionVisitor();
    private PrintWriter dest;


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

        dest.println("define i32 @main() {");

        // this calls all of your visit methods to walk the parse tree
        // note that the code emitted goes inside main()
        svisitor.visit(ptree);

        dest.println("  ret i32 0");
        dest.println("}");

        // TODO you probably want to put the string literal definitions
        // down here. They can't be directly emitted from the visit methods
        // because they have to be outside of main().

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
