package javaparser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.printer.DotPrinter;
import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
public class RemoveClassMembers {
    public static void main(String[] args) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class)
                .forEach(Node::removeForced);
        compilationUnit.findAll(MethodDeclaration.class)
                .forEach(Node::removeForced);
        System.out.println(compilationUnit.toString());
    }
}
