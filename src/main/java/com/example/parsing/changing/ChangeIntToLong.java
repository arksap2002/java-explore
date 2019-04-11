package com.example.parsing.changing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.PrimitiveType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ChangeIntToLong {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        compilationUnit.findAll(VariableDeclarator.class).stream()
                .filter(f -> f.getType().equals(PrimitiveType.intType()))
                .forEach(f -> f.setType(PrimitiveType.longType()));
        return compilationUnit.toString();
    }
}
