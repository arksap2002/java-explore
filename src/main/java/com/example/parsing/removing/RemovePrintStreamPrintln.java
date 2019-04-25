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

public class RemovePrintStreamPrintln {
    public static boolean flag = false;
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<MethodCallExpr> methodCallExprs = new ArrayList<>();
    public static ArrayList<VariableDeclarator> variableDeclarators = new ArrayList<>();

    static class Finding extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getType().isClassOrInterfaceType() && n.getType().asClassOrInterfaceType().getName().toString().equals("PrintStream")) {
                flag = true;
                names.add(n.getName().toString());
            }
        }
    }

    static class Finding_int extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(VariableDeclarator n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getType().isPrimitiveType() && n.getType().asPrimitiveType().getType().toString().equals("INT")) {
                variableDeclarators.add(n);
            }
        }
    }

    static class Searching extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(MethodCallExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getName().toString().equals("println")) {
                for (int i = 0; i < names.size(); i++){
                    if (n.getScope().get().isNameExpr() && n.getScope().get().asNameExpr().getName().toString().equals(names.get(i))) {
                        methodCallExprs.add(n);
                    }
                }
            }
        }
    }

    static class Searching_int extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(MethodCallExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getName().toString().equals("println")) {
                for (int j = 0; j < names.size(); j++) {
                    if (n.getScope().get().isNameExpr() && n.getScope().get().asNameExpr().getName().toString().equals(names.get(j))) {
                        for (int i = 0; i < n.getArguments().size(); i++) {
                            if (n.getArguments().get(i).isNameExpr()) {
                                for (VariableDeclarator variableDeclarator : variableDeclarators) {
                                    if (n.getArguments().get(i).asNameExpr().getName().toString().equals(variableDeclarator.getName().toString())) {
                                        methodCallExprs.add(n);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static class Searching_string extends VoidVisitorAdapter<JavaParserFacade> {
        @Override
        public void visit(MethodCallExpr n, JavaParserFacade javaParserFacade) {
            super.visit(n, javaParserFacade);
            if (n.getName().toString().equals("println")) {
                for (int j = 0; j < names.size(); j++) {
                    if (n.getScope().get().isNameExpr() && n.getScope().get().asNameExpr().getName().toString().equals(names.get(j))) {
                        for (int i = 0; i < n.getArguments().size(); i++) {
                            if (n.getArguments().get(i).isStringLiteralExpr()) {
                                methodCallExprs.add(n);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println(removing_int("/input2.java"));
    }

    public static String removing_all(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
        if (flag) {
            compilationUnit.accept(new Searching(), JavaParserFacade.get(typeSolver));
        }
        for (MethodCallExpr methodCallExpr : methodCallExprs) {
            methodCallExpr.removeForced();
        }
        return compilationUnit.toString();
    }

    public static String removing_int(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
        compilationUnit.accept(new Finding_int(), JavaParserFacade.get(typeSolver));
        if (flag) {
            compilationUnit.accept(new Searching_int(), JavaParserFacade.get(typeSolver));
        }
        for (MethodCallExpr methodCallExpr : methodCallExprs) {
            methodCallExpr.removeForced();
        }
        return compilationUnit.toString();
    }

    public static String removing_string(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
        if (flag) {
            compilationUnit.accept(new Searching_string(), JavaParserFacade.get(typeSolver));
        }
        for (MethodCallExpr methodCallExpr : methodCallExprs) {
            methodCallExpr.removeForced();
        }
        return compilationUnit.toString();
    }
}
