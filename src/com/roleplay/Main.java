package com.roleplay;

import com.roleplay.enums.Deities;
import com.roleplay.enums.DynamicGUIType;
import com.roleplay.enums.GUIs;
import com.roleplay.utils.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class which starts the application GUIs.
 * This class controls which GUI is displayed. The character information input by the user is stored in a private
 * attribute and shared with all the other GUIs when they are initialized. All GUIs used in conjunction with Main
 * should extend the GUIController class. This class' methods are used to switch the current scene displayed by the
 * application.
 *
 * @see com.roleplay.utils.GUIController
 */
public class Main extends Application {
    private Character character; //The program's character
    private Stage mainStage; //The application's stage
    private Deities deityChoice; //The deity chosen by the user. Used to style the application L&F

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            //Print an error when an exception propagates through all the application up to this point.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText("Fatal error in the application thread");
            alert.setContentText("There was an unexpected error with the application. The application will be aborted.");
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        loadScene("deityChoiceGUI/DeityChoiceGUI.fxml", DynamicGUIType.NULL);

        mainStage.setTitle("RolePlay Helper");
        mainStage.show();
    }

    //Setters-----------------------------------------------------------------------------------------------------------

    /**
     * Sets the deity for the program.
     * The choice of deity determines the overall look and feel of the application. This method sets the deity attribute
     * of this class.
     *
     * @param deityChoice the desired choice of Deity from the Deities enum.
     */
    public void setDeityChoice(Deities deityChoice) {
        this.deityChoice = deityChoice;
    }

    /**
     * Gets the character attribute of this class.
     *
     * @return The current Character attribute of this class is returned.
     */
    public Character getCharacter() {
        return character;
    }
    //Getters-----------------------------------------------------------------------------------------------------------

    /**
     * Sets the character of this class to the provided character.
     *
     * @param character the Character input that will replace the current character of this class.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    //Utility-----------------------------------------------------------------------------------------------------------

    /**
     * Method to change the current scene displayed by this Main class.
     * Based on the specified choice main will switch the current scene to a new one. The previous scene can be garbage
     * collected and the new scene is initialized from scratch loading the corresponding FXML file.
     *
     * @param choice GUI to be selected for displaying.
     */
    public void chooseScene(GUIs choice) {
        switch (choice) {
            case CHARACTER:
                loadScene("characterGUI/CharacterGUI.fxml", DynamicGUIType.NULL);
                break;
            case DAMAGE:
                loadScene("dynamicGUI/DynamicGUI.fxml", DynamicGUIType.DAMAGE);
                break;
            case MAGIC:
                loadScene("dynamicGUI/DynamicGUI.fxml", DynamicGUIType.MAGIC);
                break;
            case EXPERIENCE:
                loadScene("dynamicGUI/DynamicGUI.fxml", DynamicGUIType.EXPERIENCE);
                break;
            case HEALTH:
                loadScene("dynamicGUI/DynamicGUI.fxml", DynamicGUIType.HEALTH);
                break;
            case SAVING:
                loadScene("dynamicGUI/DynamicGUI.fxml", DynamicGUIType.SAVING);
                break;
            case VALUE_SETTING:
                loadScene("valueSettingGUI/ValueSettingGUI.fxml", DynamicGUIType.NULL);
                break;
        }
    }

    /**
     * Loads the scene specified by the fxmlPath.
     * Uses the specified fxmlPath to load a new scene based on the FXML file specified.
     *
     * @param fxmlPath path to the FXML file to be used to load the scene
     * @param guiType  the type of the GUI which will be loaded, used to determine the type of a DynamicGUI
     */
    private void loadScene(String fxmlPath, DynamicGUIType guiType) {
        //Loader and Scene are created using the standard JavaFX way of loading from FXML file
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Scene scene = null; //This makes sure scope is outside of try block.
        try {
            scene = new Scene(loader.load(), 650, 400);
        } catch (IOException e) {
            //Display error message and terminate app if loading fails.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error switching scene");
            alert.setHeaderText("Fatal error when switching scene.");
            alert.setContentText("There was a fatal error when loading a new scene or loading from FXML." +
                    " The application will be terminated.");
            alert.showAndWait();

            e.printStackTrace(); //Abort application
        }

        //Access the scene's controller and set main class and guiType for future access
        GUIController control = loader.getController();
        control.initialize(this, guiType);

        //Set the style of the scene based on the previously chosen deity
        setStyle(scene);

        //Set the main stage to show the newly created scene
        mainStage.setScene(scene);
    }

    /**
     * Sets the style for the input scene.
     * A CSS file is added to the input scene based on the deity that is currently set for this class.
     *
     * @param scene the scene to which the stylesheet will be added and applied.
     */
    private void setStyle(Scene scene) {
        if (deityChoice == null) return;
        switch (deityChoice) {
            case HYLIA:
                scene.getStylesheets().addAll("/com/roleplay/CSS/hylia.css");
                break;
            case NAYRU:
                scene.getStylesheets().addAll("/com/roleplay/CSS/nayru.css");
                break;
            case FARORE:
                scene.getStylesheets().addAll("/com/roleplay/CSS/farore.css");
                break;
            case DIN:
                scene.getStylesheets().addAll("/com/roleplay/CSS/din.css");
                break;
            case DEMISE:
                scene.getStylesheets().addAll("/com/roleplay/CSS/demise.css");
                break;
            case GHIRAHIM:
                scene.getStylesheets().addAll("/com/roleplay/CSS/ghirahim.css");
                break;
            case FIERCE:
                scene.getStylesheets().addAll("/com/roleplay/CSS/fierce.css");
                break;
            case MAJORA:
                scene.getStylesheets().addAll("/com/roleplay/CSS/majora.css");
                break;
        }
    }
}

