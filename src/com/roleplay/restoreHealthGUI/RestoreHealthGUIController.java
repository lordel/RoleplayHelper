package com.roleplay.restoreHealthGUI;

import com.roleplay.Character;
import com.roleplay.utils.GUIController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * File created by Lorenzo Delcroix on 12/22/2016 4:57.
 * Belongs to the package com.roleplay.restoreHealthGUI of the RoleplayHelper project.
 */

public class RestoreHealthGUIController extends GUIController implements Initializable {
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

    @FXML
    private void confirmPressed(ActionEvent actionEvent) throws IOException {
        textFieldLabel.setText("Health restored:");
        textField.setStyle("-fx-border-color: none;");
        try {
            int restore = Integer.parseInt(textField.getText());
            if (restore < 0) {
                textField.setStyle("-fx-border-color: red;");
                textFieldLabel.setText("Invalid input, try again:");
                return;
            }
            Character character = mainClass.getCharacter();
            int total = character.getHpCurr() + restore;
            if (total < character.getHpMax()) {
                character.setHpCurr(total);
                restoreImage.setImage(new Image("/com/roleplay/icons/cross.png"));
                restoredLabel.setText("You restored " + restore + " of health.");
            } else {
                character.setHpCurr(character.getHpMax());
                restoreImage.setImage(new Image("/com/roleplay/icons/twinkles.png"));
                restoredLabel.setText("You restored " + restore + " of health." +
                        "You have been fully healed.");
            }
            mainClass.setCharacter(character);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("How much health did you restore?");
        textFieldLabel.setText("Health restored:");
    }

    /**
     * info about this class!
     * TODO:Add information about the class
     */
    private class WaitingClass extends Task<Void> {
        @Override
        protected Void call() throws Exception {
            for (int i = 0; i < 50; i++) {
                //increase work one by one
                updateProgress(i + 1, 50);
                Thread.sleep(50);
            }
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            try {
                mainClass.chooseScene(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
