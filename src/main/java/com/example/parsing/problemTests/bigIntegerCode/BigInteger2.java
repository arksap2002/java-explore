package com.example.parsing.problemTests.bigIntegerCode;

import java.math.BigInteger;
import java.util.Scanner;

public class BigInteger2 {
    public static void main(String[] args) {
        //https://codeforces.com/contest/886/problem/B
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        int[] a = new int[n];
        BigInteger[] b = new BigInteger[200002];
        for (int i = 0; i < 200002; i++) {
            b[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            b[a[i]] = BigInteger.ONE;
        }
        for (int i = 0; i < 200002; i++) {
            if (b[i].equals(BigInteger.ONE)) {
                sum++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (sum == 1) {
                break;
            }
            if (b[a[i]].equals(BigInteger.ONE)) {
                b[a[i]] = BigInteger.ZERO;
                sum--;
            }
        }
        for (int i = 0; i < 200001; i++) {
            if (b[i].equals(BigInteger.ONE)) {
                System.out.println(i);
                break;
            }
        }
    }
}
