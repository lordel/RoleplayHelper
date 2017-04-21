package com.roleplay.valueSettingGUI;

import com.roleplay.Character;
import com.roleplay.Main;
import com.roleplay.utils.GUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.bind.JAXBException;

/**
 * Controller in charge of the ValueSettingGUI actions.
 * This class is linked to the ValueSettingGUI.fxml file. It provides methods for actions performed by the user on
 * the GUI. The user is provided with the current values of the the statistics of his Character, which can be changed
 * by inputting new values in the textFields. Checks for invalid input are in place and error messages are displayed
 * in case of such situations. When the continue button is pressed any changes to the values are transferred to the
 * current Character. This class also allows for reading of the values from an XML file if one is present.
 * This class extends GUIController and implements the Initializable interface.
 *
 * @see com.roleplay.utils.GUIController
 */
public class ValueSettingGUIController extends GUIController {
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
    private TextField maxMpInput;
    @FXML
    private TextField maxExpInput;
    @FXML
    private Label errorLabel;

    //Override methods-------------------------------------------------------------------------------------------------

    /**
     * Initialize the controller
     * This method overrides the default initialize() method from GUIController. It calls to the super default
     * implementation and also sets the value of all the text fields to the values of the current character stats.
     *
     * @param mainClass The Main class instance to be stored.
     * @see com.roleplay.utils.GUIController
     */
    @Override
    public void initialize(Main mainClass) {
        super.initialize(mainClass);
        setValues(mainClass.getCharacter()); //set the initial values of all the text labels to match current character
    }

    //Action listeners--------------------------------------------------------------------------------------------------

    /**
     * Action listener for continue Button press.
     * This method will parse each field and check for valid input. If the input is invalid an error message is
     * displayed. If all the values are valid the Character in Main is replaced with one with the values obtained from
     * the TextFields in this class. The scene is the switched to the CharacterGUI.
     */
    @FXML
    private void continuePressed() {
        //reset the color and error message to the default
        nameInput.setStyle("-fx-border-color: none;");
        strInput.setStyle("-fx-border-color: none;");
        dexInput.setStyle("-fx-border-color: none;");
        conInput.setStyle("-fx-border-color: none;");
        intelInput.setStyle("-fx-border-color: none;");
        wisInput.setStyle("-fx-border-color: none;");
        chaInput.setStyle("-fx-border-color: none;");
        maxHealthInput.setStyle("-fx-border-color: none;");
        maxMpInput.setStyle("-fx-border-color: none;");
        maxExpInput.setStyle("-fx-border-color: none;");
        errorLabel.setText("");

        if (nameInput.getText().equals("")) {
            nameInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }

        int str, dex, con, intel, wis, cha, hpMax, mpMax, expMax;
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
        try {
            mpMax = Integer.parseInt(maxMpInput.getText());
            if (mpMax < 0) {
                maxMpInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            maxMpInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        try {
            expMax = Integer.parseInt(maxExpInput.getText());
            if (expMax < 0) {
                maxExpInput.setStyle("-fx-border-color: red;");
                errorLabel.setText("One or more of the fields does not contain a valid input");
                return;
            }
        } catch (NumberFormatException e) {
            maxExpInput.setStyle("-fx-border-color: red;");
            errorLabel.setText("One or more of the fields does not contain a valid input");
            return;
        }
        Character character = new Character(nameInput.getText(), str, dex, con, intel, wis, cha, hpMax, mpMax, expMax);
        //Set the main character to the newly generated one using the user input so the rest of the app can use it
        mainClass.setCharacter(character);

        mainClass.chooseScene(1); //switch scene to CharacterGUI
    }

    /**
     * Action listener for Read from file Button press.
     * This method reads the Character information stored in a XML file and sets the values of the TextLabels to reflect
     * the newly read values from the XML file.
     */
    @FXML
    private void readXMLPressed() {
        try {
            Character character = new Character();
            character.readFromXML();
            setValues(character);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Reading Data");
            alert.setHeaderText("XML Reading Error");
            alert.setContentText("Ooops, there was an error reading the information! Please try again.");
            alert.showAndWait();

            mainClass.chooseScene(2); //go back to this scene in case of error
        }
    }

    //Utility-----------------------------------------------------------------------------------------------------------

    /**
     * Sets all the TextLabel values to the corresponding values taken from the input Character.
     *
     * @param character the character to use to update the TextLabels values
     */
    private void setValues(Character character) {
        if (character == null) return;
        nameInput.setText(character.getName());
        strInput.setText(Integer.toString(character.getStr()));
        dexInput.setText(Integer.toString(character.getDex()));
        conInput.setText(Integer.toString(character.getCon()));
        intelInput.setText(Integer.toString(character.getIntel()));
        wisInput.setText(Integer.toString(character.getWis()));
        chaInput.setText(Integer.toString(character.getCha()));
        maxHealthInput.setText(Integer.toString(character.getHpMax()));
        maxMpInput.setText(Integer.toString(character.getMpMax()));
        maxExpInput.setText(Integer.toString(character.getExpMax()));
    }
}
