package org.optional;

import org.apache.commons.lang.StringUtils;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        String testStr = "";
         boolean flag=  Optional.of(new OptionDto("111")).map(OptionDto::getName).map(StringUtils::isNotBlank).orElse(false);
        System.out.println(flag);
    }
}
