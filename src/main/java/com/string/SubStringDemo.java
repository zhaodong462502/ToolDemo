package com.string;

public class SubStringDemo {
    public static void main(String[] args) {
        String baggageInfo = "免费托运行李500公斤";
        System.out.println(baggageInfo.indexOf("免费托运行李"));
        System.out.println(baggageInfo.substring(baggageInfo.indexOf("免费托运行李")+6,baggageInfo.indexOf("公斤")));
    }
}
