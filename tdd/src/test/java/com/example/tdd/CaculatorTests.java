package com.example.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CaculatorTests {
    @Test
    public void addtionTest() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    @Test
    public void subtractionTest() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void multipleTest() {
        Calculator calculator = new Calculator();
        assertEquals(9, calculator.mul(3, 3));
    }

    @Test
    public void dividedTest() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.div(6, 2));
        assertThrows(IllegalStateException.class, () -> calculator.div(6, 0));
    }

    private class Calculator {
        public int add(int a, int b) {
            return (a + b);
        }

        public int sub(int a, int b) {
            return (a - b);
        }

        public int mul(int a, int b) {
            return (a * b);
        }

        public int div(int a, int b) {
            if (b == 0) {
                throw new IllegalStateException("division by zero");
            }
            else {
                return (a / b);
            }
        }
    }
}
