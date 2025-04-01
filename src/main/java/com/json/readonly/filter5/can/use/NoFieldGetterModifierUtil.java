package com.json.readonly.filter5.can.use;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Set;

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
        module.setSerializerModifier(new NoFieldGetterModifier(targetClass, ignoredFields));
        objectMapper.registerModule(module);
        return objectMapper;
    }

    /**
     * 将对象转换为 JSON 字符串，排除指定类中无字段getter的属性。
     *
     * @param targetClass 需要排除无字段getter的类
     * @param object 要转换的对象
     * @param ignoredFields 需要忽略的字段集合
     * @return JSON 字符串
     * @throws RuntimeException 如果转换过程中发生异常
     */
    public static String convertObjectToJson(Class<?> targetClass, Object object, Set<String> ignoredFields) {
        ObjectMapper objectMapper = createObjectMapperWithNoFieldGetterModifier(targetClass, ignoredFields);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}
