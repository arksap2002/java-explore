package com.example.parsing.changing.convolution_of_constants;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.javaparser.ast.Node.SYMBOL_RESOLVER_KEY;

public class Main {
    public static boolean change = true;

    public static void main(String[] args) throws IOException {
        System.out.println(transformResource("/input5.java"));
    }

    public static String transformResource(String filename) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(IOUtils.resourceToString(filename, Charset.defaultCharset()));
        TypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        compilationUnit.setData(SYMBOL_RESOLVER_KEY, new JavaSymbolSolver(typeSolver));
        int number = 0;
        while (change) {
            change = false;
//            compilationUnit.accept(new Finding(), JavaParserFacade.get(typeSolver));
            compilationUnit.accept(new New_Finding(), JavaParserFacade.get(typeSolver));
            number += 1;
        }
        System.out.println(number);
        return compilationUnit.toString();
    }
}
