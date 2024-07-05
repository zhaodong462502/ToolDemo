package com.bigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(65);
        BigDecimal b2 = new BigDecimal(35);
        BigDecimal result = b1.subtract(b2).divide(b1,2,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(0,RoundingMode.HALF_UP);
        System.out.println(result.toString()+"%");
    }
}
