package com.example.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CaculatorTests {
    @Test
    public void addtionTest() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    private class Calculator {
        public int add(int a, int b) {
            return 5;
        }
    }
}
