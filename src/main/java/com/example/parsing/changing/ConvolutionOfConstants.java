package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.NameExpr;
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
        System.out.println(transformResource("/input5.java"));
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
        }
    }

    static class Changing_name_to_int extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
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

    static class Finding_multiply extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if ((n.getLeft().isIntegerLiteralExpr()) && (n.getRight().isNameExpr()) && (n.getOperator() == BinaryExpr.Operator.MULTIPLY)){
                if (n.getLeft().asIntegerLiteralExpr().asInt() == 2){
                    n.setOperator(BinaryExpr.Operator.PLUS);
                    NameExpr nameExpr = n.getRight().asNameExpr();
                    n.setLeft(nameExpr);
                    n.setRight(nameExpr);
                    change = true;
                }
                if (n.getLeft().asIntegerLiteralExpr().asInt() == 1){
                    NameExpr nameExpr = n.getRight().asNameExpr();
                    n.replace(nameExpr);
                }
                if (n.getLeft().asIntegerLiteralExpr().asInt() > 2){
                    n.setOperator(BinaryExpr.Operator.PLUS);
                    IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
                    integerLiteralExpr.setInt(n.getLeft().asIntegerLiteralExpr().asInt() - 1);
                    BinaryExpr binaryExpr = new BinaryExpr();
                    binaryExpr.setOperator(BinaryExpr.Operator.MULTIPLY);
                    binaryExpr.setRight(n.getRight());
                    binaryExpr.setLeft(integerLiteralExpr);
                    n.replace(binaryExpr);
                }
            }
            if ((n.getRight().isIntegerLiteralExpr()) && (n.getLeft().isNameExpr()) && (n.getOperator() == BinaryExpr.Operator.MULTIPLY)){
                if (n.getRight().asIntegerLiteralExpr().asInt() == 2){
                    n.setOperator(BinaryExpr.Operator.PLUS);
                    NameExpr nameExpr = n.getLeft().asNameExpr();
                    n.setLeft(nameExpr);
                    n.setRight(nameExpr);
                    change = true;
                }
                if (n.getRight().asIntegerLiteralExpr().asInt() == 1){
                    NameExpr nameExpr = n.getLeft().asNameExpr();
                    n.replace(nameExpr);
                }
                if (n.getRight().asIntegerLiteralExpr().asInt() > 2){
                    n.setOperator(BinaryExpr.Operator.PLUS);
                    IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
                    integerLiteralExpr.setInt(n.getRight().asIntegerLiteralExpr().asInt() - 1);
                    BinaryExpr binaryExpr = new BinaryExpr();
                    binaryExpr.setOperator(BinaryExpr.Operator.MULTIPLY);
                    binaryExpr.setRight(n.getLeft());
                    binaryExpr.setLeft(integerLiteralExpr);
                    n.replace(binaryExpr);
                }
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

    static class Finding_binary_name extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
//            if ((n.getOperator() == BinaryExpr.Operator.PLUS) && (n.getRight().isNameExpr()) && (n.getLeft().isNameExpr()) && ()){
//
//            }
        }
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        while (change) {
            change = false;
            variableDeclarators.clear();
            compilationUnit.accept(new Finding_name(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_binary(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_variable(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_multiply(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_enclose(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_binary_name(), JavaParserFacade.get(typeSolver));
//            compilationUnit.accept(new Changing_name_to_int(), JavaParserFacade.get(typeSolver));
        }
        return compilationUnit.toString();
    }
}
