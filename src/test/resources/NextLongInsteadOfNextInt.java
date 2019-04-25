package com.example.parsing;

import java.io.PrintStream;
import java.util.Scanner;

public class FirstExample {

    public static int N = 17;

    public static void main(String[] args) {
        // write your code here
        PrintStream printStream = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextLong();
        int b = scanner.nextLong();
        int x = a + b;
        int y = a * b;
        Integer z = 3;
        printStream.println(z);
        System.out.println("Hello World!");
    }
}
