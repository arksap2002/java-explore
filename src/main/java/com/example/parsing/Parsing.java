package com.example.parsing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class Parsing {
    public static void main(String[] args) throws IOException {
        System.out.println(parsing("/1.txt"));
    }

    public static String parsing(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        return compilationUnit.toString();
    }
}
