package com.example.juiceapp;



import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ProductUnitTest {

    private DatabaseHelperClass databaseHelperClass;
    private ViewProducts viewProducts;

    @Test
    public void testCalculateTax() {

        //Price in rupees
        double price = 350;

        //Vat as a percentage of 100
        double vat = 5;

        //profit margin as a percentage of 100
        double margin = 15;

        //service tax as a percentage of 100
        double serviceTax = 8;

        double[] price_tax_values = Product.calculateProductPrice(price,vat, margin,serviceTax);

        int result = Product.sellingPrice(price_tax_values);

        assertEquals(447, result);
    }

   @Test
    public void productFieldsNotNullCheck() throws NullPointerException {
        String name = "Apple juice";
        String price = "250";
        String ingredients = "Apple";
        String rating = "3.4";

        boolean result = viewProducts.validationSuccessful(name,price,ingredients,rating);
        assertEquals(true, result);
    }

    @Test
    public void testInsertProduct(){

        Product product = new Product("Apple Juice","Juice","340","4.5","Apple");

        boolean check = databaseHelperClass.insertProduct(product);
        assertEquals(true, check);
    }

    @Test
    public void testGetAllProducts() {

        ArrayList<Product> productArrayList = null;

        //Manually creating all products in the database currently product values in the database
        productArrayList.add(new Product("Apple Juice","Juice","340","4.5","Apple"));
        productArrayList.add(new Product("Watermelon Juice","Juice","450","3.5","Watermelon"));
        productArrayList.add(new Product("Apple Juice","Juice","340","4.5","Apple"));
        productArrayList.add(new Product("Apple Juice","Juice","340","4.5","Apple"));

        ArrayList<Product> checkProductArrayList = databaseHelperClass.getAllProducts();
        assertEquals(checkProductArrayList, productArrayList);
    }

    @Test
    public void testGetProductViaID() {

        //The input id
        int id = 1;

        //The exact product details in the database
        Product expectedProduct = new Product("Apple Juice","Juice","340","4.5","Apple");
        expectedProduct.setID(String.valueOf(id));

        Product actualProduct = new Product();
        actualProduct = databaseHelperClass.getProductViaID(id);

        assertEquals(expectedProduct, actualProduct);
    }
}
