package com.georgios;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter intervals: ");
        Scanner myScanner = new Scanner(System.in);
        String enteredIntervals = myScanner.next();
        System.out.println("You entered: " + enteredIntervals);
    }
}
