package org.example.yandexdisk;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.vorotov.yandex_disk_api_client_lib.YandexDiskClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
    @FXML
    private Label directoryPathLabel, errorDirectoryLabel, fileSize, fileName;
    @FXML
    private ProgressBar FileProgressBar;
    @FXML
    private TextField linkField;
    @FXML
    private Button DownloadFile, chooseDirectory;


    private final boolean isCancelled = false;

    YandexDiskClient client;

    String fileLink;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        client = YandexDiskClient.getInstance();
    }

    @FXML
    protected void OnLogInButtonClick(ActionEvent actionEvent) throws java. io.IOException
    {
        Stage stageLoggedIn = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loggedIn-view.fxml"));
        fxmlLoader.setController(this);
        Scene mainScene = new Scene(fxmlLoader.load(), 980, 720);
        mainScene.getStylesheets().add("/StyleYandex.css");

        stageLoggedIn.setTitle("Яндекс-диск для Linux");
        stageLoggedIn.setScene(mainScene);

        stageLoggedIn.setMinWidth(980);
        stageLoggedIn.setMinHeight(720);

        // TODO ограничение размера окна на стэйдж
        // TODO

        //stageLoggedIn.show();
    }

    @FXML
    protected void OnUploadButtonClick(ActionEvent actionEvent) throws java. io.IOException
    {
        Stage stageUpload = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("download.fxml"));
        fxmlLoader.setController(this);
        Scene downloadingFile = new Scene(fxmlLoader.load(), 720, 360);
        downloadingFile.getStylesheets().add("/StyleYandex.css");

        stageUpload.setTitle("");
        stageUpload.setScene(downloadingFile);



        stageUpload.show();
    }

    @FXML
    protected void OnDownloadButtonClick(ActionEvent actionEvent) throws java. io.IOException
    {
        Stage stageDownload = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("download.fxml"));
        fxmlLoader.setController(this);
        Scene downloadingFile = new Scene(fxmlLoader.load(), 460, 200);
        downloadingFile.getStylesheets().add("/StyleYandex.css");

        stageDownload.setTitle("Окно скачивания");
        stageDownload.setScene(downloadingFile);

        stageDownload.setMinWidth(460);
        stageDownload.setMinHeight(200);
        fileLink = linkField.getText();
        System.out.println(fileLink);
        stageDownload.show();

    }

    @FXML
    protected void OnDownloadFileButtonClick(ActionEvent actionEvent) throws java.io.IOException {
//        fileLink = linkField.getText();
        System.out.println(fileLink);


        //      fileLink = "https://disk.yandex.ru/i/6NNFt6dVJlwqtQ";
        Task<Void> downloadTask = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
//                    String fileLink = linkField.getText();


                    InputStream in = client.download(fileLink);
                    int fileSizeF = client.getFileSize(fileLink);

                    String filename = directoryPathLabel.getText() + "/" + client.getFileName(fileLink);
                    System.out.println(filename);
                    try (FileOutputStream outputStream = new FileOutputStream(filename)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        int totalBytesRead = 0;

                        while ((bytesRead = in.read(buffer)) != -1) {
                            if (isCancelled) {
                                client.cancelDownload(in);
                                break;
                            }

                            outputStream.write(buffer, 0, bytesRead);
                            totalBytesRead += bytesRead;
                            double progress = (double) totalBytesRead / fileSizeF * 100;

                            System.out.println("Прогресс загрузки: " + progress + " %");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return null;
                }
            };

            Thread downloadThread = new Thread(downloadTask);
            downloadThread.start();
    }

    @FXML
    protected void OnChooseDirectoryButtonClick(ActionEvent actionEvent) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку");

        Stage stage = (Stage) directoryPathLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory!=null)
        {
            DownloadFile.setStyle("visibility: true;");

            chooseDirectory.setStyle("-fx-background-color: #f4f4f4; -fx-text-fill: blue;");
            directoryPathLabel.setVisible(true);
            directoryPathLabel.setText(selectedDirectory.getAbsolutePath());

//            fileSize.setText(String.valueOf(client.getFileSize(linkField.getText())));
//            fileName.setText(client.getFileName(linkField.getText()));
        }
    }

}