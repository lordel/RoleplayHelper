package com.roleplay.utils;

import com.roleplay.enums.ProgressBarType;
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
     * percentage. Different colors are set for each ProgressBarType.
     *
     * @param bar     The bar which value and color will be set.
     * @param current The current value to be set for the bar.
     * @param max     The maximum possible value for the bar.
     * @param type    The progress bar type, will determine which color the bar is set to.
     */
    public static void setBarValue(ProgressBar bar, int current, int max, ProgressBarType type) {
        double percentage = (double) current / (double) max;
        switch (type) {
            //TODO: fix coloring for the bars
            case HP:
                if (percentage <= .25) {
                    bar.setStyle("-fx-accent:red;");
                } else if (percentage <= .5) {
                    bar.setStyle("-fx-accent:orange;");
                } else {
                    bar.setStyle("-fx-accent:green;");
                }
                break;
            case MP:
                if (percentage <= .25) {
                    bar.setStyle("-fx-accent:red;");
                } else if (percentage <= .5) {
                    bar.setStyle("-fx-accent:purple;");
                } else {
                    bar.setStyle("-fx-accent:blue;");
                }
                break;
            case EXP:
                if (percentage <= .25) {
                    bar.setStyle("-fx-accent:dark-green;");
                } else if (percentage <= .5) {
                    bar.setStyle("-fx-accent:green;");
                } else {
                    bar.setStyle("-fx-accent:light-green;");
                }
                break;
        }
        bar.setProgress(percentage);
    }
}