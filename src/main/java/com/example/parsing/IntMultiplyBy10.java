package com.example.parsing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class IntMultiplyBy10 {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(IntegerLiteralExpr.class)
                .forEach(f -> f.setInt(f.asInt() * 10));
        return compilationUnit.toString();
    }
}
