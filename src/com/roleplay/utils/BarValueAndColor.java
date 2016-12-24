package com.roleplay.utils;

import javafx.scene.control.ProgressBar;

/**
 * File created by Lorenzo Delcroix on 12/5/2016 21:50.
 * Belongs to the package com.roleplay.utils of the RoleplayHelper project.
 */
public abstract class BarValueAndColor {
    public static void setBarValue(ProgressBar bar, int current, int max) {
        float percentage = (float)current/(float)max;
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
