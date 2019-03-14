package javaparser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RemoveMethods {
    public static void main(String[] args) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));
                compilationUnit.findAll(MethodDeclaration.class)
                .forEach(Node::removeForced);
        System.out.println(compilationUnit.toString());
    }
}
