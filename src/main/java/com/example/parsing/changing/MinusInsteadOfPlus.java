package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.MINUS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.PLUS;

public class MinusInsteadOfPlus {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(BinaryExpr.class).stream()
                .filter(f -> f.getOperator().equals(PLUS))
                .forEach(f -> f.setOperator(MINUS));
        return compilationUnit.toString();
    }
}
