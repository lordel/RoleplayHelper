package com.roleplay;

import com.roleplay.enums.Deities;
import com.roleplay.utils.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//TODO: add proper enums. one for FXML sheets
//TODO: Add javadoc for everything

/**
 * Main class which starts the application GUIs.
 * This class controls which GUI is displayed. The character information inout by the user is stored in a private
 * attribute and shared with all the other GUIs when they are initialized. All GUIs used in conjunction with Main
 * should extend the GUIController class. This class' methods are used to switch the current scene displayed by the
 * application.
 */
public class Main extends Application {
    private Character character;
    private Stage mainStage;
    private Deities deityChoice;

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        loadScene("deityChoiceGUI/DeityChoiceGUIController.fxml");

        mainStage.setTitle("Roleplay Helper");
        mainStage.show();
    }

    /**
     * Method to change the current scene displayed by this Main class.
     * Based on the specified choice main will switch the current scene to a new one. The previous scene can be garbage
     * collected and the new scene is initialized from scratch loading the corresponding FXML file.
     * @param choice integer which specified the chosen scene.
     * @throws IOException thrown if the method is not able to load the required FXML for the selected scene.
     */
    public void chooseScene(int choice) throws IOException {
        switch (choice) {
            case 1:
                loadScene("characterGUI/CharacterGUI.fxml");
                break;
            case 2:
                loadScene("valueSettingGUI/ValueSettingGUI.fxml");
                break;
            case 3:
                loadScene("restoreHealthGUI/RestoreHealthGUIController.fxml");
                break;
            case 4:
                loadScene("DamageGUIController/DamageGUIController.fxml");
                break;
            case 5:
                loadScene("saveXMLGUI/SaveXMLGUIController.fxml");
                break;
        }
    }

    /**
     *  Loads the scene specified by the fxmlPath.
     *  Uses the specified fxmlPath to load a new scene based on the FXML file specified.
     * @param fxmlPath path to the FXML file to be used to load the scene
     * @throws IOException thrown if the method is not able to load the required FXML for the selected scene.
     */
    private void loadScene(String fxmlPath) throws IOException {
        //Loader and Scene are created using the standard JavaFX way of loading from FXML file
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Scene scene = new Scene(loader.load(),650,400);

        //Access the scene's controller and set both main class and character for future access
        GUIController control = loader.getController();
        control.setMainClass(this);
        control.setCharacter(character);

        //Set the style of the scene based on the previously chosen deity
        setStyle(scene);

        //Set the main stage to show the newly created scene
        mainStage.setScene(scene);
    }

    /**
     * Sets the character of this class to the provided character.
     * @param character the Character input that will replace the current character of this class.
     */
    public void setCharacter(Character character){
        this.character = character;
    }

    /**
     * Gets the character attribute of this class.
     * @return The current Character attribute of this class is returned.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets the deity for the program.
     * The choice of deity determines the overall look and feel of the application. This method sets the deity attribute
     * of this class.
     * @param choice the desired choice of Deity from the Deities enum.
     */
    public void setDeityChoice(Deities choice){
        deityChoice = choice;
    }

    /**
     * Sets the style for the input scene.
     * A CSS file is added to the input scene based on the deity that is currently set for this class.
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

