// Generated from si413/ParseRules.g4 by ANTLR 4.13.1
package si413;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ParseRules extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LP=1, RP=2, LOGIC_OP=3, LOGIC_NOT=4, STR_CONTAIN=5, STR_CMP=6, CONCAT=7, 
		REVERSE=8, INPUT=9, PRINT=10, ASSIGN=11, WHILE=12, IF=13, STR_LIT=14, 
		BOOL_LIT=15, STR_TYPE=16, BOOL_TYPE=17, ID=18;
	public static final int
		RULE_prog = 0, RULE_stmt = 1, RULE_stmtList = 2, RULE_str_expr = 3, RULE_bool_expr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "stmt", "stmtList", "str_expr", "bool_expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LP", "RP", "LOGIC_OP", "LOGIC_NOT", "STR_CONTAIN", "STR_CMP", 
			"CONCAT", "REVERSE", "INPUT", "PRINT", "ASSIGN", "WHILE", "IF", "STR_LIT", 
			"BOOL_LIT", "STR_TYPE", "BOOL_TYPE", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ParseRules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ParseRules(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	 
		public ProgContext() { }
		public void copyFrom(ProgContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RegularProgContext extends ProgContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public RegularProgContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterRegularProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitRegularProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitRegularProg(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyProgContext extends ProgContext {
		public TerminalNode EOF() { return getToken(ParseRules.EOF, 0); }
		public EmptyProgContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterEmptyProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitEmptyProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitEmptyProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			setState(14);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
			case ASSIGN:
			case WHILE:
			case IF:
				_localctx = new RegularProgContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				stmt();
				setState(11);
				prog();
				}
				break;
			case EOF:
				_localctx = new EmptyProgContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignStrStmtContext extends StmtContext {
		public TerminalNode ASSIGN() { return getToken(ParseRules.ASSIGN, 0); }
		public TerminalNode STR_TYPE() { return getToken(ParseRules.STR_TYPE, 0); }
		public TerminalNode ID() { return getToken(ParseRules.ID, 0); }
		public Str_exprContext str_expr() {
			return getRuleContext(Str_exprContext.class,0);
		}
		public AssignStrStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterAssignStrStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitAssignStrStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitAssignStrStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CondStmtContext extends StmtContext {
		public TerminalNode IF() { return getToken(ParseRules.IF, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public List<TerminalNode> LP() { return getTokens(ParseRules.LP); }
		public TerminalNode LP(int i) {
			return getToken(ParseRules.LP, i);
		}
		public List<StmtListContext> stmtList() {
			return getRuleContexts(StmtListContext.class);
		}
		public StmtListContext stmtList(int i) {
			return getRuleContext(StmtListContext.class,i);
		}
		public List<TerminalNode> RP() { return getTokens(ParseRules.RP); }
		public TerminalNode RP(int i) {
			return getToken(ParseRules.RP, i);
		}
		public CondStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterCondStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitCondStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitCondStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStrStmtContext extends StmtContext {
		public TerminalNode PRINT() { return getToken(ParseRules.PRINT, 0); }
		public TerminalNode STR_TYPE() { return getToken(ParseRules.STR_TYPE, 0); }
		public Str_exprContext str_expr() {
			return getRuleContext(Str_exprContext.class,0);
		}
		public PrintStrStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterPrintStrStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitPrintStrStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitPrintStrStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignBoolStmtContext extends StmtContext {
		public TerminalNode ASSIGN() { return getToken(ParseRules.ASSIGN, 0); }
		public TerminalNode BOOL_TYPE() { return getToken(ParseRules.BOOL_TYPE, 0); }
		public TerminalNode ID() { return getToken(ParseRules.ID, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public AssignBoolStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterAssignBoolStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitAssignBoolStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitAssignBoolStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopStmtContext extends StmtContext {
		public TerminalNode WHILE() { return getToken(ParseRules.WHILE, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public TerminalNode LP() { return getToken(ParseRules.LP, 0); }
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode RP() { return getToken(ParseRules.RP, 0); }
		public WhileLoopStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterWhileLoopStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitWhileLoopStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitWhileLoopStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintBoolStmtContext extends StmtContext {
		public TerminalNode PRINT() { return getToken(ParseRules.PRINT, 0); }
		public TerminalNode BOOL_TYPE() { return getToken(ParseRules.BOOL_TYPE, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public PrintBoolStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterPrintBoolStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitPrintBoolStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitPrintBoolStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new AssignStrStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(ASSIGN);
				setState(17);
				match(STR_TYPE);
				setState(18);
				match(ID);
				setState(19);
				str_expr();
				}
				break;
			case 2:
				_localctx = new AssignBoolStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				match(ASSIGN);
				setState(21);
				match(BOOL_TYPE);
				setState(22);
				match(ID);
				setState(23);
				bool_expr();
				}
				break;
			case 3:
				_localctx = new PrintStrStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(24);
				match(PRINT);
				setState(25);
				match(STR_TYPE);
				setState(26);
				str_expr();
				}
				break;
			case 4:
				_localctx = new PrintBoolStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				match(PRINT);
				setState(28);
				match(BOOL_TYPE);
				setState(29);
				bool_expr();
				}
				break;
			case 5:
				_localctx = new WhileLoopStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				match(WHILE);
				setState(31);
				bool_expr();
				setState(32);
				match(LP);
				setState(33);
				stmtList();
				setState(34);
				match(RP);
				}
				break;
			case 6:
				_localctx = new CondStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(36);
				match(IF);
				setState(37);
				bool_expr();
				setState(38);
				match(LP);
				setState(39);
				stmtList();
				setState(40);
				match(RP);
				setState(41);
				match(LP);
				setState(42);
				stmtList();
				setState(43);
				match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtListContext extends ParserRuleContext {
		public StmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtList; }
	 
		public StmtListContext() { }
		public void copyFrom(StmtListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementListContext extends StmtListContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public StatementListContext(StmtListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStatementList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStmtContext extends StmtListContext {
		public EmptyStmtContext(StmtListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtListContext stmtList() throws RecognitionException {
		StmtListContext _localctx = new StmtListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmtList);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
			case ASSIGN:
			case WHILE:
			case IF:
				_localctx = new StatementListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				stmt();
				setState(48);
				stmtList();
				}
				break;
			case RP:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Str_exprContext extends ParserRuleContext {
		public Str_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str_expr; }
	 
		public Str_exprContext() { }
		public void copyFrom(Str_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConcatContext extends Str_exprContext {
		public TerminalNode CONCAT() { return getToken(ParseRules.CONCAT, 0); }
		public List<Str_exprContext> str_expr() {
			return getRuleContexts(Str_exprContext.class);
		}
		public Str_exprContext str_expr(int i) {
			return getRuleContext(Str_exprContext.class,i);
		}
		public ConcatContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitConcat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InputContext extends Str_exprContext {
		public TerminalNode INPUT() { return getToken(ParseRules.INPUT, 0); }
		public InputContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReverseContext extends Str_exprContext {
		public TerminalNode REVERSE() { return getToken(ParseRules.REVERSE, 0); }
		public Str_exprContext str_expr() {
			return getRuleContext(Str_exprContext.class,0);
		}
		public ReverseContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterReverse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitReverse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitReverse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrIDContext extends Str_exprContext {
		public TerminalNode ID() { return getToken(ParseRules.ID, 0); }
		public StrIDContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStrID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStrID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStrID(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringParContext extends Str_exprContext {
		public TerminalNode LP() { return getToken(ParseRules.LP, 0); }
		public Str_exprContext str_expr() {
			return getRuleContext(Str_exprContext.class,0);
		}
		public TerminalNode RP() { return getToken(ParseRules.RP, 0); }
		public StringParContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStringPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStringPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStringPar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLitContext extends Str_exprContext {
		public TerminalNode STR_LIT() { return getToken(ParseRules.STR_LIT, 0); }
		public StringLitContext(Str_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStringLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStringLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStringLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Str_exprContext str_expr() throws RecognitionException {
		Str_exprContext _localctx = new Str_exprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_str_expr);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new StringParContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(LP);
				setState(54);
				str_expr();
				setState(55);
				match(RP);
				}
				break;
			case CONCAT:
				_localctx = new ConcatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(CONCAT);
				setState(58);
				str_expr();
				setState(59);
				str_expr();
				}
				break;
			case REVERSE:
				_localctx = new ReverseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(REVERSE);
				setState(62);
				str_expr();
				}
				break;
			case INPUT:
				_localctx = new InputContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				match(INPUT);
				}
				break;
			case STR_LIT:
				_localctx = new StringLitContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(64);
				match(STR_LIT);
				}
				break;
			case ID:
				_localctx = new StrIDContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(65);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bool_exprContext extends ParserRuleContext {
		public Bool_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_expr; }
	 
		public Bool_exprContext() { }
		public void copyFrom(Bool_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends Bool_exprContext {
		public TerminalNode LOGIC_NOT() { return getToken(ParseRules.LOGIC_NOT, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public NotContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContainContext extends Bool_exprContext {
		public TerminalNode STR_CONTAIN() { return getToken(ParseRules.STR_CONTAIN, 0); }
		public List<Str_exprContext> str_expr() {
			return getRuleContexts(Str_exprContext.class);
		}
		public Str_exprContext str_expr(int i) {
			return getRuleContext(Str_exprContext.class,i);
		}
		public StringContainContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStringContain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStringContain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStringContain(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolParContext extends Bool_exprContext {
		public TerminalNode LP() { return getToken(ParseRules.LP, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public TerminalNode RP() { return getToken(ParseRules.RP, 0); }
		public BoolParContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterBoolPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitBoolPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitBoolPar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringCompareContext extends Bool_exprContext {
		public TerminalNode STR_CMP() { return getToken(ParseRules.STR_CMP, 0); }
		public List<Str_exprContext> str_expr() {
			return getRuleContexts(Str_exprContext.class);
		}
		public Str_exprContext str_expr(int i) {
			return getRuleContext(Str_exprContext.class,i);
		}
		public StringCompareContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterStringCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitStringCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitStringCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolIDContext extends Bool_exprContext {
		public TerminalNode ID() { return getToken(ParseRules.ID, 0); }
		public BoolIDContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterBoolID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitBoolID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitBoolID(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndOrContext extends Bool_exprContext {
		public TerminalNode LOGIC_OP() { return getToken(ParseRules.LOGIC_OP, 0); }
		public List<Bool_exprContext> bool_expr() {
			return getRuleContexts(Bool_exprContext.class);
		}
		public Bool_exprContext bool_expr(int i) {
			return getRuleContext(Bool_exprContext.class,i);
		}
		public AndOrContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterAndOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitAndOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitAndOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolLitContext extends Bool_exprContext {
		public TerminalNode BOOL_LIT() { return getToken(ParseRules.BOOL_LIT, 0); }
		public BoolLitContext(Bool_exprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).enterBoolLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParseRulesListener ) ((ParseRulesListener)listener).exitBoolLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParseRulesVisitor ) return ((ParseRulesVisitor<? extends T>)visitor).visitBoolLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_exprContext bool_expr() throws RecognitionException {
		Bool_exprContext _localctx = new Bool_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bool_expr);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new BoolParContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(LP);
				setState(69);
				bool_expr();
				setState(70);
				match(RP);
				}
				break;
			case LOGIC_OP:
				_localctx = new AndOrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(LOGIC_OP);
				setState(73);
				bool_expr();
				setState(74);
				bool_expr();
				}
				break;
			case LOGIC_NOT:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				match(LOGIC_NOT);
				setState(77);
				bool_expr();
				}
				break;
			case STR_CONTAIN:
				_localctx = new StringContainContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				match(STR_CONTAIN);
				setState(79);
				str_expr();
				setState(80);
				str_expr();
				}
				break;
			case STR_CMP:
				_localctx = new StringCompareContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(82);
				match(STR_CMP);
				setState(83);
				str_expr();
				setState(84);
				str_expr();
				}
				break;
			case BOOL_LIT:
				_localctx = new BoolLitContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(86);
				match(BOOL_LIT);
				}
				break;
			case ID:
				_localctx = new BoolIDContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(87);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0012[\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u000f\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001.\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00024\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003C\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004Y\b\u0004\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006"+
		"\b\u0000\u0000g\u0000\u000e\u0001\u0000\u0000\u0000\u0002-\u0001\u0000"+
		"\u0000\u0000\u00043\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000"+
		"\bX\u0001\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b\f\u0003"+
		"\u0000\u0000\u0000\f\u000f\u0001\u0000\u0000\u0000\r\u000f\u0005\u0000"+
		"\u0000\u0001\u000e\n\u0001\u0000\u0000\u0000\u000e\r\u0001\u0000\u0000"+
		"\u0000\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u000b\u0000"+
		"\u0000\u0011\u0012\u0005\u0010\u0000\u0000\u0012\u0013\u0005\u0012\u0000"+
		"\u0000\u0013.\u0003\u0006\u0003\u0000\u0014\u0015\u0005\u000b\u0000\u0000"+
		"\u0015\u0016\u0005\u0011\u0000\u0000\u0016\u0017\u0005\u0012\u0000\u0000"+
		"\u0017.\u0003\b\u0004\u0000\u0018\u0019\u0005\n\u0000\u0000\u0019\u001a"+
		"\u0005\u0010\u0000\u0000\u001a.\u0003\u0006\u0003\u0000\u001b\u001c\u0005"+
		"\n\u0000\u0000\u001c\u001d\u0005\u0011\u0000\u0000\u001d.\u0003\b\u0004"+
		"\u0000\u001e\u001f\u0005\f\u0000\u0000\u001f \u0003\b\u0004\u0000 !\u0005"+
		"\u0001\u0000\u0000!\"\u0003\u0004\u0002\u0000\"#\u0005\u0002\u0000\u0000"+
		"#.\u0001\u0000\u0000\u0000$%\u0005\r\u0000\u0000%&\u0003\b\u0004\u0000"+
		"&\'\u0005\u0001\u0000\u0000\'(\u0003\u0004\u0002\u0000()\u0005\u0002\u0000"+
		"\u0000)*\u0005\u0001\u0000\u0000*+\u0003\u0004\u0002\u0000+,\u0005\u0002"+
		"\u0000\u0000,.\u0001\u0000\u0000\u0000-\u0010\u0001\u0000\u0000\u0000"+
		"-\u0014\u0001\u0000\u0000\u0000-\u0018\u0001\u0000\u0000\u0000-\u001b"+
		"\u0001\u0000\u0000\u0000-\u001e\u0001\u0000\u0000\u0000-$\u0001\u0000"+
		"\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/0\u0003\u0002\u0001\u0000"+
		"01\u0003\u0004\u0002\u000014\u0001\u0000\u0000\u000024\u0001\u0000\u0000"+
		"\u00003/\u0001\u0000\u0000\u000032\u0001\u0000\u0000\u00004\u0005\u0001"+
		"\u0000\u0000\u000056\u0005\u0001\u0000\u000067\u0003\u0006\u0003\u0000"+
		"78\u0005\u0002\u0000\u00008C\u0001\u0000\u0000\u00009:\u0005\u0007\u0000"+
		"\u0000:;\u0003\u0006\u0003\u0000;<\u0003\u0006\u0003\u0000<C\u0001\u0000"+
		"\u0000\u0000=>\u0005\b\u0000\u0000>C\u0003\u0006\u0003\u0000?C\u0005\t"+
		"\u0000\u0000@C\u0005\u000e\u0000\u0000AC\u0005\u0012\u0000\u0000B5\u0001"+
		"\u0000\u0000\u0000B9\u0001\u0000\u0000\u0000B=\u0001\u0000\u0000\u0000"+
		"B?\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BA\u0001\u0000\u0000"+
		"\u0000C\u0007\u0001\u0000\u0000\u0000DE\u0005\u0001\u0000\u0000EF\u0003"+
		"\b\u0004\u0000FG\u0005\u0002\u0000\u0000GY\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0003\u0000\u0000IJ\u0003\b\u0004\u0000JK\u0003\b\u0004\u0000KY\u0001"+
		"\u0000\u0000\u0000LM\u0005\u0004\u0000\u0000MY\u0003\b\u0004\u0000NO\u0005"+
		"\u0005\u0000\u0000OP\u0003\u0006\u0003\u0000PQ\u0003\u0006\u0003\u0000"+
		"QY\u0001\u0000\u0000\u0000RS\u0005\u0006\u0000\u0000ST\u0003\u0006\u0003"+
		"\u0000TU\u0003\u0006\u0003\u0000UY\u0001\u0000\u0000\u0000VY\u0005\u000f"+
		"\u0000\u0000WY\u0005\u0012\u0000\u0000XD\u0001\u0000\u0000\u0000XH\u0001"+
		"\u0000\u0000\u0000XL\u0001\u0000\u0000\u0000XN\u0001\u0000\u0000\u0000"+
		"XR\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000XW\u0001\u0000\u0000"+
		"\u0000Y\t\u0001\u0000\u0000\u0000\u0005\u000e-3BX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}