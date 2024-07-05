package com.enums;

public class EnumDemo {
    public static void main(String[] args) {
        String method  ="air_query";
        System.out.println(null == FlightMethod.valueOf(method));
    }
}
