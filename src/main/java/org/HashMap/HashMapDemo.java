package org.HashMap;

import com.google.common.collect.Maps;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String,String> map = Maps.newHashMapWithExpectedSize(7);
        System.out.println(map.size());
    }
}
