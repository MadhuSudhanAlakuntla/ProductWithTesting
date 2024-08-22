package com.srvnn.ProductApplication.service;

import java.util.ArrayList;
import java.util.List;

public class Common {

    // Method to calculate factorial of a number
    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Number must be non-negative");
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Method to check if a string is a palindrome
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    // Method to find the prime numbers up to a given limit
    public List<Integer> findPrimes(int limit) {
        if (limit < 2) throw new IllegalArgumentException("Limit must be greater than or equal to 2");
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) isPrime[i] = true;
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }

    // Method to compute the nth Fibonacci number
    public int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Index must be non-negative");
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Method to perform a binary search on a sorted array
    public int binarySearch(int[] array, int target) {
        if (array == null) throw new IllegalArgumentException("Array cannot be null");
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Target not found
    }
}

