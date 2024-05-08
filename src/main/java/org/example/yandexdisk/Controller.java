package org.example.yandexdisk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller
{

    @FXML
    protected void OnLogInButtonClick(ActionEvent actionEvent) throws java. io.IOException
    {
        Stage stageLoggedIn = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("logedIn-view.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 980, 720);
        mainScene.getStylesheets().add("/StyleYandex.css");

        stageLoggedIn.setTitle("Яндекс-диск для Linux");
        stageLoggedIn.setScene(mainScene);

        // TODO ограничение размера окна на стэйдж
        // TODO

        //stageLoggedIn.show();
    }

    @FXML
    protected void OnUploadButtonClick(ActionEvent actionEvent) throws java. io.IOException
    {
        Stage stageDownload = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("download.fxml"));
        Scene downloadingFile = new Scene(fxmlLoader.load(), 720, 360);
        downloadingFile.getStylesheets().add("/StyleYandex.css");

        stageDownload.setTitle("");
        stageDownload.setScene(downloadingFile);


        stageDownload.show();
    }
}