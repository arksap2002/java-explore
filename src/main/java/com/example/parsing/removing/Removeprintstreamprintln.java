package com.example.parsing.removing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
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
    public static boolean flag = false;
    public static String name = "";
    public static ArrayList<MethodCallExpr> methodCallExprs = new ArrayList<>();
    static class Finding extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getType().isClassOrInterfaceType() && n.getType().asClassOrInterfaceType().getName().toString().equals("PrintStream")){
                flag = true;
                name = n.getName().toString();
            }
        }
    }
    static class Searching extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(MethodCallExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getName().toString().equals("println")) {
                if (n.getScope().get().isNameExpr() && n.getScope().get().asNameExpr().getName().toString().equals(name)) {
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
        compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
        if (flag){
            compilationUnit.accept(new Searching(), JavaParserFacade.get(typeSolver));
        }
        for (MethodCallExpr methodCallExpr : methodCallExprs) {
            methodCallExpr.removeForced();
        }
        return compilationUnit.toString();
    }
}
