package com.json.readonly.filter3;

import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

class Example {
    public String getOnlyGetterField() {
        return "value";
    }

    public String normalField;
}

class CustomFilter extends SimpleBeanPropertyFilter {
    private final Set<String> readOnlyProperties;

    public CustomFilter(Set<String> readOnlyProperties) {
        this.readOnlyProperties = readOnlyProperties;
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        return !readOnlyProperties.contains(writer.getName());
    }

    public static Set<String> findReadOnlyProperties(Class<?> clazz) {
        Set<String> readOnlyProps = new HashSet<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method getter = propertyDescriptor.getReadMethod();
                Method setter = propertyDescriptor.getWriteMethod();
                if (getter != null && setter == null) {
                    readOnlyProps.add(propertyDescriptor.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readOnlyProps;
    }
}


