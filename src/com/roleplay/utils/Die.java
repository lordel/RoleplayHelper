package com.roleplay.utils;


import java.util.Random;
import java.util.Scanner;

/**
 * Utility Class which can be used to randomly generate die "rolls".
 * This class provides a method to execute different kinds of die rolls and returns the output of the rolls.
 */
public abstract class Die {
    /**
     * Simple roll of a die.
     * This method performs a simple random roll of a die. The number of sides of the die rolled is determined by the
     * "die" parameter.
     *
     * @param die The type of die to be rolled (number of sides of the die).
     * @return The result of the roll.
     */
    public static int roll(int die) {
        Random rand = new Random();
        return (rand.nextInt(die) + 1); //Random number between 1 and 'die'
    }

    /**
     * Simple roll of a die, with the automatic addition of a bonus.
     * This method performs a simple random roll of a die. The number of sides of the die rolled is determined by the
     * "die" parameter. The "bonus" parameter is added to the result of the roll.
     *
     * @param die   The type of die to be rolled (number of sides of the die).
     * @param bonus The bonus to be added to the roll.
     * @return The result of the roll plus bonus.
     */
    public static int roll(int die, int bonus) {
        Random rand = new Random();
        return ((rand.nextInt(die) + 1) + bonus); //Random number between 1 and 'die'
    }

    /**
     * Simple roll of a die, repeated a number of times.
     * This method performs a simple random roll of a die. The number of sides of the die rolled is determined by the
     * "die" parameter. The result of each roll is printed to the screen.
     *
     * @param die The type of die to be rolled (number of sides of the die).
     * @param num The number of times to perform the roll.
     */
    private static void rollNumTimes(int die, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("You rolled a " + roll(die));
        }
    }

    /**
     * Simple roll of a die, repeated a number of times, with addition of bonus.
     * This method performs a simple random roll of a die. The number of sides of the die rolled is determined by the
     * "die" parameter. The result of each roll is printed to the screen. Each roll has an added bonus specified by
     * the "bonus" parameter.
     *
     * @param die   The type of die to be rolled (number of sides of the die).
     * @param num   The number of times to perform the roll.
     * @param bonus The bonus to be added to the rolls.
     */
    public static void roll(int die, int num, int bonus) {
        Scanner in = new Scanner(System.in);
        if (bonus == 0) {
            rollNumTimes(die, num);
            return;
        }
        System.out.println("Will the bonus be added to every roll? Y/N");
        if (in.nextLine().equalsIgnoreCase("y")) {
            for (int i = 0; i < num; i++) {
                System.out.println("You rolled a " + (roll(die) + bonus));
            }
        } else {
            for (int i = 0; i < num; i++) {
                System.out.println("Would you like to add bonus just to this roll? Y/N");
                if (in.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("You rolled a " + (roll(die) + bonus));
                } else {
                    System.out.println("You rolled a " + roll(die));
                }
            }
        }
    }
}
