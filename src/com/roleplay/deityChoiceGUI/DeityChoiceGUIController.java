package com.roleplay.deityChoiceGUI;

import com.roleplay.enums.Deities;
import com.roleplay.utils.GUIController;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller in charge of the DeityChoiceGUI actions.
 * This class is linked to the DeityChoiceGUI.fxml file. It provides methods for actions performed by the user on the
 * GUI. The user is asked to select a deity preference out of the available choices.
 * This class extends GUIController and implements the Initializable interface.
 * @see com.roleplay.utils.GUIController
 * @see javafx.fxml.Initializable
 */
public class DeityChoiceGUIController extends GUIController{
    //Action listeners--------------------------------------------------------------------------------------------------
    /**
     * Action listener method for the Fierce button press.
     * This method executes when the Fierce GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void fiercePressed() throws IOException {
        mainClass.setDeityChoice(Deities.FIERCE);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Majora button press.
     * This method executes when the Majora GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void majoraPressed() throws IOException {
        mainClass.setDeityChoice(Deities.MAJORA);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Demise button press.
     * This method executes when the Demise GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void demisePressed() throws IOException {
        mainClass.setDeityChoice(Deities.DEMISE);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Ghirahim button press.
     * This method executes when the Ghirahim GUI button is pressed. It sets Main's deity choice to the correct value
     * and switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void ghirahimPressed() throws IOException {
        mainClass.setDeityChoice(Deities.GHIRAHIM);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Farore button press.
     * This method executes when the Farore GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void farorePressed() throws IOException {
        mainClass.setDeityChoice(Deities.FARORE);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Hylia button press.
     * This method executes when the Hylia GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void hyliaPressed() throws IOException {
        mainClass.setDeityChoice(Deities.HYLIA);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Nayru button press.
     * This method executes when the Nayru GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void nayruPressed() throws IOException {
        mainClass.setDeityChoice(Deities.NAYRU);
        mainClass.chooseScene(2);
    }

    /**
     * Action listener method for the Din button press.
     * This method executes when the Din GUI button is pressed. It sets Main's deity choice to the correct value and
     * switches the scene to the next one in the program.
     * @throws IOException Possibly thrown by Main's chooseScene() method when it is called.
     * @see com.roleplay.Main
     */
    @FXML
    private void dinPressed() throws IOException {
        mainClass.setDeityChoice(Deities.DIN);
        mainClass.chooseScene(2);
    }
}
