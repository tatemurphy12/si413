// Generated from si413/ParseRules.g4 by ANTLR 4.13.1
package si413;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParseRules}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ParseRulesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code RegularProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularProg(ParseRules.RegularProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyProg(ParseRules.EmptyProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStrStmt(ParseRules.AssignStrStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStrStmt(ParseRules.PrintStrStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringPar}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringPar(ParseRules.StringParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcat(ParseRules.ConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Reverse}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReverse(ParseRules.ReverseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(ParseRules.InputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLit(ParseRules.StringLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrID}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrID(ParseRules.StrIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolPar}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolPar(ParseRules.BoolParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndOr}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOr(ParseRules.AndOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(ParseRules.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringContain}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringContain(ParseRules.StringContainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringCompare}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringCompare(ParseRules.StringCompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLit(ParseRules.BoolLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolID}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolID(ParseRules.BoolIDContext ctx);
}