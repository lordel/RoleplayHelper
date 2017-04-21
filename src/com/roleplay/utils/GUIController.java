package com.roleplay.utils;

import com.roleplay.Main;
import com.roleplay.enums.DynamicGUIType;

/**
 * Abstract class providing the basic framework for Controllers used in this project.
 * This class is used to ensure all Controllers in the project have a common structure which can be used to access Main
 * and its methods from within the controller. It also enables Main to refer to the general class GUIController when
 * initializing all Controller classes.
 */
public abstract class GUIController {
    protected Main mainClass;
    protected DynamicGUIType guiType;

    /**
     * Initializes and sets the main class for this GUIController.
     * This method is used to store the mainClass as a protected field which can later be used by this class to access
     * Main's methods. This method can also be used to initialize any values of the Controller.
     *
     * @param mainClass The Main class instance to be stored.
     */
    public void initialize(Main mainClass, DynamicGUIType guiType) {
        this.mainClass = mainClass;
        this.guiType = guiType;
    }
}
