package com.json.readonly.filter5.can.use;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Example example = new Example();
        example.setNormalField("normalValue");

        // **正常 JSON 输出**
        String normalJson = mapper.writeValueAsString(example);
        System.out.println("正常 JSON 输出: " + normalJson);

        // **注册 `NoFieldGetterModifier`**
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new NoFieldGetterModifier(Example.class));

        ObjectMapper filteredMapper = new ObjectMapper();
        filteredMapper.registerModule(module);

        // **过滤后 JSON 输出**
        String filteredJson = filteredMapper.writeValueAsString(example);
        System.out.println("过滤后 JSON 输出: " + filteredJson);

        // 假设 Example 是需要排除无字段getter属性的类
        Set<String> ignoredFields = new HashSet<>();
//        ignoredFields.add("onlyGetter");
        ignoredFields.add("anotherFieldToIgnore");

        try {
            String jsonString = NoFieldGetterModifierUtil.convertObjectToJson(Example.class, example, ignoredFields);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
