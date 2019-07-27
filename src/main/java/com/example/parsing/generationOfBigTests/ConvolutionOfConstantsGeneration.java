package com.example.parsing.generationOfBigTests;

import java.util.Random;

public class ConvolutionOfConstantsGeneration {

    private static String[] words = {"a", "b", "c", "d", "e", "f", "g", "e", "k", "l", "m"};
    private static String[] operators = {"+", "-", "*"};


    public static void main(String[] args) {
        System.out.println(Generating_test());
    }

    public static String Generating_test() {
        Random random = new Random();
        int step = 100;
        return new_making_test(random, step);
    }

    private static String new_making_test(Random random, int number) {
        if (number < 0) {
            return "(" + making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + making_test(random, number) + ")";
        }
        number--;
        if (random.nextInt() % 2 == 0) {
            return "(" + making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + new_making_test(random, number) + ")";
        } else {
            return "(" + new_making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + making_test(random, number) + ")";
        }
    }

    private static String making_test(Random random, int number) {
        if (number < 0) {
            return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + Math.abs(random.nextInt()) % 100 + ")";
        }
        number--;
        if (random.nextInt() % 2 == 0) {
            return "(" + making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + words[Math.abs(random.nextInt()) % 11] + ")";
        } else {
            return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + making_test(random, number) + ")";
        }
    }
}
