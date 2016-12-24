package com.roleplay.characterGUI;

import com.roleplay.Character;
import com.roleplay.utils.GUIController;
import com.roleplay.enums.CharacterTraits;
import com.roleplay.utils.BarValueAndColor;
import com.roleplay.utils.Die;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterGUIController extends GUIController implements Initializable {
    /**
     * //TODO:add information about attributes
     */
    private ToggleGroup radioGroup; //group to combine all radio buttons

    //Character attribute fields----------------------------------------------------------------------------------------
    @FXML
    private Label name;
    @FXML
    private Label str;
    @FXML
    private Label con;
    @FXML
    private Label dex;
    @FXML
    private Label intel;
    @FXML
    private Label wis;
    @FXML
    private Label cha;

    //Character bonus fields--------------------------------------------------------------------------------------------
    @FXML
    private Label strBonus;
    @FXML
    private Label dexBonus;
    @FXML
    private Label conBonus;
    @FXML
    private Label intelBonus;
    @FXML
    private Label wisBonus;
    @FXML
    private Label chaBonus;

    //Die roll fields---------------------------------------------------------------------------------------------------
    @FXML
    private Label roll;
    @FXML
    private Label bonus;
    @FXML
    private Label total;

    //Radio buttons-----------------------------------------------------------------------------------------------------
    @FXML
    private RadioButton noneRadio;
    @FXML
    private RadioButton strRadio;
    @FXML
    private RadioButton dexRadio;
    @FXML
    private RadioButton conRadio;
    @FXML
    private RadioButton intelRadio;
    @FXML
    private RadioButton wisRadio;
    @FXML
    private RadioButton chaRadio;

    //Progress bars-----------------------------------------------------------------------------------------------------
    @FXML
    private ProgressBar mpBar;
    @FXML
    private ProgressBar hpBar;

    //TODO: add experience bar

    @FXML
    private ComboBox<Object> comboBox; //combo box of objects to allow for Separator objects

    //Override methods--------------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Create a group for the radio buttons and add all of them to it------------------------------------------------
        radioGroup = new ToggleGroup();
        noneRadio.setToggleGroup(radioGroup);
        strRadio.setToggleGroup(radioGroup);
        dexRadio.setToggleGroup(radioGroup);
        conRadio.setToggleGroup(radioGroup);
        intelRadio.setToggleGroup(radioGroup);
        wisRadio.setToggleGroup(radioGroup);
        chaRadio.setToggleGroup(radioGroup);
        noneRadio.setSelected(true);

        //TODO: need to add labels with the value of health and mp!

        //set ComboBox items--------------------------------------------------------------------------------------------
        comboBox.getItems().addAll("Edit Values", "Restore Health", "Take Damage", "Use Magic", new Separator(),
                "Save Character Info"); //TODO: add implementation for magic points

        //Set initial  roll, bonus, and total to 0----------------------------------------------------------------------
        roll.setText("0");
        bonus.setText("0");
        total.setText("0");
    }

    //Setters-----------------------------------------------------------------------------------------------------------
    @Override
    public void setCharacter(Character character) {
        super.setCharacter(character);
        setValues();
    }

    private void setValues() {
        name.setText(character.getName());
        str.setText(Integer.toString(character.getStr()));
        strBonus.setText(Integer.toString(character.getBonus(CharacterTraits.STR)));
        dex.setText(Integer.toString(character.getDex()));
        dexBonus.setText(Integer.toString(character.getBonus(CharacterTraits.DEX)));
        con.setText(Integer.toString(character.getCon()));
        conBonus.setText(Integer.toString(character.getBonus(CharacterTraits.CON)));
        intel.setText(Integer.toString(character.getIntel()));
        intelBonus.setText(Integer.toString(character.getBonus(CharacterTraits.INTEL)));
        wis.setText(Integer.toString(character.getWis()));
        wisBonus.setText(Integer.toString(character.getBonus(CharacterTraits.WIS)));
        cha.setText(Integer.toString(character.getCha()));
        chaBonus.setText(Integer.toString(character.getBonus(CharacterTraits.CHA)));

        BarValueAndColor.setBarValue(hpBar, character.getHpCurr(), character.getHpMax());
        BarValueAndColor.setBarValue(mpBar, character.getHpCurr(), character.getHpMax());
    }

    //Action Listeners--------------------------------------------------------------------------------------------------
    @FXML
    private void d4Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(4);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void d6Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(6);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void d8Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(8);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void d10Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(10);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void d12Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(12);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void d20Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(20);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    @FXML
    private void percentPressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(100);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * TODO:add info
     * @throws IOException
     */
    @FXML
    private void optionSelected() throws IOException {
        //We check to make sure if the value is not null. This ensures that we only take action when user selects items
        if (comboBox.getValue() != null) {
            //reset the selection of the user selects the separator
            /*if (comboBox.getSelectionModel().getSelectedIndex() == 5){
                Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                return;
            }*/
            switch ((String) comboBox.getValue()) {
                case "Restore Health":
                    mainClass.chooseScene(3);
                    Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                    break;
                case "Take Damage":
                    mainClass.chooseScene(4);
                    Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                    break;
                case "Edit Values":
                    mainClass.chooseScene(2);
                    Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                    break;
                case "Save Character Info":
                    //mainClass.chooseScene(5);
                    character.saveToXML();
                    Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                    break;
            }
        }
    }

    //Utilities---------------------------------------------------------------------------------------------------------

    /**
     * TODO: add info
     * @return
     */
    private CharacterTraits checkButtons() {
        if (radioGroup.getSelectedToggle().equals(strRadio)) {
            return CharacterTraits.STR;
        } else if (radioGroup.getSelectedToggle().equals(dexRadio)) {
            return CharacterTraits.DEX;
        } else if (radioGroup.getSelectedToggle().equals(conRadio)) {
            return CharacterTraits.CON;
        } else if (radioGroup.getSelectedToggle().equals(intelRadio)) {
            return CharacterTraits.INTEL;
        } else if (radioGroup.getSelectedToggle().equals(wisRadio)) {
            return CharacterTraits.WIS;
        } else if (radioGroup.getSelectedToggle().equals(chaRadio)) {
            return CharacterTraits.CHA;
        } else {
            return CharacterTraits.NONE;
        }
    }
}