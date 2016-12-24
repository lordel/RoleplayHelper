package com.roleplay;

import com.roleplay.enums.Deities;
import com.roleplay.utils.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//TODO: add proper enums. one for FXML sheets
public class Main extends Application {
    //TODO: make javadoc properly for all classes and functions

    /**
     * The character value is kept by main and shared with the other guis
     */
    private Character character;
    private Stage mainStage;
    private Deities deityChoice;

    /**
     * The current scene displayed can be selected using the setScene() function.
     */

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

        loadScene("deityChoiceGUI/DeityChoiceGUI.fxml");

        mainStage.setTitle("Roleplay Helper");
        mainStage.show();
    }

    public void chooseScene(int choice) throws IOException {
        switch (choice) {
            case 1:
                loadScene("characterGUI/CharacterGUI.fxml");
                break;
            case 2:
                loadScene("valueSettingGUI/ValueSettingGUI.fxml");
                break;
            case 3:
                loadScene("restoreHealthGUI/RestoreHealthGUI.fxml");
                break;
            case 4:
                loadScene("damageGUI/DamageGUI.fxml");
            case 5:
                loadScene("saveXMLGUI/SaveXMLGUI.fxml");
                break;
        }
    }

    private void loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Scene scene = new Scene(loader.load(),650,400);
        GUIController control = loader.getController();
        control.setMainClass(this);
        control.setCharacter(character);
        setStyle(scene);
        mainStage.setScene(scene);
    }

    public void setCharacter(Character character){
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setDeityChoice(Deities choice){
        deityChoice = choice;
    }
    
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

