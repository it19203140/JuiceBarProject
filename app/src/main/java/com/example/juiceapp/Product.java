package com.example.juiceapp;

public class Product {
    private String ID;
    private String name;
    private String type;
    private String price;
    private String rating;
    private String ingredients;

    public static double[] calculateProductPrice(double price, double vat, double margin, double serviceTax){

        double[] arr = new double[4];

        arr[0] = price;
        arr[1] = arr[0] + (arr[0] * margin)/100;
        arr[2] = arr[1] + (arr[1] * serviceTax)/100;
        arr[3] = arr[2] + (arr[2] * vat)/100;

        return arr;
    }

    public static int sellingPrice(double[] arr) {
        int count = 0;
        for(int x= 0 ; x<4; x++) {
            count = (int) (count + arr[x]);
        }

        return count;
    }

    public Product() {
    }

    public Product(String name, String type, String price, String rating, String ingredients) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.ingredients = ingredients;
    }

    public Product(String id, String name, String type, String price, String rating, String ingredients) {
        this.ID = id;
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
