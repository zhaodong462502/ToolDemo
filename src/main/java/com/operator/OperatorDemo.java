package com.operator;

import java.math.BigInteger;

public class OperatorDemo {
    public static void main(String[] args) {
        BigInteger bigInteger1 = new BigInteger("123456789012345678901234567890");
        int i =9;
        long LONG_MASK = 0xffffffffL;
        System.out.println(i&LONG_MASK);


    }
}
