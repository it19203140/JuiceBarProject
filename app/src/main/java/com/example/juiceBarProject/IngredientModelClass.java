package com.example.juiceBarProject;

public class IngredientModelClass {

    Integer id;
    String name;
    String quantity;
    String supplier;
    String date;

    public IngredientModelClass(String name, String quantity, String supplier, String date) {
        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.date = date;
    }

    public IngredientModelClass(Integer id, String name, String quantity, String supplier, String date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.date = date;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
