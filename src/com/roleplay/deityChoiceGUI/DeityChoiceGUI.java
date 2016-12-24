package com.roleplay.deityChoiceGUI;

import com.roleplay.utils.GUIController;
import com.roleplay.enums.Deities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * File created by Lorenzo Delcroix on 12/21/2016 2:54.
 * Belongs to the package com.roleplay.deityChoiceGUI of the RoleplayHelper project.
 */
public class DeityChoiceGUI extends GUIController{
    @FXML
    private void fiercePressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.FIERCE);
        mainClass.chooseScene(2);
    }

    @FXML
    private void majoraPressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.MAJORA);
        mainClass.chooseScene(2);
    }

    @FXML
    private void demisePressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.DEMISE);
        mainClass.chooseScene(2);
    }

    @FXML
    private void ghirahimPressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.GHIRAHIM);
        mainClass.chooseScene(2);
    }

    @FXML
    private void farorePressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.FARORE);
        mainClass.chooseScene(2);
    }

    @FXML
    private void hyliaPressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.HYLIA);
        mainClass.chooseScene(2);
    }

    @FXML
    private void nayruPressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.NAYRU);
        mainClass.chooseScene(2);
    }

    @FXML
    private void dinPressed(ActionEvent actionEvent) throws IOException {
        mainClass.setDeityChoice(Deities.DIN);
        mainClass.chooseScene(2);
    }
}
