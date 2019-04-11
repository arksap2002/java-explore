package com.example.parsing.removing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Removeprintstreamprintln {
    public static ArrayList<MethodCallExpr> methodCallExprs = new ArrayList<>();
    static class TypeCalculatorVisitor extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(MethodCallExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getName().toString().equals("println")) {
                if (n.getScope().get().isNameExpr() && n.getScope().get().asNameExpr().getName().toString().equals("printStream")) {
                    methodCallExprs.add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(removing("/input2.java"));
    }

    public static String removing(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.accept(new TypeCalculatorVisitor(), JavaParserFacade.get(typeSolver));
        for (int i = 0; i < methodCallExprs.size(); i++){
            methodCallExprs.get(i).removeForced();
        }
        return compilationUnit.toString();
    }
}
