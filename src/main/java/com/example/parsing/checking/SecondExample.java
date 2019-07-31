package com.example.parsing.checking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecondExample {
    public static int N = 17;

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        PrintStream printStream = new PrintStream();
        java.io.PrintStream printStream1 = new java.io.PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int x = a + b;
        int y = a * b;
        Integer z = 3;
        printStream1.println(z);
        printStream.println(x);
        printStream.println("hello");
        System.out.println("Hello World!");
    }

    static class PrintStream {
        int point = 10;


        public static void println(Object object) {
            System.out.println(object);
        }
    }

    static class DeleteMe {
        int delete_number = 0;

        public static int delete_method() {
            return 1;
        }
    }
}