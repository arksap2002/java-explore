package main;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class Generate {
    public static void main(String[] args) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));
        compilationUnit.findAll(FieldDeclaration.class).stream()
                .filter(f -> f.isPublic() && !f.isStatic())
                .forEach(f -> System.out.println("Check field at line " +
                        f.getRange().map(r -> r.begin.line).orElse(-1)));
//        ClassOrInterfaceDeclaration myClass = compilationUnit
//                .addClass("MyClass")
//                .setPublic(true);
//        myClass.addField(int.class, "A_CONSTANT", PUBLIC, STATIC);
//        myClass.addField(String.class, "/1.txt", PRIVATE);
//        String code = myClass.toString();
//        System.out.println(code);
    }
}
