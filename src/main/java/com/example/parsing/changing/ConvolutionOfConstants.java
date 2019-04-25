package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ConvolutionOfConstants {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input3.java"));
    }

    static class Finding_operators extends VoidVisitorAdapter<JavaParserFacade> {

        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getLeft().isBinaryExpr()) {
                visit(n.getLeft().asBinaryExpr(), javaParserFacade);
            }
            if (n.getRight().isBinaryExpr()) {
                visit(n.getRight().asBinaryExpr(), javaParserFacade);
            }
            if (n.getLeft().isIntegerLiteralExpr() && n.getRight().isIntegerLiteralExpr()) {
                IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
                if (n.getOperator() == BinaryExpr.Operator.PLUS) {
                    integerLiteralExpr.setInt(n.getLeft().asIntegerLiteralExpr().asInt() + n.getRight().asIntegerLiteralExpr().asInt());
                }
                if (n.getOperator() == BinaryExpr.Operator.MINUS) {
                    integerLiteralExpr.setInt(n.getLeft().asIntegerLiteralExpr().asInt() - n.getRight().asIntegerLiteralExpr().asInt());
                }
                if (n.getOperator() == BinaryExpr.Operator.MULTIPLY) {
                    integerLiteralExpr.setInt(n.getLeft().asIntegerLiteralExpr().asInt() * n.getRight().asIntegerLiteralExpr().asInt());
                }
                if (n.getOperator() == BinaryExpr.Operator.DIVIDE) {
                    integerLiteralExpr.setInt(n.getLeft().asIntegerLiteralExpr().asInt() / n.getRight().asIntegerLiteralExpr().asInt());
                }
                n.replace(integerLiteralExpr);
            }
        }
    }

    static class Updating_variables extends VoidVisitorAdapter<JavaParserFacade> {

        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getInitializer().get().isUnaryExpr()){
                
            }
        }
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.accept(new Finding_operators(), JavaParserFacade.get(typeSolver));
        compilationUnit.accept(new Updating_variables(), JavaParserFacade.get(typeSolver));
        //
        return compilationUnit.toString();
    }
}
