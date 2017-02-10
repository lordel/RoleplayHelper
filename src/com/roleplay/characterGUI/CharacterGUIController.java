package com.roleplay.characterGUI;

import com.roleplay.Character;
import com.roleplay.enums.CharacterTraits;
import com.roleplay.utils.BarValueAndColor;
import com.roleplay.utils.Die;
import com.roleplay.utils.GUIController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller in charge of the CharacterGUI actions.
 * This class is linked to the CharacterGUI.fxml file. It provides methods for actions performed by the user on the GUI.
 * This class extends GUIController and implements the Initializable interface.
 *
 * @see com.roleplay.utils.GUIController
 * @see javafx.fxml.Initializable
 */
public class CharacterGUIController extends GUIController implements Initializable {
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

    /**
     * Default initialize() method for Initializable interface.
     * This method generates a toggle group and links all the radio buttons to it so that only one can be selected at a
     * time. It initializes the ComboBox with the required items and sets the initial values of die roll, bonus and
     * total to 0.
     *
     * @see javafx.fxml.Initializable
     */
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

    /**
     * Sets this class' Character attribute and sets all the GUI values based on that Character.
     * This method calls to the superclass setCharacter() method to set the Character attribute and then sets the text
     * fields of the GUI to the correct values based on the information provided by "character".
     *
     * @param character Input Character to be used to set the values of the GUI elements.
     */
    @Override
    public void setCharacter(Character character) {
        super.setCharacter(character);
        setValues();
    }

    /**
     * Sets the text labels and progress bars based on the current values held by the character attribute of this class.
     */
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

        //Use the BarValueAndColor util to set both color and value of the progress bars
        BarValueAndColor.setBarValue(hpBar, character.getHpCurr(), character.getHpMax());
        BarValueAndColor.setBarValue(mpBar, character.getHpCurr(), character.getHpMax());
    }

    //Action Listeners--------------------------------------------------------------------------------------------------

    /**
     * Action listener method for the D4 button press.
     * This method is executed when the D4 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d4Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(4);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the D6 button press.
     * This method is executed when the D6 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d6Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(6);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the D8 button press.
     * This method is executed when the D8 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d8Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(8);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the D10 button press.
     * This method is executed when the D10 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d10Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(10);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the D12 button press.
     * This method is executed when the D12 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d12Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(12);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the D20 button press.
     * This method is executed when the D20 button is pressed. It randomly rolls the die using the Die class, gets the
     * bonus from the current Character, and adds it to the result of the roll to get the total. It then sets the values
     * of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void d20Pressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(20);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the Percent Die button press.
     * This method is executed when the Percent Die button is pressed. It randomly rolls the die using the Die class,
     * gets the bonus from the current Character, and adds it to the result of the roll to get the total. It then sets
     * the values of the roll, bonus, and total text labels to match the results.
     *
     * @see com.roleplay.utils.Die
     */
    @FXML
    private void percentPressed() {
        int bonusValue = character.getBonus(checkButtons());
        int rollValue = Die.roll(100);
        roll.setText(Integer.toString(rollValue));
        bonus.setText(Integer.toString(bonusValue));
        total.setText(Integer.toString(rollValue + bonusValue));
    }

    /**
     * Action listener method for the selection of an item from the ComboBox.
     * This method executes when an item is selected from the ComboBox list. It checks for a null value of the selected
     * item, which would result from the user selecting Separator items. If the value is not null the method performs
     * the necessary action based on the value selected by the user. It then proceeds to reset the selection of the
     * ComboBox.
     *
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void optionSelected() throws IOException {
        try {
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
                    mainClass.chooseScene(5);
                    Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
                    break;
            }
        } catch (NullPointerException | ClassCastException e) {
            //Do nothing and reset the selection as this means the separator was the selected value
            Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
        }
    }

    //Utilities---------------------------------------------------------------------------------------------------------

    /**
     * Returns CharacterTrait based on which radioButton is selected.
     * This method checks all radioButtons in the radioGroup to see which is selected. Based on which is selcted it
     * returns the corresponding CharacterTrait.
     *
     * @return Returns a CharacterTraits value.
     * @see com.roleplay.enums.CharacterTraits
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