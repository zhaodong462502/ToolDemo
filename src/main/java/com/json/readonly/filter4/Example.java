package com.json.readonly.filter4;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("dynamicFilter")
class Example {
    public String getOnlyGetter() {
        return "onlyGetterValue";
    }
    private String normalField;
    public String getNormalField() {
        return normalField;
    }
    public void setNormalField(String normalField) {
        this.normalField = normalField;
    }
}
