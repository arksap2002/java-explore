package com.example.parsing.problemTests.bigIntegerCode;

import java.math.BigInteger;
import java.util.Scanner;

public class BigInteger5 {
    public static void main(String[] args) {
        //https://codeforces.com/contest/929/problem/B
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger k = scanner.nextBigInteger();
        char[][] a = new char[n][12];
        BigInteger number = BigInteger.ZERO;
        for (int i = 0; i < n + 1; i++) {
            String s = scanner.nextLine();
            char[] arr = s.toCharArray();
            if (i != 0) {
                System.arraycopy(arr, 0, a[i - 1], 0, 12);
            }
        }
        int[][] zer = new int[10 * n][2];
        int[][] one = new int[10 * n][2];
        int[][] two = new int[10 * n][2];
        for (int i = 0; i < 10 * n; i++) {
            for (int j = 0; j < 2; j++) {
                zer[i][j] = -1;
                one[i][j] = -1;
                two[i][j] = -1;
            }
        }
        int numZer = 0;
        int numOne = 0;
        int numTwo = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 12; j++) {
                if (a[i][j] == '.') {
                    if (j == 0) {
                        if ((a[i][j + 1] == 'P') || (a[i][j + 1] == '.')) {
                            zer[numZer][0] = i;
                            zer[numZer][1] = j;
                            numZer++;
                        } else {
                            one[numOne][0] = i;
                            one[numOne][1] = j;
                            numOne++;
                        }
                    }
                    if (j == 11) {
                        if ((a[i][j - 1] == 'P') || (a[i][j - 1] == '.')) {
                            zer[numZer][0] = i;
                            zer[numZer][1] = j;
                            numZer++;
                        } else {
                            one[numOne][0] = i;
                            one[numOne][1] = j;
                            numOne++;
                        }
                    }
                    if ((j != 0) && (j != 11)) {
                        if (((a[i][j - 1] == '.') || (a[i][j - 1] == '-') || (a[i][j - 1] == 'P')) && ((a[i][j + 1] == '.') || (a[i][j + 1] == '-') || (a[i][j + 1] == 'P'))) {
                            zer[numZer][0] = i;
                            zer[numZer][1] = j;
                            numZer++;
                        } else {
                            if (((a[i][j - 1] == 'S') || (a[i][j + 1] == 'S')) && (a[i][j - 1] != a[i][j + 1])) {
                                one[numOne][0] = i;
                                one[numOne][1] = j;
                                numOne++;
                            } else {
                                two[numTwo][0] = i;
                                two[numTwo][1] = j;
                                numTwo++;
                            }
                        }
                    }
                }
                if (a[i][j] == 'S') {
                    if (j == 0) {
                        if (a[i][j + 1] != '.') {
                            number = number.add(BigInteger.ONE);
                        }
                    }
                    if (j == 11) {
                        if (a[i][j - 1] != '.') {
                            number = number.add(BigInteger.ONE);
                        }
                    }
                    if ((j != 0) && (j != 11)) {
                        if ((a[i][j - 1] != '.') && (a[i][j - 1] != '-')) {
                            number = number.add(BigInteger.ONE);
                        }
                        if ((a[i][j + 1] != '.') && (a[i][j + 1] != '-')) {
                            number = number.add(BigInteger.ONE);
                        }
                    }
                }
            }
        }
        if (k.compareTo(BigInteger.valueOf(numZer)) <= 0) {
            for (int i = 0; BigInteger.valueOf(i).compareTo(k) < 0; i++) {
                a[zer[i][0]][zer[i][1]] = 'x';
            }
        }
        if ((k.compareTo(BigInteger.valueOf(numZer)) > 0) && (k.compareTo(BigInteger.valueOf(numOne + numZer)) <= 0)) {
            number = number.add(k.subtract(BigInteger.valueOf(numZer)));
            for (int i = 0; i < numZer; i++) {
                a[zer[i][0]][zer[i][1]] = 'x';
            }
            for (int i = 0; BigInteger.valueOf(i).compareTo(k.subtract(BigInteger.valueOf(numZer))) < 0; i++) {
                a[one[i][0]][one[i][1]] = 'x';
            }
        }
        if (k.subtract(BigInteger.valueOf(numOne + numZer)).compareTo(BigInteger.ZERO) > 0) {
            number = number.add(BigInteger.valueOf(numOne).add((k.subtract(BigInteger.valueOf(numZer + numOne))).multiply(BigInteger.TWO)));
            for (int i = 0; i < numZer; i++) {
                a[zer[i][0]][zer[i][1]] = 'x';
            }
            for (int i = 0; i < numOne; i++) {
                a[one[i][0]][one[i][1]] = 'x';
            }
            for (int i = 0; BigInteger.valueOf(i).subtract(k.subtract(BigInteger.valueOf(numZer + numOne))).compareTo(BigInteger.ZERO) < 0; i++) {
                a[two[i][0]][two[i][1]] = 'x';
            }
        }
        System.out.println(number);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
}
