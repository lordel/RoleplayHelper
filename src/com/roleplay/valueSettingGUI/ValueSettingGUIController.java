package com.roleplay.valueSettingGUI;

import com.roleplay.Character;
import com.roleplay.utils.GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * File created by Lorenzo Delcroix on 12/19/2016 9:09.
 * Belongs to the package com.roleplay.valueSettingGUI of the RoleplayHelper project.
 */
public class ValueSettingGUIController extends GUIController{
    @FXML
    private TextField nameInput;
    @FXML
    private TextField strInput;
    @FXML
    private TextField dexInput;
    @FXML
    private TextField conInput;
    @FXML
    private TextField intelInput;
    @FXML
    private TextField wisInput;
    @FXML
    private TextField chaInput;
    @FXML
    private TextField maxHealthInput;
    @FXML
    private Label errorLabel;

    @FXML
    private void continuePressed(ActionEvent actionEvent) {
        //reset the color and error message to the default
        nameInput.setStyle("-fx-border-color: none;");
        strInput.setStyle("-fx-border-color: none;");
        dexInput.setStyle("-fx-border-color: none;");
        conInput.setStyle("-fx-border-color: none;");
        intelInput.setStyle("-fx-border-color: none;");
        wisInput.setStyle("-fx-border-color: none;");
        chaInput.setStyle("-fx-border-color: none;");
        maxHealthInput.setStyle("-fx-border-color: none;");
        errorLabel.setText("");

        if (nameInput.getText().equals("")) {
            nameInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }

        int str, dex, con, intel, wis, cha, hpMax;
        try {
            str = Integer.parseInt(strInput.getText());
            if (str < 0) {
                strInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            strInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            dex = Integer.parseInt(dexInput.getText());
            if (dex < 0) {
                dexInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            dexInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            con = Integer.parseInt(conInput.getText());
            if (con < 0) {
                conInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            conInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            intel = Integer.parseInt(intelInput.getText());
            if (intel < 0) {
                intelInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            intelInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            wis = Integer.parseInt(wisInput.getText());
            if (wis < 0) {
                wisInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            wisInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            cha = Integer.parseInt(chaInput.getText());
            if (cha < 0) {
                chaInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            chaInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            hpMax = Integer.parseInt(maxHealthInput.getText());
            if (hpMax < 0) {
                maxHealthInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            maxHealthInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        Character character = new Character(nameInput.getText(), str, dex, con, intel, wis, cha, hpMax);
        mainClass.setCharacter(character);

        try {
            mainClass.chooseScene(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error switching scene");
            System.out.println(e.getMessage());
            System.exit(-2);
        }
    }

    @FXML
    private void readXMLPressed(ActionEvent actionEvent) {
        Character character = new Character();
        character.readFromXML();
        nameInput.setText(character.getName());
        strInput.setText(Integer.toString(character.getStr()));
        dexInput.setText(Integer.toString(character.getDex()));
        conInput.setText(Integer.toString(character.getCon()));
        intelInput.setText(Integer.toString(character.getIntel()));
        wisInput.setText(Integer.toString(character.getWis()));
        chaInput.setText(Integer.toString(character.getCha()));
        maxHealthInput.setText(Integer.toString(character.getHpMax()));
    }

    @Override
    public void setCharacter(Character character) {
        super.setCharacter(character);
        setValues();
    }

    private void setValues() {
        if (character == null) return;
        nameInput.setText(character.getName());
        strInput.setText(Integer.toString(character.getStr()));
        dexInput.setText(Integer.toString(character.getDex()));
        conInput.setText(Integer.toString(character.getCon()));
        intelInput.setText(Integer.toString(character.getIntel()));
        wisInput.setText(Integer.toString(character.getWis()));
        chaInput.setText(Integer.toString(character.getCha()));
        maxHealthInput.setText(Integer.toString(character.getHpMax()));
    }
}
