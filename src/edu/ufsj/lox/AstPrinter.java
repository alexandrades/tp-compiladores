package edu.ufsj.lox;


class AstPrinter implements Expr.Visitor<String> {
	  String print(Expr expr) {
	    return expr.accept(this);
	  }
	  @Override
	  public String visitBinaryExpr(Expr.Binary expr) {
	    return parenthesize(expr.operator.lexeme,
	                        expr.left, expr.right);
	  }


	  @Override
	  public String visitTernaryExpr(Expr.Ternary expr) {
	    return parenthesize(expr.expr,
	                        expr.ifTrue, expr.ifFalse);
	  }

	  @Override
	  public String visitGroupingExpr(Expr.Grouping expr) {
	    return parenthesize("group", expr.expression);
	  }

	  @Override
	  public String visitLiteralExpr(Expr.Literal expr) {
	    if (expr.value == null) return "nil";
	    return expr.value.toString();
	  }

	  @Override
	  public String visitUnaryExpr(Expr.Unary expr) {
	    return parenthesize(expr.operator.lexeme, expr.right);
	  }
	  private String parenthesize(String name, Expr... exprs) {
		    StringBuilder builder = new StringBuilder();

		    builder.append("(").append(name);
		    for (Expr expr : exprs) {
		      builder.append(" ");
		      builder.append(expr.accept(this));
		    }
		    builder.append(")");

		    return builder.toString();
	  }


	  private String parenthesize(Expr expr, Expr ifTrue, Expr ifFalse) {
		StringBuilder builder = new StringBuilder();

		builder.append("(").append(expr.accept(this)).append(" ").append(ifTrue.accept(this)).append(" ").append(ifFalse.accept(this));
		builder.append(")");

		return builder.toString();
  }

	
	    /*private String parenthesize2(String name, Object... parts) {
	    *    StringBuilder builder = new StringBuilder();
	    *
	    *    builder.append("(").append(name);
	    *    //transform(builder,parts);
	    *    builder.append(")");
	    *
	    *    return builder.toString();
	    *}
	    */
	    /**
	     * @param builder
	     * @param parts
	     */
	    /*
	    *private void transform(StringBuilder builder, Object... parts){
	    *    for(Object part:parts){
	    *        builder.append(" ");
	    *        if(part instanceof Expr){
	    *            builder.append(((Expr)part).accept(this));
	    *        }else if(part instanceof Stmt)
	    *            bu(((stmt) part).accept(this))
	    *        }else if(part instanceof Token){
	    *            builder.append(((Token) part).lexeme);
	    *        }else if(part instanceof List){
	    *            transform(builder,((List)parts).toArray());
	    *        }else{
	    *            builder.append(part);
	    *        }
	    *    }
	    }
	    */
	    /**
	     * @param args
	     */
	  public static void main(String[] args) {
		    Expr expression = new Expr.Binary(
		        new Expr.Unary(
		            new Token(TokenType.MINUS, "-", null, 1),
		            new Expr.Literal(123)),
		        new Token(TokenType.STAR, "*", null, 1),
		        new Expr.Grouping(
		            new Expr.Literal(45.67)));

		    System.out.println(new AstPrinter().print(expression));
	   }
	
}
