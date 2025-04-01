package com.json.readonly.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class CustomReadOnlyFilter extends SimpleBeanPropertyFilter {

    private final Map<Class<?>, Set<String>> readOnlyPropertiesCache = new HashMap<>();

    // 获取指定类中的只读属性（即只有getter没有setter的属性）
    private Set<String> getReadOnlyProperties(Class<?> clazz) {
        if (!readOnlyPropertiesCache.containsKey(clazz)) {
            readOnlyPropertiesCache.put(clazz, findReadOnlyProperties(clazz));
        }
        return readOnlyPropertiesCache.get(clazz);
    }

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        Class<?> declaringClass = writer.getMember().getDeclaringClass();
        return !getReadOnlyProperties(declaringClass).contains(writer.getName());
    }

    public static Set<String> findReadOnlyProperties(Class<?> clazz) {
        Set<String> fieldNames = Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());

        return Arrays.stream(clazz.getMethods())
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0)
                .map(m -> {
                    String setterName = "set" + m.getName().substring(3);
                    try {
                        Method setter = clazz.getMethod(setterName, m.getReturnType());
                        return null; // 如果找到了setter方法，则返回null表示该属性不是只读
                    } catch (NoSuchMethodException e) {
                        String propertyName = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
                        Field field = null;
                        try {
                            field = clazz.getDeclaredField(propertyName);
                        } catch (NoSuchFieldException ex) {
                            // 字段不存在，返回null
                            return null;
                        }
                        // 确保字段存在且没有对应的setter方法
                        return field != null ? propertyName : null;
                    }
                })
                .filter(Objects::nonNull) // 过滤掉非只读属性
                .collect(Collectors.toSet());
    }

}

// 使用示例
class NestedExample {
    public String getNestedField() { return "nestedValue"; } // 只有getter方法
}

@JsonFilter("customFilter")
class Example {
    public String getOnlyGetterField() { return "value"; } // 只有getter方法
    public NestedExample getNestedExample() { return new NestedExample(); }
    public String getNormalField() { return "test"; }
    public void setNormalField(String normalField) { /* setter */ }
}


