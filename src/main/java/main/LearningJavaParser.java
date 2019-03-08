package main;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.printer.DotPrinter;
import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class LearningJavaParser {
    public static void main(String[] args) throws IOException {
        System.out.println(remove_variables("1.txt"));
    }

    public static String parsing(String filename) throws IOException {
        CompilationUnit cu = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(cu));
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_class_members(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
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
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_class_members.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_methods(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(MethodDeclaration.class)
                .forEach(Node::removeForced);
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_methods.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_fields(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class)
                .forEach(Node::removeForced);
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_fields.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_variables(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class)
                .forEach(Node::removeForced);
        compilationUnit.findAll(VariableDeclarationExpr.class)
                .forEach(Node::removeForced);
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_variables.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_int(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class).stream()
                .filter(f -> f.getElementType().toString().startsWith("int"))
                .forEach(Node::removeForced);
        compilationUnit.findAll(VariableDeclarationExpr.class).stream()
                .filter(f -> f.getElementType().toString().startsWith("int"))
                .forEach(Node::removeForced);
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_int.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String remove_integer(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class).stream()
                .filter(f -> f.getElementType().toString().startsWith("Integer"))
                .forEach(Node::removeForced);
        compilationUnit.findAll(VariableDeclarationExpr.class).stream()
                .filter(f -> f.getElementType().toString().startsWith("Integer"))
                .forEach(Node::removeForced);
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast_remove_integer.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }

    public static String change_int(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString("/" + filename, Charset.defaultCharset()));
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast2.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        compilationUnit.findAll(FieldDeclaration.class).stream()
                .filter(f -> f.getElementType().toString().startsWith("int"))
                .forEach(f -> {
                    String newName = "long";});
        printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter("ast.dot");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(compilationUnit));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IOUtils.resourceToString("/" + filename, Charset.defaultCharset());
    }
}
