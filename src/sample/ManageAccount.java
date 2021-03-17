package sample;

import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ManageAccount {

    ObservableList<String> genders = FXCollections.observableArrayList("male", "female");

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField newPassword;
    @FXML
    private DatePicker birthDate;
    @FXML
    private ComboBox<String> gender;
    @FXML
    Button showBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button confirmBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Label userNotFoundError;

    @FXML
    private void initialize() {
        gender.setValue("female");
        gender.setItems(genders);
    }


    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuSubscriber.fxml");
        menu.setStageTitle("HOME");
    }

    public void showAccount(ActionEvent actionEvent) throws SQLException {
        userNotFoundError.setText("");
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            userNotFoundError.setText("Please enter your username & password!");
        }
        else {
            Subscriber oldUser = new Subscriber(username.getText(), password.getText());
            if (oldUser.exists()) {
                Subscriber subscriber = new Subscriber();
                subscriber = (Subscriber) subscriber.consultAccount(username.getText());
                if (subscriber.consultAccount(username.getText()) != null ) {
                    newPassword.setText(subscriber.getPassword());
                    birthDate.setValue(subscriber.getBirthDate());
                    gender.setValue(subscriber.getGender());
                    updateBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                }
                else {
                    userNotFoundError.setText("wrong username or password");
                }
            }
        }
    }

    public void deleteAccount(ActionEvent actionEvent) throws SQLException{
        userNotFoundError.setText("");
        Subscriber subscriber = new Subscriber(username.getText());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("The deletion is not reversible!");
        alert.setTitle("Delete my account");
        alert.setHeaderText("Are you sure you want to delete your account?");

        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Boolean resultD = subscriber.deleteAccount();
            if(! resultD) {
                userNotFoundError.setText("An error occurred while deleting the account");
            }
            else {
                userNotFoundError.setText("Account deleted successfully");
                username.clear();
                password.clear();
            }
        }
        else {
            userNotFoundError.setText("Deletion aborted");
        }
    }

    public void showUpdatable(ActionEvent actionEvent) throws SQLException{
        username.setEditable(false);
        password.setEditable(false);
        userNotFoundError.setText("");
        newPassword.setEditable(true);
        confirmBtn.setVisible(true);
        confirmBtn.setDisable(false);
    }

    public void updateAccount (ActionEvent actionEvent) throws SQLException {
        userNotFoundError.setText("");
        if(newPassword.getText().isEmpty() || birthDate.getValue() == null || gender.getValue() == null) {
            userNotFoundError.setText("Please fill all the fields!");
        }
        else {
            Subscriber subscriber = new Subscriber(username.getText());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The modification is not reversible!");
            alert.setTitle("Update my account");
            alert.setHeaderText("Are you sure you want to update your account?");

            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getDialogPane().getButtonTypes().add(cancelButtonType);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Subscriber subscriberUpdated = new Subscriber(newPassword.getText(),gender.getValue(), birthDate.getValue());
                Boolean resultD = subscriber.modifyAccount(subscriberUpdated);
                if(! resultD) {
                    userNotFoundError.setText("An error occurred while updating the account");
                }
                else {
                    userNotFoundError.setText("Account updated successfully");
                    username.clear();
                    password.clear();
                    newPassword.clear();
                    username.setEditable(true);
                    password.setEditable(true);
                    newPassword.setEditable(false);
                }
            }
            else {
                userNotFoundError.setText("Update aborted");
            }
        }
    }
}
