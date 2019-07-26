package com.example.parsing.generationOfBigTests;

import java.util.Random;

public class ConvolutionOfConstantsGeneration {

    public static void main(String[] args) {
        System.out.println(Generating_test());
    }

    public static String Generating_test() {
        String[] words = {"a", "b", "c", "d", "e", "f", "g", "e", "k", "l", "m"};
        String[] operators = {"+", "-", "*"};
        Random random = new Random();
        int number = 100;
        return making_test(words, operators, random, number) + operators[Math.abs(random.nextInt()) % 3] + making_test(words, operators, random, number);
    }

    private static String making_test(String[] words, String[] operators, Random random, int number) {
        if (number < 0) {
            return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + Math.abs(random.nextInt()) % 100 + ")";
        }
        number--;
        if (random.nextInt() % 2 == 0) {
            return "(" + making_test(words, operators, random, number) + operators[Math.abs(random.nextInt()) % 3] + words[Math.abs(random.nextInt()) % 11] + ")";
        } else {
            return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + making_test(words, operators, random, number) + ")";
        }
    }
}
