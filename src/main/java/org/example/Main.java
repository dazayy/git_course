package org.example;

public class Main {
    public static void main(String[] args) {


        int a = 10;
        int b = 15;
        System.out.println(sumNumbers(a, b));

    }

    public static int sumNumbers(int a, int b) {
        return a + b;
    }

    public static int divideNumbers(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

}