package sample;

import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {
    public void downloadClient(ActionEvent actionEvent) throws IOException {
        Downloader.downloadClient();
    }
}
