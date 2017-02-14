package com.roleplay.saveXMLGUI;

import com.roleplay.utils.GUIController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;

/**
 * Controller in charge of the SaveXMLGUI actions.
 * This class is linked to the SaveXMLGUI.fxml file. It provides methods which execute when the GUI is initialized.
 * When this class is initialized the current Character information is saved to an XML file. A progress bar is displayed
 * and fills up before the scene is reverted back to the previous one.
 * This class extends GUIController.
 *
 * @see com.roleplay.utils.GUIController
 * @see com.roleplay.Character
 */
public class SaveXMLGUIController extends GUIController {
    @FXML
    private ProgressBar progressBar;

    //Override methods--------------------------------------------------------------------------------------------------

    /**
     * Override method which executes at initialization.
     * This method launches a wait task which will save the character information to XML.
     */
    @FXML
    private void initialize() {
        //Initialize a WaitingClass, it will save character data to XML
        WaitingClass wait = new WaitingClass();
        progressBar.progressProperty().bind(wait.progressProperty()); //Bind progress bar to wait progress
        new Thread(wait).start();
    }

    //Utility-----------------------------------------------------------------------------------------------------------
    /**
     * Class used to save character info and add a short wait.
     * This class is used to save the current character information and then create a short delay before the scene is
     * switched back to the previous one.
     * This class extends Task and is used to show the progress of the waiting period on a progress bar.
     *
     * @see javafx.concurrent.Task
     */
    private class WaitingClass extends Task<Void> {
        /**
         * Override method which executes when the WaitingClass task is started.
         * This method increases the workDone by one at each iteration of a for loop and sets the Thread to sleep for a
         * few milliseconds. It also saves the current Character information to XML.
         *
         * @see com.roleplay.Character
         */
        @Override
        protected Void call() throws Exception {
            mainClass.getCharacter().saveToXML(); //save Character information

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
            mainClass.chooseScene(1);
        }

        /**
         * Override method which executes in case of failure in the Thread.
         * This method will return an error message to the user and revert scene.
         */
        @Override
        protected void failed() {
            super.failed();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Saving Data");
            alert.setHeaderText("XML Saving Error");
            alert.setContentText("Ooops, there was an error saving the information! Please try again.");
            alert.showAndWait();

            mainClass.chooseScene(1);
        }
    }

}
