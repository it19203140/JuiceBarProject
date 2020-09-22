package com.example.juiceapp;

public class Product {
    private String ID;
    private String name;
    private String type;
    private String price;
    private String rating;
    private String ingredients;

    public Product() {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.rating = rating;
    }



    public Product(String name, String type, String price, String rating, String ingredients) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.ingredients = ingredients;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
