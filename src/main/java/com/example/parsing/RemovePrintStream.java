package com.example.parsing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RemovePrintStream {
    public static String PrintStreamName;
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }
    public static String transformResource(String filename) throws IOException {
        PrintStreamName = "";
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(ImportDeclaration.class).stream()
                .filter(f -> f.getName().toString().equals("PrintStream"))
                .forEach(Node::removeForced);
        compilationUnit.findAll(VariableDeclarator.class).stream()
                .filter(f -> {
                    if (f.getType().isClassOrInterfaceType()){
                        ClassOrInterfaceType g = f.getType().asClassOrInterfaceType();
                        if (g.getName().toString().equals("PrintStream")) {
                            PrintStreamName = f.getName().toString();
                            return true;
                        }
                    }
                    return false;
                })
                .forEach(Node::removeForced);
        compilationUnit.findAll(MethodCallExpr.class).stream()
                .filter(f -> {
                    if (f.getScope().get().isNameExpr()){
                        NameExpr g = f.getScope().get().asNameExpr();
                        return g.getName().toString().equals(PrintStreamName);
                    }
                    return false;
                })
                .forEach(Node::removeForced);
        return compilationUnit.toString();
    }
}
