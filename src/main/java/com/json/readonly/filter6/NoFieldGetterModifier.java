package com.json.readonly.filter6;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的 BeanSerializerModifier，用于排除没有对应字段的 getter 方法。
 * 这个类会在序列化过程中忽略那些只有 getter 方法而没有对应字段的属性。
 */
public class NoFieldGetterModifier extends BeanSerializerModifier {
    private final Class<?> targetClass;
    private final Set<String> excludedFields;
    private final Set<String> ignoredFields;

    /**
     * 构造函数，初始化目标类和需要排除的字段集合。
     *
     * @param targetClass 需要排除无字段 getter 的类
     */
    public NoFieldGetterModifier(Class<?> targetClass) {
        this(targetClass, new HashSet<>());
    }

    /**
     * 构造函数，初始化目标类、需要排除的字段集合以及需要忽略的字段集合。
     *
     * @param targetClass 需要排除无字段 getter 的类
     * @param ignoredFields 需要忽略的字段集合
     */
    public NoFieldGetterModifier(Class<?> targetClass, Set<String> ignoredFields) {
        this.targetClass = targetClass;
        this.ignoredFields = ignoredFields;
        this.excludedFields = findNoFieldGetters(targetClass);
    }

    /**
     * 重写 modifySerializer 方法，用于修改序列化器。
     *
     * @param config 序列化配置
     * @param beanDesc Bean 描述
     * @param serializer 原始的 JsonSerializer
     * @return 修改后的 JsonSerializer
     */
    @Override
    public JsonSerializer<?> modifySerializer(
            SerializationConfig config,
            BeanDescription beanDesc,
            JsonSerializer<?> serializer) {
        // 检查当前 Bean 类是否为目标类，并且序列化器是 BeanSerializerBase 的实例
        if (beanDesc.getBeanClass() == targetClass && serializer instanceof BeanSerializerBase) {
            // 返回一个新的 CustomBeanSerializer 实例
            return new CustomBeanSerializer((BeanSerializerBase) serializer, excludedFields);
        }
        // 如果不是目标类或不是 BeanSerializerBase 的实例，则返回原始的序列化器
        return serializer;
    }

