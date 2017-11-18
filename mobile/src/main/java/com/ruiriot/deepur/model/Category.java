package com.ruiriot.deepur.model;

/**
 * Created by ruiri on 18-Nov-17.
 */

public class Category {

    public String id;
    public String description;
    public String image;

    public Category() {

    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Category(String id, String description, String image) {
        this.id = id;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
