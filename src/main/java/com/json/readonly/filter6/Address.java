package com.json.readonly.filter6;

public class Address {

    private String name;

    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters
    public String getStreet() {
        return "测试街道";
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
