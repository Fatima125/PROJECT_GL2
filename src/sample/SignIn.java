package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SignIn {

    @FXML
    private BorderPane hobbiesMainPane;

    public void showHobbies(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("hobbies.fxml");
        menu.setStageTitle("HOBBIES");
    }

    public void showMusic(ActionEvent actionEvent) {
        PageLoader pageLoader = new PageLoader();
        Pane view = pageLoader.getPage("music.fxml");
        hobbiesMainPane.setCenter(view);
    }

    public void showBooks(ActionEvent actionEvent) {
        PageLoader pageLoader = new PageLoader();
        Pane view = pageLoader.getPage("books.fxml");
        hobbiesMainPane.setCenter(view);
    }

    public void showTvSeries(ActionEvent actionEvent) {
        PageLoader pageLoader = new PageLoader();
        Pane view = pageLoader.getPage("tvSeries.fxml");
        hobbiesMainPane.setCenter(view);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("sample.fxml");
        menu.setStageTitle("HOBBIES");
    }

    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("manageAccount.fxml");
        menu.setStageTitle("HOBBIES");
    }
}
