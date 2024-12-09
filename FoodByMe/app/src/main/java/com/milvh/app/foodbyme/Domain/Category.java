package com.milvh.app.foodbyme.Domain;

public class Category {
    private int Id;
    private String ImagePath;

    private String Name;

    public Category() {
    }

    public Category(int id, String ImagePath, String Name) {
        this.Id = Id;
        this.ImagePath = ImagePath;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
