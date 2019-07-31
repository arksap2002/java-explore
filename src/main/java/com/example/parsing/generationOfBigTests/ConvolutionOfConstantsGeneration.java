package com.example.parsing.generationOfBigTests;

import java.util.Random;

public class ConvolutionOfConstantsGeneration {

    private static String[] words = {"a", "b", "c", "d", "e", "f", "g", "e", "k", "l", "m"};
    private static String[] operators = {"+", "-", "*"};


    public static void main(String[] args) {
        System.out.println(Generating_test());
    }

    private static String Generating_test() {
        Random random = new Random();
        int step = 30;
        return making_test(random, step);
    }


    private static String making_test(Random random, int number) {
        if (number < 0) {
            return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + Math.abs(random.nextInt()) % 100 + ")";
        }
        number--;
        int int_random = random.nextInt();
        if (int_random % 4 == 0) {
            return "(" + making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + words[Math.abs(random.nextInt()) % 11] + ")";
        } else {
            if (int_random % 4 == 1) {
                return "(" + Math.abs(random.nextInt()) % 100 + operators[Math.abs(random.nextInt()) % 3] + making_test(random, number) + ")";
            } else {
                return "(" + making_test(random, number) + operators[Math.abs(random.nextInt()) % 3] + making_test(random, number) + ")";
            }
        }
    }
}
