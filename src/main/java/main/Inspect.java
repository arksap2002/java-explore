package main;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.DotPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Inspect {
    public static void main(String[] args) {
        // Parse the code you want to inspect:
        CompilationUnit cu = JavaParser.parse("class X { int x; }");
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(cu));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

