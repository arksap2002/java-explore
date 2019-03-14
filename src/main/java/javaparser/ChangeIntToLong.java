package javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.PrimitiveType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ChangeIntToLong {
    public static void main(String[] args) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));
        compilationUnit.findAll(VariableDeclarator.class).stream()
                .filter(f -> f.getType().equals(PrimitiveType.intType()))
                .forEach(f -> f.setType(PrimitiveType.longType()));
        System.out.println(compilationUnit.toString());
    }
}
