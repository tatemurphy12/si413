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
            System.out.println(b);
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
          Scanner sc = new Scanner(System.in);
          String input = sc.nextLine();
          return input;
        }

        @Override
        public String visitStringLit(ParseRules.StringLitContext ctx) {
          return ctx.STR_LIT().getText();
        }

        @Override
        public String visitStrID(ParseRules.StrIDContext ctx) {
          String key = ctx.ID().getText();
          return strDict.get(key);
        }

    }

    private class BoolExpressionVisitor extends ParseRulesBaseVisitor<Boolean> {
        @Override
        public Boolean visitBoolPar(ParseRules.BoolParContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitAndOr(ParseRules.AndOrContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitNot(ParseRules.NotContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitStringContain(ParseRules.StringContainContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitStringCompare(ParseRules.StringCompareContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitBoolLit(ParseRules.BoolLitContext ctx) {
            return new Boolean(true);
        }

        @Override
        public Boolean visitBoolID(ParseRules.BoolIDContext ctx) {
            return new Boolean(true);
        }

    }

    private Map<String, String> strDict = new HashMap<>();
    private Map<String, Boolean> boolDict = new HashMap<>();

    private Scanner stdin = new Scanner(System.in);
    private StatementVisitor svisitor = new StatementVisitor();
    private BoolExpressionVisitor boolVisitor = new BoolExpressionVisitor();
    private StringExpressionVisitor strVisitor = new StringExpressionVisitor();
    private Tokenizer tokenizer;

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
