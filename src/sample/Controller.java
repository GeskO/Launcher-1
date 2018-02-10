package sample;

import javafx.event.ActionEvent;

public class Controller {
    public void downloadClient(ActionEvent actionEvent) {
        Downloader.download();
    }
}
