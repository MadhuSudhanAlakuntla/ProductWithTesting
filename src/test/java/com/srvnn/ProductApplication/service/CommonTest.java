package com.srvnn.ProductApplication.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CommonTest {

    @InjectMocks
    private Common common;

    @Test
    public void testFactorial_throwsException() {
        int n = -1;
        assertThrows(IllegalArgumentException.class, () -> common.factorial(n));
    }

    @Test
    public void testFactorial_success() {
        int n = 5;
        long result = common.factorial(n);
        assertEquals(120, result);
    }


    @Test
    public void testFibonacci() {
        int n = -1;
        assertThrows(IllegalArgumentException.class, () -> common.fibonacci(n));
    }
}
