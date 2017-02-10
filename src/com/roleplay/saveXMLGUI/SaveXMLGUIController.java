package com.roleplay.saveXMLGUI;

import com.roleplay.utils.GUIController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller in charge of the SaveXMLGUI actions.
 * This class is linked to the SaveXMLGUI.fxml file. It provides methods which execute when the GUI is initialized.
 * When this class is initialized the current Character information is saved to an XML file. A progress bar is displayed
 * and fills up before the scene is reverted back to the previous one.
 * This class extends GUIController and implements the Initializable interface.
 * @see com.roleplay.utils.GUIController
 * @see javafx.fxml.Initializable
 * @see com.roleplay.Character
 */
public class SaveXMLGUIController extends GUIController implements Initializable {
    @FXML
    private ProgressBar progressBar;

    //Override methods--------------------------------------------------------------------------------------------------

    /**
     * Override method which executes at initialization.
     * This method saves the current Character information to a XML file and starts the progress bar Task.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            character.saveToXML();
            WaitingClass wait = new WaitingClass();
            progressBar.progressProperty().bind(wait.progressProperty());
            new Thread(wait).start();
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Saving Data");
            alert.setHeaderText("XML Saving Error");
            alert.setContentText("Ooops, there was an error saving the information! Please try again.");
            alert.showAndWait();

            try {
                mainClass.chooseScene(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.out.println("Error: " + e.getMessage());
        }
    }

    //Utility-----------------------------------------------------------------------------------------------------------
    /**
     * Class used to add a short wait.
     * This class is used to create a short delay before the DamageGUIController is switched back to the previous scene.
     * This class extends Task and is used to show the progress of the waiting period on a progress bar.
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
                updateProgress(i +1, 50);
                Thread.sleep(50);
            }
            return null;
        }

        /**
         * Override method which executes at successful completion of the Thread.
         * Upon successful execution the method calls Main.chooseScene() to switch to the previous scene.
         * @see com.roleplay.Main
         */
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
