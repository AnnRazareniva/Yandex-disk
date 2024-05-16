package org.example.yandexdisk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 980, 720);
        scene.getStylesheets().add("/StyleYandex.css");
        stage.setTitle("Яндекс-диск для Linux");
        stage.setScene(scene);

        stage.setMinWidth(980);
        stage.setMinHeight(720);

        // TODO ограничение размера окна на стэйдж
        // TODO

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}