// Generated from si413/ParseRules.g4 by ANTLR 4.13.1
package si413;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParseRules}.
 */
public interface ParseRulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code RegularProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 */
	void enterRegularProg(ParseRules.RegularProgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RegularProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 */
	void exitRegularProg(ParseRules.RegularProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 */
	void enterEmptyProg(ParseRules.EmptyProgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyProg}
	 * labeled alternative in {@link ParseRules#prog}.
	 * @param ctx the parse tree
	 */
	void exitEmptyProg(ParseRules.EmptyProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStrStmt(ParseRules.AssignStrStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStrStmt(ParseRules.AssignStrStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignBoolStmt(ParseRules.AssignBoolStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStrStmt(ParseRules.PrintStrStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStrStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStrStmt(ParseRules.PrintStrStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintBoolStmt}
	 * labeled alternative in {@link ParseRules#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintBoolStmt(ParseRules.PrintBoolStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringPar}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringPar(ParseRules.StringParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringPar}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringPar(ParseRules.StringParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterConcat(ParseRules.ConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Concat}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitConcat(ParseRules.ConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Reverse}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterReverse(ParseRules.ReverseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Reverse}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitReverse(ParseRules.ReverseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterInput(ParseRules.InputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitInput(ParseRules.InputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLit(ParseRules.StringLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLit(ParseRules.StringLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrID}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void enterStrID(ParseRules.StrIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrID}
	 * labeled alternative in {@link ParseRules#str_expr}.
	 * @param ctx the parse tree
	 */
	void exitStrID(ParseRules.StrIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolPar}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolPar(ParseRules.BoolParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolPar}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolPar(ParseRules.BoolParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndOr}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterAndOr(ParseRules.AndOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndOr}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitAndOr(ParseRules.AndOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(ParseRules.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(ParseRules.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringContain}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringContain(ParseRules.StringContainContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringContain}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringContain(ParseRules.StringContainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringCompare}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringCompare(ParseRules.StringCompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringCompare}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringCompare(ParseRules.StringCompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(ParseRules.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(ParseRules.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolID}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolID(ParseRules.BoolIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolID}
	 * labeled alternative in {@link ParseRules#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolID(ParseRules.BoolIDContext ctx);
}