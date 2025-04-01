package com.json.readonly.filter4;

import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class NoFieldGetterFilter extends SimpleBeanPropertyFilter {
    private final Set<String> noFieldGetters;
    public NoFieldGetterFilter(Class<?> clazz) {
        this.noFieldGetters = findNoFieldGetters(clazz);
    }
    public Set<String> findNoFieldGetters(Class<?> clazz) {
        Set<String> result = new HashSet<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Set<String> fieldNames = new HashSet<>();

            // ✅ 获取所有字段名称
            for (Field field : clazz.getDeclaredFields()) {
                fieldNames.add(field.getName());
            }

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propName = propertyDescriptor.getName();
                Method getter = propertyDescriptor.getReadMethod();

                // ✅ 过滤掉没有对应字段的 getter
                if (getter != null && !fieldNames.contains(propName) && !"class".equals(propName)) {
                    result.add(propName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected boolean include(PropertyWriter writer) {
        boolean result = !noFieldGetters.contains(writer.getName());
        System.out.println("检查属性: " + writer.getName() + ", 是否包含: " + result);
        return result;
    }
}
