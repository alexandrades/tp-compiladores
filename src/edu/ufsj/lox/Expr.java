package edu.ufsj.lox;

import java.util.List;
import edu.ufsj.lox.*;

abstract class Expr {
   interface Visitor<R>{
      R visitBinaryExpr(Binary expr);
      R visitTernaryExpr(Ternary expr);
      R visitGroupingExpr(Grouping expr);
      R visitUnaryExpr(Unary expr);
      R visitLiteralExpr(Literal expr);
   }
   static class Binary extends Expr {
      Binary(Expr left, Token operator, Expr right) {
         this.left = left;
         this.operator = operator;
         this.right = right;
      }

      @Override
      <R> R accept(Visitor<R> visitor) {
         return visitor.visitBinaryExpr(this);
      }

      final Expr left;
      final Token operator;
      final Expr right;
   }


   static class Ternary extends Expr {
      Ternary(Expr expr, Expr ifTrue, Expr ifFalse) {
         this.expr = expr;
         this.ifTrue = ifTrue;
         this.ifFalse = ifFalse;
      }

      @Override
      <R> R accept(Visitor<R> visitor) {
         return visitor.visitTernaryExpr(this);
      }

      final Expr expr;
      final Expr ifTrue;
      final Expr ifFalse;
   }

   static class Grouping extends Expr {
      Grouping(Expr expression) {
         this.expression = expression;
      }

      @Override
      <R> R accept(Visitor<R> visitor) {
         return visitor.visitGroupingExpr(this);
      }

      final Expr expression;
   }

   static class Unary extends Expr {
      Unary(Token operator, Expr right) {
         this.operator = operator;
         this.right = right;
      }

      @Override
      <R> R accept(Visitor<R> visitor) {
         return visitor.visitUnaryExpr(this);
      }

      final Token operator;
      final Expr right;
   }

   static class Literal extends Expr {
      Literal(Object value) {
         this.value = value;
      }

      @Override
      <R> R accept(Visitor<R> visitor) {
         return visitor.visitLiteralExpr(this);
      }

      final Object value;
   }


   abstract <R> R accept(Visitor<R> visitor);
}
