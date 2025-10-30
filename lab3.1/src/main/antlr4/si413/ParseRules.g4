parser grammar ParseRules;
// grammar for Pirate language

tokens {LP, RP, LOGIC_OP, LOGIC_NOT, STR_CONTAIN, STR_CMP, CONCAT, REVERSE,
INPUT, PRINT, ASSIGN, WHILE, IF, STR_LIT, BOOL_LIT, STR_TYPE, BOOL_TYPE, ID}

prog
  : stmt prog #RegularProg
  | EOF #EmptyProg
  ;

stmt
  : ASSIGN STR_TYPE ID str_expr #AssignStrStmt
  | ASSIGN BOOL_TYPE ID bool_expr #AssignBoolStmt
  | PRINT STR_TYPE str_expr #PrintStrStmt
  | PRINT BOOL_TYPE bool_expr #PrintBoolStmt
  | WHILE bool_expr LP stmtList RP #WhileLoopStmt
  | IF bool_expr LP stmtList RP LP stmtList RP #CondStmt
  ;

stmtList
  : stmt stmtList #StatementList
  |               #EmptyStmt
  ;

str_expr
  : LP str_expr RP #StringPar
  | CONCAT str_expr str_expr #Concat
  | REVERSE str_expr #Reverse
  | INPUT #Input
  | STR_LIT #StringLit
  | ID #StrID
  ;

bool_expr
  : LP bool_expr RP #BoolPar
  | LOGIC_OP bool_expr bool_expr #AndOr
  | LOGIC_NOT bool_expr #Not
  | STR_CONTAIN str_expr str_expr #StringContain
  | STR_CMP str_expr str_expr #StringCompare
  | BOOL_LIT #BoolLit
  | ID #BoolID
  ;
