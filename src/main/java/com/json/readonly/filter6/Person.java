package com.json.readonly.filter6;

import java.util.List;

public class Person {
    private int age;
    private List<Address> addressList;

    private boolean allMatched;

    public boolean isAllMatched() {
        return allMatched;
    }


    // Getters and setters
    public String getName() {
        return "固定名称";
    }


    public int getAge() {
        return 18;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    // Additional getter without corresponding field
    public String getFullName() {
        return "full "+getName() ;
    }


}
