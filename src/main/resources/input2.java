package com.example.parsing;

import java.util.Scanner;

public class SecondExample {
    public static int N = 17;

    public static void main(String[] args) {
        // write your code here
        PrintStream printStream = new PrintStream(System.out);
        java.io.PrintStream printStream1 = new PrintStream(System.out);
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