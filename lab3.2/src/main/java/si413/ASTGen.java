package si413;

import java.util.List;
import java.util.ArrayList;

/** This class is used to create the AST from a parse tree.
 * The static method ASTGen.gen(parseTree) is the specific function
 * to perform that conversion.
 */
public class ASTGen {
    /** Turns a parse tree Prog node into a complete AST.
     * This is the main external interface for the ASTGen class.
     */
    public static Stmt.Block gen(ParseRules.ProgContext ptreeRoot) {
        return new ASTGen().progVis.visit(ptreeRoot);
    }


    private class ProgVisitor extends Visitor<Stmt.Block> {
        @Override
        public Stmt.Block visitRegularProg(ParseRules.RegularProgContext ctx) {
            // recursively call visit to get the first statement and block for the rest
            Stmt first = stmtVis.visit(ctx.stmt());
            Stmt.Block rest = visit(ctx.prog());
            // combine those into a single block AST node
            List<Stmt> children = new ArrayList<>();
            children.add(first);
            children.addAll(rest.children());
            return new Stmt.Block(children);
        }

        @Override
        public Stmt.Block visitEmptyProg(ParseRules.EmptyProgContext ctx) {
            return new Stmt.Block(List.of());
        }

        @Override
        public Stmt.Block visitStatementList(ParseRules.StatementListContext ctx)
        {
            Stmt s = stmtVis.visit(ctx.stmt());
            Stmt.Block rest = visit(ctx.stmtList());
            List<Stmt> children = new ArrayList<>();
            children.add(s);
            children.addAll(rest.children());
            return new Stmt.Block(children);
        }

        @Override
        public Stmt.Block visitEmptyStmt(ParseRules.EmptyStmtContext ctx)
        {
            return new Stmt.Block(List.of());
        }

    }

    private class StmtVisitor extends Visitor<Stmt> {
        @Override
        public Stmt visitPrintStrStmt(ParseRules.PrintStrStmtContext ctx) {
            Expr<String> child = strExprVis.visit(ctx.str_expr());
            return new Stmt.PrintString(child);
        }

        @Override
        public Stmt visitPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx) {
            Expr<Boolean> child = boolExprVis.visit(ctx.bool_expr());
            return new Stmt.PrintBool(child);
        }
		@Override
		public Stmt visitAssignStrStmt(ParseRules.AssignStrStmtContext ctx)
		{
			String name = ctx.ID().getText();
			Expr<String> str = strExprVis.visit(ctx.str_expr());
			return new Stmt.AssignString(name, str);
		}
		@Override
		public Stmt visitAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx)
		{
			String name = ctx.ID().getText();
			Expr<Boolean> b = boolExprVis.visit(ctx.bool_expr());
			return new Stmt.AssignBool(name, b);
		}
		@Override
		public Stmt visitCondStmt(ParseRules.CondStmtContext ctx)
		{
			Expr<Boolean> cond = boolExprVis.visit(ctx.bool_expr());
			Stmt.Block ifBody = progVis.visit(ctx.stmtList(0));
			Stmt.Block elseBody = progVis.visit(ctx.stmtList(1));
			return new Stmt.IfElse(cond, ifBody, elseBody);
		}
		@Override
		public Stmt visitWhileLoopStmt(ParseRules.WhileLoopStmtContext ctx)
		{
			Expr<Boolean> cond = boolExprVis.visit(ctx.bool_expr());
			Stmt.Block whileBody = progVis.visit(ctx.stmtList());
			return new Stmt.While(cond, whileBody);
		}
	}


    private class StringExprVisitor extends Visitor<Expr<String>> {
        @Override
        public Expr<String> visitStringLit(ParseRules.StringLitContext ctx) {
            // extract the actual string literal
            String raw = ctx.STR_LIT().getText();
	    	String strBetter = raw.replaceAll("(~\\.*~)(.*?)(\\1)", "$2");
            return new Expr.StringLit(strBetter);
        }
		@Override
		public Expr<String> visitStrID(ParseRules.StrIDContext ctx)
		{
			String name = ctx.ID().getText();
			return new Expr.StrVar(name);
		}

		@Override
		public Expr<String> visitStringPar(ParseRules.StringParContext ctx)
		{
			return visit(ctx.str_expr());

		}

		@Override
		public Expr<String> visitInput(ParseRules.InputContext ctx)
		{
			return new Expr.Input();
		}

		@Override
		public Expr<String> visitReverse(ParseRules.ReverseContext ctx)
		{
			Expr<String> str = visit(ctx.str_expr());
			return new Expr.Reverse(str);
		}

        @Override
        public Expr<String> visitConcat(ParseRules.ConcatContext ctx) {
            Expr<String> lhs = visit(ctx.str_expr(0));
            Expr<String> rhs = visit(ctx.str_expr(1));
            return new Expr.Concat(lhs, rhs);
        }
    }


    private class BoolExprVisitor extends Visitor<Expr<Boolean>> {
        @Override
        public Expr<Boolean> visitBoolLit(ParseRules.BoolLitContext ctx) {
            return new Expr.BoolLit(ctx.BOOL_LIT().getText().equals("True"));
        }

        @Override
        public Expr<Boolean> visitNot(ParseRules.NotContext ctx) {
            Expr<Boolean> child = visit(ctx.bool_expr());
            return new Expr.Not(child);
        }

		@Override
		public Expr<Boolean> visitBoolPar(ParseRules.BoolParContext ctx)
		{
			return visit(ctx.bool_expr());
		}

		@Override
		public Expr<Boolean> visitBoolID(ParseRules.BoolIDContext ctx)
		{
			String name = ctx.ID().getText();
			return new Expr.BoolVar(name);
		}

		@Override
		public Expr<Boolean> visitStringCompare(ParseRules.StringCompareContext ctx)
		{
			String op = ctx.STR_CMP().getText();
			Expr<String> lhs = strExprVis.visit(ctx.str_expr(0));
			Expr<String> rhs = strExprVis.visit(ctx.str_expr(1));
			return new Expr.StrLess(op, lhs, rhs);
		}

		@Override
		public Expr<Boolean> visitStringContain(ParseRules.StringContainContext ctx)
		{
			Expr<String> lhs = strExprVis.visit(ctx.str_expr(0));
			Expr<String> rhs = strExprVis.visit(ctx.str_expr(1));
			return new Expr.Contains(lhs, rhs);
		}
		
		@Override
		public Expr<Boolean> visitAndOr(ParseRules.AndOrContext ctx)
		{
			String op = ctx.LOGIC_OP().getText();
			if (op.equals("Chantey"))
			{
				return new Expr.And(visit(ctx.bool_expr(0)), visit(ctx.bool_expr(1)));
			}
			else
			{
				return new Expr.Or(visit(ctx.bool_expr(0)), visit(ctx.bool_expr(1)));
			}	
		}

    }


    private ProgVisitor progVis = new ProgVisitor();
    private StmtVisitor stmtVis = new StmtVisitor();
    private StringExprVisitor strExprVis = new StringExprVisitor();
    private BoolExprVisitor boolExprVis = new BoolExprVisitor();


    /** Use this as the subclass for the visitor classes.
     * It overrides the default method to alert you if one of the
     * visit methods is missing.
     */
    private static class Visitor<T> extends ParseRulesBaseVisitor<T> {
        // This overrides the default behavior to alert if a visit method is missing.
        @Override
        public T visitChildren(org.antlr.v4.runtime.tree.RuleNode node) {
            return Errors.error(String.format(
                "class %s has no visit method for %s",
                getClass().getSimpleName(),
                node.getClass().getSimpleName()));
        }
    }
}

