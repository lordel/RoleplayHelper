package com.roleplay.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

@Deprecated public abstract class IntInput {
    public static int getIntInput() {
        Scanner in = new Scanner(System.in);
        int output;
        try {
            output =  in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("The value: \'" + in.nextLine() + "\' was invalid. Please enter an integer");
            output = getIntInput(); //go back and ask for the input
        }

        if(output < 0){
            System.out.println("Sorry you entered a negative number. Please enter a " +
                    "positive integer.");
            output = getIntInput();
        }
        return output;
    }
}
