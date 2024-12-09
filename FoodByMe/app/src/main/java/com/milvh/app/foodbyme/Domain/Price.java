package com.milvh.app.foodbyme.Domain;

public class Price {
    private int Id;
    private String Value;

    public Price() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    @Override
    public String toString() {
        return Value ;
    }
}