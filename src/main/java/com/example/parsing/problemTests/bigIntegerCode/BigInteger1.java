package com.example.parsing.problemTests.bigIntegerCode;

import java.math.BigInteger;
import java.util.Scanner;

public class BigInteger1 {
    public static void main(String[] args) {
        //https://codeforces.com/contest/857/problem/A
        Scanner in = new Scanner(System.in);
        BigInteger n = in.nextBigInteger();
        if (n.mod((BigInteger.ONE).add(BigInteger.TWO)).equals(BigInteger.ZERO)){
            System.out.println(n.divide((BigInteger.ONE).add(BigInteger.TWO)).subtract(BigInteger.ONE) + " " + n.divide((BigInteger.ONE).add(BigInteger.TWO)) + " " + (n.divide((BigInteger.ONE).add(BigInteger.TWO)).add(BigInteger.ONE)));
        }else{
            System.out.println(-1);
        }
    }
}
