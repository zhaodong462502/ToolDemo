package com.json.readonly.filter4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Example example = new Example();
        example.setNormalField("normalValue");

        // ✅ 1. 先确定需要排除的字段
        NoFieldGetterFilter filter = new NoFieldGetterFilter(Example.class);
        Set<String> excludedFields = filter.findNoFieldGetters(Example.class);

        // ✅ 2. 创建过滤器，排除无字段 getter
        SimpleFilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("dynamicFilter", SimpleBeanPropertyFilter.serializeAllExcept(excludedFields));

        // ✅ 3. 使用 `ObjectWriter` 过滤 JSON
        ObjectWriter writer = mapper.writer(filterProvider);

        // **正常 JSON 输出**
        String normalJson = mapper.writeValueAsString(example);
        System.out.println("正常 JSON 输出: " + normalJson);

        // **过滤后 JSON 输出**
        String filteredJson = writer.writeValueAsString(example);
        System.out.println("过滤后 JSON 输出: " + filteredJson);
    }
}