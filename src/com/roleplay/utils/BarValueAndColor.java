package com.roleplay.utils;

import javafx.scene.control.ProgressBar;

/**
 * Utility Class which can be used to set the value and color of a ProgressBar.
 * This class provides a method to set the value of a ProgressBar and automatically adjusts the color based on the
 * current percentage of the bar.
 */
public abstract class BarValueAndColor {
    /**
     * Sets a ProgressBar's value and automatically sets it's color.
     * This method sets the input ProgressBar's value and automatically changes its color based on the current
     * percentage. If the percentage is < 25% the color is set to red, if the percentage is < 50% it is set to yellow,
     * and if the percentage is higher it is set to green.
     * TODO: add new colors for MP and EXP bars.
     * @param bar The bar which value and color will be set.
     * @param current The current value to be set for the bar.
     * @param max The maximum possible value for the bar.
     */
    public static void setBarValue(ProgressBar bar, int current, int max) {
        double percentage = (double)current/(double)max;
        if(percentage <= .25) {
            bar.setStyle("-fx-accent:red;");
        }
        else if (percentage <= .5) {
            bar.setStyle("-fx-accent:orange;");
        }
        else {
            bar.setStyle("-fx-accent:green;");
        }
        bar.setProgress(percentage);
    }
}
