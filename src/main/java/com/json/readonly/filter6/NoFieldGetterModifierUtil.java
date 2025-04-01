package com.json.readonly.filter6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NoFieldGetterModifierUtil {

    /**
     * 创建一个配置了 NoFieldGetterModifier 的 ObjectMapper 实例。
     *
     * @param targetClass 需要排除无字段getter的类
     * @param ignoredFields 需要忽略的字段集合
     * @return 配置好的 ObjectMapper 实例
     */
    public static ObjectMapper createObjectMapperWithNoFieldGetterModifier(Class<?> targetClass, Set<String> ignoredFields) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // 如果目标类是集合类型，获取其泛型类型
        if (Collection.class.isAssignableFrom(targetClass)) {
            Class<?> elementType = Object.class; // 默认为Object
            // 注意：运行时需要实际的集合元素类型
            module.setSerializerModifier(new NoFieldGetterModifier(elementType, ignoredFields));
        } else {
            module.setSerializerModifier(new NoFieldGetterModifier(targetClass, ignoredFields));
        }
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /**
     * 将对象转换为 JSON 字符串，排除指定类中无字段getter的属性。
     *
     * @param targetClass 需要排除无字段getter的类
     * @param object 要转换的对象
     * @param ignoredFieldsStr 需要忽略的字段，多个字段用逗号分隔（如："field1,field2,field3"）
     * @return JSON 字符串
     * @throws RuntimeException 如果转换过程中发生异常
     */
    public static String convertObjectToJson(Class<?> targetClass, Object object, String ignoredFieldsStr) {
        // 将逗号分隔的字符串转换为Set
        Set<String> ignoredFields = new HashSet<>();
        if (ignoredFieldsStr != null && !ignoredFieldsStr.trim().isEmpty()) {
            ignoredFields.addAll(Arrays.asList(ignoredFieldsStr.split(",")));
            // 去除可能存在的空格
            ignoredFields = ignoredFields.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
        }

        // 如果对象是集合类型，获取集合元素的实际类型
        if (object instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) object;
            if (!collection.isEmpty()) {
                // 获取集合中第一个非空元素的类型
                Object firstElement = collection.stream()
                    .filter(item -> item != null)
                    .findFirst()
                    .orElse(null);
                if (firstElement != null) {
                    targetClass = firstElement.getClass();
                }
            }
        }
        
        ObjectMapper objectMapper = createObjectMapperWithNoFieldGetterModifier(targetClass, ignoredFields);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}
