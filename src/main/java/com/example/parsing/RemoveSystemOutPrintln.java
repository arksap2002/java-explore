package com.example.parsing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveSystemOutPrintln {
    public static void main(String[] args) throws IOException {
        System.out.println(removing("/1.txt"));
    }
//
    public static String removing(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(MethodCallExpr.class).stream()
                .filter(f -> {
                    FieldAccessExpr g = f.asFieldAccessExpr();
                    NameExpr h = g.asNameExpr();
                    return (h.getName().toString().equals("System")) && (g.getName().toString().equals("out")) && (f.getName().toString().equals("println"));
                })
                .forEach(Node::remove);
        return compilationUnit.toString();
    }
}
