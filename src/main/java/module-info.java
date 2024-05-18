module org.example.yandexdisk {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires yandex.disk.api.client.lib;
    requires java.desktop;

    opens org.example.yandexdisk to javafx.fxml;
    exports org.example.yandexdisk;
}