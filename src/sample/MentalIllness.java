package sample;

import entities.Books;
import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MentalIllness {


    ObservableList<String> illnessesBox = FXCollections.observableArrayList("Anxiety disorders", "Mood disorders", "Psychotic disorders", "Eating disorders", "Impulse control and addiction disorders", "Personality disorders", "Obsessive-compulsive disorder", "Post-traumatic stress disorder", "Dissociative disorders");


    @FXML TextArea illnessDescription;
    @FXML TextArea illnessAdvice;
    @FXML private ComboBox<String> illnessBox;
    @FXML
    private void initialize() {
        illnessBox.setValue("Anxiety disorders");
        illnessBox.setItems(illnessesBox);
    }


    public void searchIllness(ActionEvent actionEvent) throws SQLException {
        Subscriber subscriber = new Subscriber();
        ResultSet resultSet;
        resultSet = subscriber.showIllness(illnessBox.getValue());
        illnessDescription.setText(resultSet.getString("description"));
        illnessAdvice.setText(resultSet.getString("advices"));
    }

    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuSubscriber.fxml");
        menu.setStageTitle("HOME");
    }
}
