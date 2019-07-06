package com.example.parsing.changing.convolution_of_constants;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserSymbolDeclaration;

public class New_Finding extends ModifierVisitor<JavaParserFacade> {

    @Override
    public Visitable visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
        super.visit(n, javaParserFacade);
        if (n.getInitializer().get().isNameExpr()) {
            NameExpr nameExpr = n.getInitializer().get().asNameExpr();
            ResolvedValueDeclaration resolvedValueDeclaration = nameExpr.resolve();
            if (resolvedValueDeclaration instanceof JavaParserSymbolDeclaration) {
                VariableDeclarator variableDeclarator = (VariableDeclarator) ((JavaParserSymbolDeclaration) (resolvedValueDeclaration)).getWrappedNode();
                IntegerLiteralExpr integerLiteralExpr = variableDeclarator.getInitializer().get().asIntegerLiteralExpr();
                n.setInitializer(integerLiteralExpr);
                Main.change = true;
            }
            if (resolvedValueDeclaration instanceof JavaParserFieldDeclaration) {
                FieldDeclaration fieldDeclaration = ((JavaParserFieldDeclaration) (resolvedValueDeclaration)).getWrappedNode();
                IntegerLiteralExpr integerLiteralExpr = fieldDeclaration.getVariable(0).getInitializer().get().asIntegerLiteralExpr();
                n.setInitializer(integerLiteralExpr);
                Main.change = true;
            }
        }
        return n;
    }

    @Override
    public Visitable visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
        super.visit(n, javaParserFacade);
        IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
        number_and_number(n, integerLiteralExpr);
        if (n.getLeft().isNameExpr()) {
            ResolvedValueDeclaration resolvedValueDeclaration = n.getLeft().asNameExpr().resolve();
            n.getLeft().replace(word(resolvedValueDeclaration));
            Main.change = true;
        }
        if (n.getRight().isNameExpr()) {
            ResolvedValueDeclaration resolvedValueDeclaration = n.getRight().asNameExpr().resolve();
            n.getRight().replace(word(resolvedValueDeclaration));
            Main.change = true;
        }
        return number_and_number(n, integerLiteralExpr);
    }

    private IntegerLiteralExpr word(ResolvedValueDeclaration resolvedValueDeclaration) {
        IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
        if (resolvedValueDeclaration instanceof JavaParserFieldDeclaration) {
            FieldDeclaration fieldDeclaration = ((JavaParserFieldDeclaration) (resolvedValueDeclaration)).getWrappedNode();
            integerLiteralExpr = fieldDeclaration.getVariable(0).getInitializer().get().asIntegerLiteralExpr();
        }
        if (resolvedValueDeclaration instanceof JavaParserSymbolDeclaration) {
            VariableDeclarator variableDeclarator = (VariableDeclarator) ((JavaParserSymbolDeclaration) (resolvedValueDeclaration)).getWrappedNode();
            integerLiteralExpr = variableDeclarator.getInitializer().get().asIntegerLiteralExpr();
        }
        return integerLiteralExpr;
    }

    private Visitable number_and_number(BinaryExpr n, IntegerLiteralExpr integerLiteralExpr) {
        if (n.getLeft().isIntegerLiteralExpr() && n.getRight().isIntegerLiteralExpr()) {
            integerLiteralExpr = new IntegerLiteralExpr();
            IntegerLiteralExpr left = new IntegerLiteralExpr();
            IntegerLiteralExpr right = new IntegerLiteralExpr();
            if (n.getLeft().isIntegerLiteralExpr()) {
                left = n.getLeft().asIntegerLiteralExpr();
            }
            if (n.getRight().isIntegerLiteralExpr()) {
                right = n.getRight().asIntegerLiteralExpr();
            }
            return updating_math(left, right, integerLiteralExpr, n);
        }
        return n;
    }

    private Visitable updating_math(IntegerLiteralExpr left, IntegerLiteralExpr right, IntegerLiteralExpr integerLiteralExpr, BinaryExpr n) {
        if (n.getOperator() == BinaryExpr.Operator.PLUS) {
            integerLiteralExpr.setInt(left.asInt() + right.asInt());
        }
        if (n.getOperator() == BinaryExpr.Operator.MINUS) {
            integerLiteralExpr.setInt(left.asInt() - right.asInt());
        }
        if (n.getOperator() == BinaryExpr.Operator.MULTIPLY) {
            integerLiteralExpr.setInt(left.asInt() * right.asInt());
        }
        if (n.getOperator() == BinaryExpr.Operator.DIVIDE) {
            integerLiteralExpr.setInt(left.asInt() / right.asInt());
        }
        Main.change = true;
        return integerLiteralExpr;
    }

    @Override
    public Visitable visit(EnclosedExpr n, JavaParserFacade javaParserFacade) {
        super.visit(n, javaParserFacade);
        if (n.getInner().isIntegerLiteralExpr()) {
            IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
            integerLiteralExpr.setInt(n.getInner().asIntegerLiteralExpr().asInt());
            Main.change = true;
            return integerLiteralExpr;
        }
        return n;
    }
}
