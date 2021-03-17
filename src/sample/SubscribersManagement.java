package sample;

import entities.Admin;
import entities.Music;
import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscribersManagement {


    @FXML
    private TableView<entities.Subscriber> subscribersTable;
    @FXML
    private TableColumn<entities.Subscriber, String> usernameColumn;
    @FXML
    private TableColumn<entities.Subscriber, String> passwordColumn;
    @FXML
    private TableColumn<entities.Subscriber, String> genderColumn;

    @FXML
    TextField usernameToDelete;
    @FXML
    Label usernameNotFound;

    public void initColumn() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));



    }

    public void deleteSubscriber(ActionEvent actionEvent) throws SQLException {
        usernameNotFound.setText("");
        if (usernameToDelete.getText().isEmpty()) {
            usernameNotFound.setText("Please enter the username!");
        } else {
            Admin admin = new Admin();
            if (!admin.searchUsername(usernameToDelete.getText())) {
                usernameNotFound.setText("Username not found!");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("The deletion is not reversible!");
                alert.setTitle("Delete this account");
                alert.setHeaderText("Are you sure you want to delete this account?");

                ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getDialogPane().getButtonTypes().add(cancelButtonType);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Boolean resultD = admin.deleteAccount(usernameToDelete.getText());
                    if (!resultD) {
                        usernameNotFound.setText("An error occurred while deleting the account");
                    } else {
                        usernameNotFound.setText("Account deleted successfully");
                        usernameToDelete.clear();
                    }
                }
                else {
                    usernameNotFound.setText("Deletion aborted");
                }
            }
        }
    }


    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuAdmin.fxml");
        menu.setStageTitle("HOME");
    }

    public void showSubscribers(ActionEvent actionEvent) throws SQLException {
        entities.Subscriber subscribers;
        Admin admin = new Admin();
        ResultSet resultSet;
        resultSet = admin.showSubscribers();
        admin.showSubscribers();
        List<Subscriber> data = new ArrayList<>();
        while (resultSet.next()) {
            Subscriber subscriber;
            subscriber = new Subscriber();
            subscriber.setUsername(resultSet.getString("username"));
            subscriber.setPassword(resultSet.getString("password"));
            subscriber.setGender(resultSet.getString("gender"));
            data.add(subscriber);
        }
        initColumn();
        ObservableList<entities.Subscriber> result = FXCollections.observableArrayList(data);
        subscribersTable.setItems(result);

    }
}
