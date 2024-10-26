package com.example.androidapp.model;

public class ObjectCount {

    private String objectName;
    private int count;

    public ObjectCount(String objectName, int count) {
        this.objectName = objectName;
        this.count = count;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
