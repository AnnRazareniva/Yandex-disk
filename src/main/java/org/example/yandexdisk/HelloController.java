package org.example.yandexdisk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLogInButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}