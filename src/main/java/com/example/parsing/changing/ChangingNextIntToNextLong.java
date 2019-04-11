package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ChangingNextIntToNextLong {
    private static String scanner_name = "";
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }
    //
    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(VariableDeclarator.class).stream()
                .filter(f -> {
                    if (f.getType().isClassOrInterfaceType()) {
                        ClassOrInterfaceType g = f.getType().asClassOrInterfaceType();
                        if (g.getName().toString().equals("Scanner")){
                            scanner_name = f.getName().toString();
                            return true;
                        }
                    }
                    return false;
                })
                .forEach(f -> f.setName(scanner_name));
        compilationUnit.findAll(MethodCallExpr.class).stream()
                .filter(f -> {
                    if (f.getScope().get().isNameExpr()) {
                        NameExpr g = f.getScope().get().asNameExpr();
                        return f.getName().toString().equals("nextInt") && g.getName().toString().equals(scanner_name);
                    }
                    return false;
                })
                .forEach(f -> f.setName("nextLong"));
        return compilationUnit.toString();
    }
}
