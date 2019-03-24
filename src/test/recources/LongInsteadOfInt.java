package com.example.parsing;

import java.io.PrintStream;
import java.util.Scanner;

public class FirstExample {

    public static long N = 17;

    public static void main(String[] args) {
        // write your code here
        PrintStream printStream = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextInt();
        long b = scanner.nextInt();
        long x = a + b;
        long y = a * b;
        Integer z = 3;
        printStream.println(z);
        System.out.println("Hello World!");
    }
}
