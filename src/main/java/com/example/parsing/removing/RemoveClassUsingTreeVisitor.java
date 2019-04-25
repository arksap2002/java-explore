package com.example.parsing.removing;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveClassUsingTreeVisitor {
    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input2.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        //

        return compilationUnit.toString();
    }

}
