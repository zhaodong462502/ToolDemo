package com.json.readonly.filter2;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomFilter extends SimpleBeanPropertyFilter {

    private Set<String> readOnlyProperties;

    public CustomFilter(Set<String> readOnlyProperties) {
        this.readOnlyProperties = readOnlyProperties;
    }

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        return !readOnlyProperties.contains(writer.getName());
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        return !readOnlyProperties.contains(writer.getName());
    }

    public static Set<String> findReadOnlyProperties(Class<?> clazz, ObjectMapper mapper) {
        SerializationConfig config = mapper.getSerializationConfig();
        BeanDescription beanDesc = config.introspect(config.constructType(clazz));
        return beanDesc.findProperties().stream()
                .filter(prop -> {
                    AnnotatedMember accessor = prop.getAccessor();
                    if (accessor instanceof AnnotatedMethod) {
                        String setterName = "set" + ((AnnotatedMethod) accessor).getName().substring(3);
                        return beanDesc.findMethod(setterName, null) == null;
                    }
                    return false;
                })
                .map(prop -> prop.getName())
                .collect(Collectors.toSet());
    }
}
// 使用示例
class Example {
    public String getOnlyGetterField() { return "value"; } // 假设这是getter方法
    public String normalField;
}


