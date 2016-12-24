package com.roleplay.utils;


import java.util.Random;
import java.util.Scanner;

public abstract class Die {
    //simple single roll of die
    public static int roll(int die){
        Random rand= new Random();
        return (rand.nextInt(die) + 1); //Random number between 1 and 'die'
    }

    //simple roll with added bonus
    public static int roll(int die, int bonus){
        Random rand= new Random();
        return ((rand.nextInt(die) + 1) + bonus); //Random number between 1 and 'die'
    }
    private static void rollNumTimes(int die, int num){
        for (int i = 0; i < num; i++) {
            System.out.println("You rolled a " + roll(die));
        }
    }

    //Method to do die roll with addition of bonus
    public static void roll(int die, int num, int bonus){
        Scanner in = new Scanner(System.in);
        if(bonus == 0){
            rollNumTimes(die, num);
            return;
        }
        System.out.println("Will the bonus be added to every roll? Y/N");
        if(in.nextLine().equalsIgnoreCase("y")) {
            for (int i = 0; i < num; i++) {
                System.out.println("You rolled a " + (roll(die) + bonus));
            }
        } else {
            for (int i = 0; i < num ; i++) {
                System.out.println("Would you like to add bonus just to this roll? Y/N");
                if(in.nextLine().equalsIgnoreCase("y")){
                    System.out.println("You rolled a " + (roll(die) + bonus));
                }else {
                    System.out.println("You rolled a " + roll(die));
                }
            }
        }
    }
}
