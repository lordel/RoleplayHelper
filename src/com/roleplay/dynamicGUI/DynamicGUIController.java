package com.roleplay.dynamicGUI;

import com.roleplay.Character;
import com.roleplay.Main;
import com.roleplay.enums.DynamicGUIType;
import com.roleplay.enums.GUIs;
import com.roleplay.utils.GUIController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.bind.JAXBException;

/**
 * Controller in charge of the DamageGUI actions.
 * This class is linked to the DamageGUI.fxml file. It provides methods for actions performed by the user on the GUI.
 * The user is asked to input a value for the damage taken by his Character. A custom message and image is displayed
 * based on the amount of damage received.
 * This class extends GUIController.
 *
 * @see com.roleplay.utils.GUIController
 */
public class DynamicGUIController extends GUIController {
    public Label textFieldLabel;
    public TextField textField;
    public Label titleLabel;
    public Button confirmButton;
    public ImageView image;
    public Label informationLabel;
    public ProgressBar progressBar;
    public Label progressLabel;

    //Override methods--------------------------------------------------------------------------------------------------
    @Override
    public void initialize(Main mainClass, DynamicGUIType guiType) {
        //TODO: comment code to clarify what is happening!!
        super.initialize(mainClass, guiType);
        progressLabel.setVisible(false);
        progressBar.setVisible(false);
        switch (guiType) {
            case HEALTH:
                titleLabel.setText("How much health have you restored?");
                textFieldLabel.setText("Health restored:");
                break;
            case DAMAGE:
                titleLabel.setText("How much damage have you taken?");
                textFieldLabel.setText("Damage taken:");
                break;
            case MAGIC_USE:
                titleLabel.setText("How much magic have you used?");
                textFieldLabel.setText("Magic used:");
                break;
            case MAGIC_RESTORE:
                titleLabel.setText("How much magic point are you restoring?");
                textFieldLabel.setText("Magic points restored:");
                break;
            case EXPERIENCE:
                titleLabel.setText("How much experience points have you gained?");
                textFieldLabel.setText("Experience gained:");
                break;
            case SAVING:
                titleLabel.setText("Saving character data to file...");
                informationLabel.setText("Character data is being saved to file, please wait.");
                textFieldLabel.setVisible(false); //hide the text label
                textField.setVisible(false); //hide text field
                confirmButton.setVisible(false);
                progressLabel.setVisible(true);
                progressBar.setVisible(true);

                try {
                    mainClass.getCharacter().saveToXML(); //save Character information
                } catch (JAXBException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Saving Data");
                    alert.setHeaderText("XML Saving Error");
                    alert.setContentText("Ooops, there was an error saving the information! Please try again.");
                    alert.showAndWait();

                    mainClass.chooseScene(GUIs.CHARACTER);
                }

                //Initialize a WaitingClass, it will save character data to XML
                WaitingClass wait = new WaitingClass();
                progressBar.progressProperty().bind(wait.progressProperty()); //Bind progress bar to wait progress
                new Thread(wait).start();
                break;
        }

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
        switch (guiType) {
            case HEALTH:
                //Reset the labels to the original value in case they were modified because of an error
                textFieldLabel.setText("Health restored:");
                textField.setStyle("-fx-border-color: none;");
                try {
                    int restore = Integer.parseInt(textField.getText());
                    if (restore < 0) {
                        //check for negative input and exit method if found
                        textField.setStyle("-fx-border-color: red;");
                        textFieldLabel.setText("Invalid input, try again:");
                        return;
                    }

                    Character character = mainClass.getCharacter(); //Get the current Character from Main
                    int total = character.getHpCurr() + restore; //calculate the total health after healing

                    if (total < character.getHpMax()) {
                        character.setHpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/cross.png"));
                        informationLabel.setText("You restored " + restore + " of health.");
                    } else {
                        character.setHpCurr(character.getHpMax());
                        image.setImage(new Image("/com/roleplay/icons/twinkles.png"));
                        informationLabel.setText("You restored " + restore + " of health." +
                                "You have been fully healed.");
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
                break;
            case DAMAGE:
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
                        image.setImage(new Image("/com/roleplay/icons/skull.png"));
                        informationLabel.setText("You received " + damage + " points of damage. Oh dear, you are dead!");
                    } else {
                        character.setHpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/sword.png"));
                        informationLabel.setText("You received " + damage + " points of damage.");
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
                break;
            case MAGIC_USE:
                //Reset the labels to the original value in case they were modified because of an error
                textFieldLabel.setText("Magic used:");
                textField.setStyle("-fx-border-color: none;");

                try {
                    int used = Integer.parseInt(textField.getText());
                    if (used < 0) {
                        //check for negative input and exit method if found
                        textField.setStyle("-fx-border-color: red;");
                        textFieldLabel.setText("Invalid input, try again:");
                        return;
                    }

                    Character character = mainClass.getCharacter(); //get the current Character from Main
                    int total = character.getMpCurr() - used; //calculate the total magic points after taking usage

                    if (total < -(character.getMpMax())) {
                        //TODO: possibly add check to avoid character from using magic they don't have
                        character.setMpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/skull.png")); //TODO:find image
                        informationLabel.setText("You used " + used + " magic points. Oh dear, you are out of magic!");
                    } else {
                        character.setMpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/sword.png")); //TODO:find image
                        informationLabel.setText("You used " + used + " points of magic.");
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
                break;
            case MAGIC_RESTORE:
                //Reset the labels to the original value in case they were modified because of an error
                textFieldLabel.setText("Magic points restored:");
                textField.setStyle("-fx-border-color: none;");

                try {
                    int restored = Integer.parseInt(textField.getText());
                    if (restored < 0) {
                        //check for negative input and exit method if found
                        textField.setStyle("-fx-border-color: red;");
                        textFieldLabel.setText("Invalid input, try again:");
                        return;
                    }

                    Character character = mainClass.getCharacter(); //get the current Character from Main
                    int total = character.getMpCurr() + restored; //calculate the total magic points after taking usage

                    if (total > (character.getMpMax())) {
                        character.setMpCurr(character.getMpMax());
                        image.setImage(new Image("/com/roleplay/icons/skull.png")); //TODO:find image
                        informationLabel.setText("You restored " + restored + " magic points. Your magic bar is now full!");
                    } else {
                        character.setMpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/sword.png")); //TODO:find image
                        informationLabel.setText("You restored " + restored + " points of magic.");
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
                break;
            case EXPERIENCE:
                //Reset the labels to the original value in case they were modified because of an error
                textFieldLabel.setText("Experience gained:");
                textField.setStyle("-fx-border-color: none;");
                try {
                    int gained = Integer.parseInt(textField.getText());
                    if (gained < 0) {
                        //check for negative input and exit method if found
                        textField.setStyle("-fx-border-color: red;");
                        textFieldLabel.setText("Invalid input, try again:");
                        return;
                    }

                    Character character = mainClass.getCharacter(); //Get the current Character from Main
                    int total = character.getExpCurr() + gained; //calculate the total health after healing

                    if (total < character.getExpMax()) {
                        //TODO:check the logic here
                        character.setExpCurr(total);
                        image.setImage(new Image("/com/roleplay/icons/cross.png")); //TODO: find image
                        informationLabel.setText("You gained " + gained + " experience points.");
                    } else {
                        character.setExpCurr(character.getExpMax());
                        //TODO:check this logic
                        image.setImage(new Image("/com/roleplay/icons/twinkles.png")); //TODO: find image
                        informationLabel.setText("You gained " + gained + " experience points." +
                                "You have been fully healed.");
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
                break;
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
