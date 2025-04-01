package com.json.readonly.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFilter;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John");

        SerializeConfig config = new SerializeConfig();
        config.addFilter(CustomPropertyFilter.class, new CustomPropertyFilter());

        String jsonString = JSON.toJSONString(person, config, SerializerFeature.PrettyFormat);
        System.out.println(jsonString);
    }
}

@JsonFilter("customFilter")
class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() { // 没有对应的字段定义
        return "30";
    }
}
