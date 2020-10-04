package com.example.juiceapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IngredientsUnitTest {

    IngrediantsDatabaseHelperClass ingrediantsDatabaseHelperClass;

    @Test
    public void testRetrieveEmployeeList() {
        List<IngrediantModelClass> expectedList = new ArrayList<>();
        List<IngrediantModelClass> actualList = new ArrayList<>();

        expectedList.add(new IngrediantModelClass(1, "Apples", "20", "Apple Supplies", "2020/3/5"));
        expectedList.add(new IngrediantModelClass(2, "Watermelon", "25", "Indra Supplies", "2020/5/5"));
        expectedList.add(new IngrediantModelClass(3, "Sugar", "10", "Apple Supplies", "2020/3/5"));
        expectedList.add(new IngrediantModelClass(4, "Mint", "5", "AAA Supplies", "2020/3/5"));

        actualList = ingrediantsDatabaseHelperClass.getEmployeeList();
        assertEquals(expectedList, actualList);
    }
}