    /**
     * 递归收集类及其父类的所有字段名称。
     *
     * @param clazz 类
     * @param fieldNames 字段名称集合
     * @param processedClasses 已处理的类集合，用于防止循环引用
     */
    private void collectFieldNames(Class<?> clazz, Set<String> fieldNames, Set<Class<?>> processedClasses) {
        if (clazz == null || clazz.isPrimitive() || clazz.getName().startsWith("java.") 
            || processedClasses.contains(clazz)) {
            return;
        }
        
        processedClasses.add(clazz);
        
        for (Field field : clazz.getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        collectFieldNames(clazz.getSuperclass(), fieldNames, processedClasses);
    }

    /**
     * 递归收集类及其父类的所有属性描述符，并找出没有对应字段的 getter 方法。
     *
     * @param clazz 类
     * @param result 结果集合
     * @param fieldNames 字段名称集合
     * @param processedClasses 已处理的类集合，用于防止循环引用
     */
    private void collectPropertyDescriptors(Class<?> clazz, Set<String> result, Set<String> fieldNames,
                                          Set<Class<?>> processedClasses) {
        if (clazz == null || clazz.isPrimitive() || clazz.getName().startsWith("java.") 
            || processedClasses.contains(clazz)) {
            return;
        }

        processedClasses.add(clazz);

        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propName = propertyDescriptor.getName();
                Method getter = propertyDescriptor.getReadMethod();
                
                if (getter != null && !fieldNames.contains(propName) && 
                    !"class".equals(propName) && !ignoredFields.contains(propName)) {
                    // 检查方法名是否以get或is开头
                    String methodName = getter.getName();
                    if (methodName.startsWith("get") || methodName.startsWith("is")) {
                        // 对于is方法，确保它返回boolean类型
                        if (methodName.startsWith("is")) {
                            Class<?> returnType = getter.getReturnType();
                            if (returnType != boolean.class && returnType != Boolean.class) {
                                continue; // 跳过非boolean类型的is方法
                            }
                        }
                        result.add(propName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        collectPropertyDescriptors(clazz.getSuperclass(), result, fieldNames, processedClasses);
    }

    /**
     * 查找指定类及其相关类中没有对应字段的 getter 方法。
     *
     * @param clazz 需要检查的类
     * @return 包含没有对应字段的 getter 方法名称的集合
     */
    private Set<String> findNoFieldGetters(Class<?> clazz) {
        Set<String> result = new HashSet<>();
        Set<String> fieldNames = new HashSet<>();
        Set<Class<?>> processedClasses = new HashSet<>();

        // 递归获取所有字段名称
        collectFieldNames(clazz, fieldNames, processedClasses);

        // 重置已处理类集合
        processedClasses.clear();

        // 递归获取所有属性描述符
        collectPropertyDescriptors(clazz, result, fieldNames, processedClasses);

        return result;
    }

    /**
     * 自定义的 BeanSerializerBase 子类，用于排除没有对应字段的 getter 方法。
     */
    static class CustomBeanSerializer extends BeanSerializerBase {
        private final Set<String> excludedFields;
        private final Class<?> targetClass;

        /**
         * 构造函数，初始化原始的 BeanSerializerBase 和需要排除的字段集合。
         *
         * @param src 原始的 BeanSerializerBase 实例
         * @param excludedFields 需要排除的字段集合
         */
        protected CustomBeanSerializer(BeanSerializerBase src, Set<String> excludedFields) {
            super(src);
            this.excludedFields = excludedFields;
            this.targetClass = src.handledType();
        }

        /**
         * 重写 withObjectIdWriter 方法，返回一个新的 CustomBeanSerializer 实例。
         *
         * @param objectIdWriter ObjectIdWriter 实例
         * @return 新的 CustomBeanSerializer 实例
         */
        @Override
        public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
            return new CustomBeanSerializer(this, excludedFields);
        }

        /**
         * 重写 withIgnorals 方法，返回一个新的 CustomBeanSerializer 实例。
         *
         * @param toIgnore 需要忽略的字段集合
         * @return 新的 CustomBeanSerializer 实例
         */
        @Override
        protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
            Set<String> newIgnore = new HashSet<>(excludedFields);
            if (toIgnore != null) {
                newIgnore.addAll(toIgnore);
            }
            return new CustomBeanSerializer(this, newIgnore);
        }

        /**
         * 重写 withByNameInclusion 方法，返回一个新的 CustomBeanSerializer 实例。
         *
         * @param included 包含的字段集合
         * @param excluded 排除的字段集合
         * @return 新的 CustomBeanSerializer 实例
         */
        @Override
        protected BeanSerializerBase withByNameInclusion(Set<String> included, Set<String> excluded) {
            return new CustomBeanSerializer(this, excludedFields);
        }

        /**
         * 重写 serialize 方法，序列化对象时排除没有对应字段的 getter 方法。
         *
         * @param bean 要序列化的对象
         * @param gen JsonGenerator 实例
         * @param provider SerializerProvider 实例
         * @throws java.io.IOException 如果序列化过程中发生异常
         */
        @Override
        public void serialize(Object bean, com.fasterxml.jackson.core.JsonGenerator gen,
                              com.fasterxml.jackson.databind.SerializerProvider provider) throws java.io.IOException {
            gen.writeStartObject();
            for (BeanPropertyWriter writer : _props) {
                String propertyName = writer.getName();
                Class<?> propertyType = writer.getType().getRawClass();
                
                if (!excludedFields.contains(propertyName) || writer.getMember().getDeclaringClass() != targetClass) {
                    try {
                        // 处理集合类型
                        if (Collection.class.isAssignableFrom(propertyType)) {
                            Object propertyValue = writer.get(bean);
                            if (propertyValue != null) {
                                Collection<?> collection = (Collection<?>) propertyValue;
                                if (!collection.isEmpty()) {
                                    // 获取集合中第一个非空元素的类型
                                    Object firstElement = collection.stream()
                                        .filter(item -> item != null)
                                        .findFirst()
                                        .orElse(null);
                                    
                                    if (firstElement != null) {
                                        Class<?> elementType = firstElement.getClass();
                                        // 如果元素是自定义类型（非基本类型和非Java标准库类型）
                                        if (!elementType.isPrimitive() && !elementType.getName().startsWith("java.")) {
                                            gen.writeFieldName(propertyName);
                                            gen.writeStartArray();
                                            // 为集合中的每个元素创建新的序列化器
                                            for (Object element : collection) {
                                                if (element != null) {
                                                    NoFieldGetterModifier modifier = new NoFieldGetterModifier(elementType);
                                                    JsonSerializer<Object> serializer = provider.findValueSerializer(elementType);
                                                    if (serializer instanceof BeanSerializerBase) {
                                                        new CustomBeanSerializer((BeanSerializerBase) serializer, 
                                                            modifier.excludedFields).serialize(element, gen, provider);
                                                    } else {
                                                        serializer.serialize(element, gen, provider);
                                                    }
                                                } else {
                                                    gen.writeNull();
                                                }
                                            }
                                            gen.writeEndArray();
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                        // 处理非集合类型
                        else if (!propertyType.isPrimitive() && !propertyType.getName().startsWith("java.")) {
                            Object propertyValue = writer.get(bean);
                            if (propertyValue != null) {
                                gen.writeFieldName(propertyName);
                                NoFieldGetterModifier modifier = new NoFieldGetterModifier(propertyType);
                                JsonSerializer<Object> serializer = provider.findValueSerializer(propertyType);
                                if (serializer instanceof BeanSerializerBase) {
                                    new CustomBeanSerializer((BeanSerializerBase) serializer, 
                                        modifier.excludedFields).serialize(propertyValue, gen, provider);
                                    continue;
                                }
                            }
                        }
                        
                        // 处理基本类型、Java标准库类型或空集合
                        writer.serializeAsField(bean, gen, provider);
                        
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            gen.writeEndObject();
        }

        /**
         * 重写 asArraySerializer 方法，返回当前实例。
         *
         * @return 当前实例
         */
        @Override
        public BeanSerializerBase asArraySerializer() {
            return this;
        }

        /**
         * 重写 withFilterId 方法，返回当前实例。
         *
         * @param o 过滤器 ID
         * @return 当前实例
         */
        @Override
        public BeanSerializerBase withFilterId(Object o) {
            return this;
        }

        /**
         * 重写 withProperties 方法，返回当前实例。
         *
         * @param beanPropertyWriters 属性写入器数组
         * @param beanPropertyWriters1 属性写入器数组
         * @return 当前实例
         */
        @Override
        protected BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriters, BeanPropertyWriter[] beanPropertyWriters1) {
            return this;
        }
    }
}
