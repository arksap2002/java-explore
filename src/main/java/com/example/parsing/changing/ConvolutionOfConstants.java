package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import static com.github.javaparser.ast.Node.SYMBOL_RESOLVER_KEY;

public class ConvolutionOfConstants {
    public static boolean change = true;

    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input5.java"));
//        System.out.println(Generating_test());
    }


    static class Finding extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
            integerLiteralExpr.setInt(0);
            if ((n.getOperator() == BinaryExpr.Operator.MINUS) && (n.getRight().isNameExpr()) && (n.getLeft().isNameExpr())) {
                if ((((JavaParserFieldDeclaration) (n.getRight().asNameExpr().resolve())).getWrappedNode()).equals(((JavaParserFieldDeclaration) (n.getLeft().asNameExpr().resolve())).getWrappedNode())) {
                    n.replace(integerLiteralExpr);
                    change = true;
                }
            }
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

        @Override
        public void visit(EnclosedExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getInner().isIntegerLiteralExpr()) {
                IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
                integerLiteralExpr.setInt(n.getInner().asIntegerLiteralExpr().asInt());
                n.replace(integerLiteralExpr);
                change = true;
            }
        }
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.setData(SYMBOL_RESOLVER_KEY, new JavaSymbolSolver(typeSolver));
        int number = 0;
        while (change) {
            change = false;
            compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
            number += 1;
        }
        System.out.println(number);
        return compilationUnit.toString();
    }

    public static String Generating_test() {
        String[] words = {"a", "b", "c", "d", "e", "f", "g", "e", "k", "l", "m"};
        String[] operators = {"+", "-", "*"};
        Random random = new Random();
        int number = 100;
        return making_test(words, operators, random, number);
    }

    private static String making_test(String[] words, String[] operators, Random random, int number) {
        if (number < 0) {
            return "(" + Math.abs(random.nextInt()) % 11 + operators[Math.abs(random.nextInt()) % 3] + Math.abs(random.nextInt()) % 11 + ")";
        }
        number--;
        if (random.nextInt() % 2 == 0) {
            return "(" + making_test(words, operators, random, number) + operators[Math.abs(random.nextInt()) % 3] + Math.abs(random.nextInt()) % 11 + ")";
        } else {
            return "(" + Math.abs(random.nextInt()) % 11 + operators[Math.abs(random.nextInt()) % 3] + making_test(words, operators, random, number) + ")";
        }
    }
}
