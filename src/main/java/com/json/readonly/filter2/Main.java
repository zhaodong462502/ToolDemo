package com.json.readonly.filter2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Example example = new Example();
        example.normalField = "test";
        // 正常 JSON 输出
        String normalJson = mapper.writeValueAsString(example);
        System.out.println("正常 JSON 输出: " + normalJson);
        // 不输出只有 get 方法的属性
        Set<String> readOnlyProperties = CustomFilter.findReadOnlyProperties(Example.class,mapper);
        PropertyFilter filter = new CustomFilter(readOnlyProperties);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        // 这里 filterId 可以随意指定，因为没有使用注解关联
        filterProvider.addFilter("customFilterId", filter);
        mapper.setFilterProvider(filterProvider);
        String filteredJson = mapper.writeValueAsString(example);
        System.out.println("过滤后 JSON 输出: " + filteredJson);
    }
}