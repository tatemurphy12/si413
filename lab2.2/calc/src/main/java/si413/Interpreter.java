package si413;

import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.antlr.v4.runtime.TokenStream;

/** Interpreter for basic calculator language.
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
        public Void visitPrintStmt(ParseRules.PrintStmtContext ctx) {
            int value = evisitor.visit(ctx.expr());
            System.out.println(value);
            return null;
        }

        @Override
        public Void visitSaveStmt(ParseRules.SaveStmtContext ctx) {
            savedValue = evisitor.visit(ctx.expr());
            return null;
        }
    }

    /** Methods in this class will execute expressions and return the result.
     */
    private class ExpressionVisitor extends ParseRulesBaseVisitor<Integer> {
        @Override
        public Integer visitLiteralExpr(ParseRules.LiteralExprContext ctx) {
            return Integer.valueOf(ctx.INT().getText());
        }

        @Override
        public Integer visitVarExpr(ParseRules.VarExprContext ctx) {
            if (savedValue == null)
                return Errors.error("x is referenced before being saved");
            else return savedValue;
        }

        @Override
        public Integer visitSignExpr(ParseRules.SignExprContext ctx) {
            int rhs = visit(ctx.expr());
            if (ctx.ADDOP().getText().equals("-")) return -rhs;
            else return rhs;
        }

        @Override
        public Integer visitMulExpr(ParseRules.MulExprContext ctx) {
            int lhs = visit(ctx.expr(0));
            int rhs = visit(ctx.expr(1));
            if (ctx.MULOP().getText().equals("*")) return lhs * rhs;
            else {
                if (rhs == 0) return Errors.error("divide by zero");
                else return lhs / rhs;
            }
        }

        @Override
        public Integer visitAddExpr(ParseRules.AddExprContext ctx) {
            int lhs = visit(ctx.expr(0));
            int rhs = visit(ctx.expr(1));
            if (ctx.ADDOP().getText().equals("+")) return lhs + rhs;
            else return lhs - rhs;
        }
    }

    private Integer savedValue = null;
    private Scanner stdin = new Scanner(System.in);
    private StatementVisitor svisitor = new StatementVisitor();
    private ExpressionVisitor evisitor = new ExpressionVisitor();
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
