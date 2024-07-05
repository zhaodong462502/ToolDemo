package com.Collections;

import java.util.HashSet;
import java.util.Set;

public class CollectionDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");
        set1.add("C");
        set1.add("D");


        System.out.println(set1.equals(set));
    }
}
