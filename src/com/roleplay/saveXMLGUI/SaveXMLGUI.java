package com.roleplay.saveXMLGUI;

import com.roleplay.utils.GUIController;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * File created by Lorenzo Delcroix on 12/22/2016 4:57.
 * Belongs to the package com.roleplay.restoreHealthGUI of the RoleplayHelper project.
 */

public class SaveXMLGUI extends GUIController implements Initializable {
    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WaitingClass wait = new WaitingClass();
        progressBar.progressProperty().bind(wait.progressProperty());
        new Thread(wait).start();
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
