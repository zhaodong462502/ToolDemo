package com.json.readonly.filter6;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class ExampleUsage {
    public static void main(String[] args) {
        // 创建一个 Person 对象
        Person person = new Person();

        Address address = new Address();
        address.setName("测试街道名称");
        address.setCity("Anytown");

        person.setAddressList(Arrays.asList(address));


        // 创建一个 Person 对象
        Person person2 = new Person();

        Address address2 = new Address();
        address2.setName("22——测试街道名称");
        address2.setCity("22--Anytown");

        person2.setAddressList(Arrays.asList(address2));

        // 定义需要忽略的字段
        HashSet<String> ignoredFields = new HashSet<>();
//        ignoredFields.add("fullName"); // 假设我们想忽略 fullName 字段

        // 使用 NoFieldGetterModifierUtil 创建 ObjectMapper
        ObjectMapper objectMapper = NoFieldGetterModifierUtil.createObjectMapperWithNoFieldGetterModifier(Person.class, ignoredFields);

        try {
            // 将 Person 对象转换为 JSON 字符串
            String jsonString = objectMapper.writeValueAsString(person);
            System.out.println("[ jsonString ]:"+jsonString);

              // 创建测试数据
        List<Person> personList = Arrays.asList(person, person2);
        String jsonForList = NoFieldGetterModifierUtil.convertObjectToJson(
            Person.class,
            personList,
                ""
        );
        System.out.println("[ jsonForList ]:"+jsonForList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
