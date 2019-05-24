package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.javaparser.ast.Node.SYMBOL_RESOLVER_KEY;

public class ConvolutionOfConstants {
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

    static class Finding_enclose extends VoidVisitorAdapter<JavaParserFacade> {
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

    static class Finding_binary_name extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(BinaryExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr();
            integerLiteralExpr.setInt(0);
            if ((n.getOperator() == BinaryExpr.Operator.MINUS) && (n.getRight().isNameExpr()) && (n.getLeft().isNameExpr())) {
                ResolvedValueDeclaration resolvedValueDeclaration_r = n.getRight().asNameExpr().resolve();
                ResolvedValueDeclaration resolvedValueDeclaration_l = n.getLeft().asNameExpr().resolve();
                JavaParserFieldDeclaration javaParserFieldDeclaration_r = (JavaParserFieldDeclaration) (resolvedValueDeclaration_r);
                JavaParserFieldDeclaration javaParserFieldDeclaration_l = (JavaParserFieldDeclaration) (resolvedValueDeclaration_l);
                Node node_right = javaParserFieldDeclaration_r.getWrappedNode();
                Node node_left = javaParserFieldDeclaration_l.getWrappedNode();
                if (node_left.equals(node_right)) {
                    n.replace(integerLiteralExpr);
                    change = true;
                }
            }
        }
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.setData(SYMBOL_RESOLVER_KEY, new JavaSymbolSolver(typeSolver));
        while (change) {
            change = false;
            compilationUnit.accept(new Finding_binary(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_enclose(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new Finding_binary_name(), JavaParserFacade.get(typeSolver));
        }
        return compilationUnit.toString();
    }
}
