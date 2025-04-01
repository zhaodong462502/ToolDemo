package com.json.readonly.filter2;

import com.alibaba.fastjson.JSON;

public class ExampleOut {
    public String getOnlyGetterField() { return "value"; } // 假设这是getter方法
    public String normalField;

    public static void main(String[] args) {
        ExampleOut example = new ExampleOut();
        example.normalField = "test";
        String json = JSON.toJSONString(example);
        System.out.println(json);
    }
}