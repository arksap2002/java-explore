package main;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.DotPrinter;
import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class LearningJavaParser {
    public static String parsing(String filename) throws IOException {
        ClassLoader cl = new LearningJavaParser().run();
//        InputStream file = cl.getResourceAsStream("1.txt");
//        System.out.println(IOUtils.resourceToString("/1.txt", Charset.defaultCharset()));
        CompilationUnit cu = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(cu));
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    private ClassLoader run() {
        return getClass().getClassLoader();
    }
}
