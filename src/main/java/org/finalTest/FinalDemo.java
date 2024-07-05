package org.finalTest;

public class FinalDemo {
    public static void main(String[] args) {
        MyFinal myFinal = new MyFinal(3);
        MyFinal myFinal2 = new MyFinal(4);
        myFinal = myFinal2;
        System.out.println(myFinal.getValue());
    }
}
