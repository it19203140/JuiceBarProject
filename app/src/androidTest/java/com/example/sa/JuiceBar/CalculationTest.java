package com.example.sa.JuiceBar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculationTest {

    @Test
    public void testcalculatefinal(){
        int result = Calculation.calculateFinal(200,4);
        assertEquals(800,result);
    }
}
