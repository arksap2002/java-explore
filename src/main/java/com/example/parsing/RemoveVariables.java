package com.example.parsing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveVariables {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(FieldDeclaration.class)
                .forEach(Node::removeForced);
        compilationUnit.findAll(VariableDeclarationExpr.class)
                .forEach(Node::removeForced);
        return compilationUnit.toString();
    }
}
