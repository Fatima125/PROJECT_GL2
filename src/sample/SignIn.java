package sample;

import entities.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;


public class SignIn {

    @FXML
    private BorderPane hobbiesMainPane;

    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginError;

    private static int i = 0;

    public void login(ActionEvent actionEvent) throws IOException, SQLException {

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            loginError.setText("PLEASE ENTER YOU DATA");
        }
        else {
            Main main = new Main();
            if( i < 5) {
                Subscriber subscriber = new Subscriber(username.getText(), password.getText());
                if (subscriber.exists()) {
                    main.changeScene("menuSubscriber.fxml");
                    main.setStageTitle("HOME");
                    i = 0;
                }
                else {
                    loginError.setText("WRONG USERNAME OR PASSWORD");
                    i++;
                }
            }
        }
        if(i == 5) {
            button.setDisable(true);
            loginError.setText("PLEASE SUBSCRIBE!");
        }
        username.clear();
        password.clear();
    }


    public void showHobbies(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("hobbies.fxml");
        menu.setStageTitle("HOBBIES");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("sample.fxml");
        menu.setStageTitle("SEE YOU NEXT TIME!");
    }

    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuSubscriber.fxml");
        menu.setStageTitle("HOME");
    }


    public void showMentalIllnesses(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("mentalIllnesses.fxml");
        menu.setStageTitle("MENTAL ILLNESS");
    }

    public void showPersonalDiary(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("personalDiary.fxml");
        menu.setStageTitle("PERSONAL DIARY");
    }

    public void showAccountManagement(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("manageAccount.fxml");
        menu.setStageTitle("MY ACCOUNT");
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
}
