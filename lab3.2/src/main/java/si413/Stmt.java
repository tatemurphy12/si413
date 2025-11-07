package si413;

import java.util.List;

/** AST nodes for statements.
 * Statements can be executed but do not return a value.
 */
public interface Stmt {
    /** Executes this AST node in an interpreter. */
    void exec(Interpreter interp);

    /** Compiles this AST node.
     * The Compiler instance comp is used to store shared state
     * of the running compiler, such as the destination output stream.
     */
    void compile(Compiler comp);

    // ******** AST Node types for statements ******** //

    record Block(List<Stmt> children) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            for (Stmt child : children) {
                child.exec(interp);
            }
        }

        @Override
        public void compile(Compiler comp) {
            for (Stmt child: children) {
                child.compile(comp);
            }
        }
    }

    record ExprStmt(Expr<?> child) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            child.eval(interp);
        }

        @Override
        public void compile(Compiler comp) {
            child.compile(comp);
        }
    }

    record AssignString(String name, Expr<String> child) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            String val = child.eval(interp);
            interp.getStringVars().put(name, val);
        }

        @Override
        public void compile(Compiler comp) {
	    	String s = child.compile(comp);
            comp.dest().format("   %%%sptr = alloca i8*\n", name);
            comp.dest().format("   store i8* %s, i8** %%%sptr\n", s, name);
			comp.dest().format("   %%%s = load i8*, i8** %%%sptr", name, name);

        }
    }

    record AssignBool(String name, Expr<Boolean> child) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            boolean val = child.eval(interp);
            interp.getBoolVars().put(name, val);
        }

        @Override
        public void compile(Compiler comp) {
            comp.dest().format("   %%%s = alloca i1*\n", name);
	    	String val = child.compile(comp);
	    	comp.dest().format("   store i1 %s, i1* %%%s\n", val, name);
        }
    }

    record PrintString(Expr<String> child) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            System.out.println(child.eval(interp));
        }

        @Override
        public void compile(Compiler comp) {
            String chreg = child.compile(comp);
            comp.dest().format("  call i32 @puts(ptr %s)\n", chreg);
        }
    }

    record PrintBool(Expr<Boolean> child) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            if (child.eval(interp)) System.out.println("True");
            else System.out.println("False");
        }

        @Override
        public void compile(Compiler comp) {
            String chreg = child.compile(comp);
            comp.dest().format("  call void @print_bool(i1 %s)\n", chreg);
        }
    }

    record IfElse(Expr<Boolean> condition, Stmt.Block ifBody, Stmt.Block elseBody) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            if (condition.eval(interp)) {
                ifBody.exec(interp);
            }
            else {
                elseBody.exec(interp);
            }
        }

        @Override
        public void compile(Compiler comp) {
            String bool = condition.compile(comp);
			int delin = comp.getNum();
            comp.dest().format("   br i1 %s, label %%true_%d, label %%false_%d\n", bool, delin, delin );
			comp.dest().format("true_%d:\n", delin);
            ifBody.compile(comp);
			comp.dest().format("   br label %%exit_%d\n", delin);
			comp.dest().format("false_%d:\n", delin);
			elseBody.compile(comp);
			comp.dest().format("   br label %%exit_%d\n", delin);
			comp.dest().format("exit_%d:\n", delin);
			comp.incrementNum();
        }
    }

    record While(Expr<Boolean> condition, Stmt.Block body) implements Stmt {
        @Override
        public void exec(Interpreter interp) {
            while (condition.eval(interp)) {
                body.exec(interp);
            }
        }

        @Override
        public void compile(Compiler comp) 
		{
			int delin = comp.getNum();
			comp.dest().format("   br label %%header_%d\n", delin);
			comp.dest().format("header_%d:\n", delin);
			String bool = condition.compile(comp);
			comp.dest().format("   br i1 %s, label %%body_%d, label %%exit_%d\n", bool, delin, delin);
			comp.dest().format("body_%d:\n", delin);
            body.compile(comp);
			comp.dest().format("   br label %%header_%d\n", delin);
			comp.dest().format("exit_%d:\n", delin);
			comp.incrementNum();
        }
    }
}
