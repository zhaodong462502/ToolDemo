package com.json.readonly.filter5.can.use;

import com.fasterxml.jackson.annotation.JsonFilter;

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
