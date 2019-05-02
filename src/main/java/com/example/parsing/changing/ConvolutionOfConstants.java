package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ConvolutionOfConstants {
    public static ArrayList<VariableDeclarator> variableDeclarators = new ArrayList<>();
    public static boolean change = true;

    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input3.java"));
    }

    static class Finding_binary extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getLeft().isIntegerLiteralExpr() && n.getRight().isIntegerLiteralExpr()) {
                IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
                IntegerLiteralExpr left = new IntegerLiteralExpr();
                IntegerLiteralExpr right = new IntegerLiteralExpr();
                if (n.getLeft().isIntegerLiteralExpr()) {
                    left = n.getLeft().asIntegerLiteralExpr();
                }
                if (n.getRight().isIntegerLiteralExpr()) {
                    right = n.getRight().asIntegerLiteralExpr();
                }
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
                n.replace(integerLiteralExpr);
                change = true;
            }
            if (n.getLeft().isNameExpr()) {
                for (VariableDeclarator variableDeclarator : variableDeclarators) {
                    if (variableDeclarator.getName().toString().equals(n.getLeft().toString())) {
                        n.getLeft().replace(variableDeclarator.getInitializer().get());
                        change = true;
                    }
                }
            }
            if (n.getRight().isNameExpr()) {
                for (VariableDeclarator variableDeclarator : variableDeclarators) {
                    if (variableDeclarator.getName().toString().equals(n.getRight().toString())) {
                        n.getRight().replace(variableDeclarator.getInitializer().get());
                        change = true;
                    }
                }
            }
        }
    }

    static class Finding_enclose extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(EnclosedExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getInner().isBinaryExpr()) {
                n.replace(n.getInner().asBinaryExpr());
                change = true;
            }
        }
    }

    static class Finding_name extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getInitializer().get().isIntegerLiteralExpr()) {
                variableDeclarators.add(n);
            }
        }
    }

    static class Finding_variable extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getInitializer().get().isNameExpr()) {
                for (VariableDeclarator variableDeclarator : variableDeclarators) {
                    if (variableDeclarator.getName().toString().equals(n.getInitializer().get().toString())) {
                        n.setInitializer(variableDeclarator.getInitializer().get());
                        change = true;
                    }
                }
            }
        }
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        while (change) {
            change = false;
            variableDeclarators.clear();
            compilationUnit.accept(new Finding_name(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_enclose(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_binary(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_variable(), JavaParserFacade.get(typeSolver));
//            for (VariableDeclarator variableDeclarator : variableDeclarators) {
//                System.out.print(variableDeclarator.getName().toString() + " ");
//            }
//            System.out.println();
        }
        return compilationUnit.toString();
    }
}
