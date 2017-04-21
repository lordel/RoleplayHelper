package com.roleplay.damageGUI;

import com.roleplay.Character;
import com.roleplay.Main;
import com.roleplay.enums.DynamicGUIType;
import com.roleplay.enums.GUIs;
import com.roleplay.utils.GUIController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller in charge of the DamageGUI actions.
 * This class is linked to the DamageGUI.fxml file. It provides methods for actions performed by the user on the GUI.
 * The user is asked to input a value for the damage taken by his Character. A custom message and image is displayed
 * based on the amount of damage received.
 * This class extends GUIController and implements the Initializable interface.
 *
 * @see com.roleplay.utils.GUIController
 * @see javafx.fxml.Initializable
 */
@Deprecated
public class DamageGUIController extends GUIController {
    @FXML
    private Label progressLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView restoreImage;
    @FXML
    private Label restoredLabel;
    @FXML
    private Label textFieldLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField textField;

    //Override methods--------------------------------------------------------------------------------------------------
    @Override
    public void initialize(Main mainClass, DynamicGUIType guiType) {
        super.initialize(mainClass, guiType);
        textFieldLabel.setText("Damage taken:");
    }

    //Action listeners--------------------------------------------------------------------------------------------------

    /**
     * Action listener for the confirm button press.
     * This method executes when the confirm button is pressed. It gets the input damage from the textField and
     * subtracts it from the current health of the current Character. The method checks for invalid input and returns
     * errors to the user if the input is not valid. Based on the amount of damage and the current health of the
     * Character the method will return a custom message and image to inform the user of the current state of their
     * Character. After applying the damage a progress bar is displayed, which will fill up in a few seconds before the
     * scene transitions back to the previous one.
     */
    @FXML
    private void confirmPressed() {
        //Reset the labels to the original value in case they were modified because of an error
        textFieldLabel.setText("Damage taken:");
        textField.setStyle("-fx-border-color: none;");

        try {
            int damage = Integer.parseInt(textField.getText());
            if (damage < 0) {
                //check for negative input and exit method if found
                textField.setStyle("-fx-border-color: red;");
                textFieldLabel.setText("Invalid input, try again:");
                return;
            }

            Character character = mainClass.getCharacter(); //get the current Character from Main
            int total = character.getHpCurr() - damage; //calculate the total health after taking damage

            if (total < -(character.getHpMax())) {
                character.setHpCurr(total);
                restoreImage.setImage(new Image("/com/roleplay/icons/skull.png"));
                restoredLabel.setText("You received " + damage + " points of damage. Oh dear, you are dead!");
            } else {
                character.setHpCurr(total);
                restoreImage.setImage(new Image("/com/roleplay/icons/sword.png"));
                restoredLabel.setText("You received " + damage + " points of damage.");
            }

            mainClass.setCharacter(character);

            //Show progress label and start short wait with progressbar before returning to previous screen-------------
            confirmButton.setDisable(true);
            progressLabel.setVisible(true);
            progressBar.setVisible(true);
            WaitingClass wait = new WaitingClass();
            progressBar.progressProperty().bind(wait.progressProperty());
            new Thread(wait).start();
        } catch (NumberFormatException e) {
            textField.setStyle("-fx-border-color: red;");
            textFieldLabel.setText("Invalid input, try again:");
        }
    }

    //Utility-----------------------------------------------------------------------------------------------------------

    /**
     * Class used to add a short wait.
     * This class is used to create a short delay before the DamageGUIController is switched back to the previous scene.
     * This class extends Task and is used to show the progress of the waiting period on a progress bar.
     *
     * @see javafx.concurrent.Task
     */
    private class WaitingClass extends Task<Void> {
        /**
         * Override method which executes when the WaitingClass task is started.
         * This method increases the workDone by one at each iteration of a for loop and sets the Thread to sleep for a
         * few milliseconds.
         */
        @Override
        protected Void call() throws Exception {
            for (int i = 0; i < 50; i++) {
                //increase work one by one
                updateProgress(i + 1, 50);
                Thread.sleep(50);
            }
            return null;
        }

        /**
         * Override method which executes at successful completion of the Thread.
         * Upon successful execution the method calls Main.chooseScene() to switch to the previous scene.
         *
         * @see com.roleplay.Main
         */
        @Override
        protected void succeeded() {
            super.succeeded();
            mainClass.chooseScene(GUIs.CHARACTER);
        }
    }

}
