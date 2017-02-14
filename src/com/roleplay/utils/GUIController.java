package com.roleplay.utils;

import com.roleplay.Main;

/**
 * Abstract class providing the basic framework for Controllers used in this project.
 * This class is used to ensure all Controllers in the project have a common structure which can be used to access Main
 * and its methods from within the controller. It also enables Main to refer to the general class GUIController when
 * initializing all Controller classes.
 */
public abstract class GUIController {
    protected Main mainClass;

    /**
     * Sets the main class for this GUIController.
     * This method is used to store the mainClass as a protected field which can late be used by this class to access
     * Main's methods.
     * @param mainClass The Main class instance to be stored.
     */
    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }
}
