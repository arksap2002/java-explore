package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ChangingOutToArr {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }
    //
    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(MethodCallExpr.class).stream()
                .filter(f -> {
                    if (f.getScope().get().isFieldAccessExpr()) {
                        FieldAccessExpr g = f.getScope().get().asFieldAccessExpr();
                        if (g.getScope().isNameExpr()){
                            NameExpr h = g.getScope().asNameExpr();
                            return (h.getName().toString().equals("System")) && (g.getName().toString().equals("out")) && (f.getName().toString().equals("println"));
                        }
                    }
                    return false;
                })
                .forEach(f -> {
                    FieldAccessExpr g = f.getScope().get().asFieldAccessExpr();
                    g.setName("err");
                });
        return compilationUnit.toString();
    }
}
