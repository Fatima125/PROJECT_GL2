package sample;

import entities.NoteBook;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonalD {

    @FXML
    private TableView<entities.NoteBook> notesTable;
    @FXML
    private TableColumn<entities.NoteBook, String> titleColumn;

    public void initColumn() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    @FXML
    TextArea description;
    @FXML
    TextField title;
    @FXML
    Button searchBtn;
    @FXML
    Button displayBtn;
    @FXML
    Button clearBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button validateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Label titleError;

    public void addNote(ActionEvent actionEvent) throws SQLException {
        if (title.getText().isEmpty() || description.getText().isEmpty()) {
            titleError.setText("Please enter your data");
        }
        else{
            NoteBook noteBook = new NoteBook(title.getText(), description.getText());
            Subscriber subscriber = new Subscriber();
            Boolean exists = subscriber.searchNote(noteBook);
            if(exists) {
                titleError.setText("Title already used, please change it!");
                title.clear();
            }
            else {
                subscriber.addNote(noteBook);
                titleError.setText("Your note has been added successfully!");
                title.clear();
                description.clear();
            }
        }
        title.setEditable(true);
    }

    public void showUpdatable(ActionEvent actionEvent) {
        validateBtn.setDisable(false);
        validateBtn.setVisible(true);
        description.setEditable(true);
        title.setEditable(false);
    }

    public void deleteNote(ActionEvent actionEvent) throws SQLException {
        titleError.setText("");
        title.setEditable(false);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("The deletion is not reversible!");
        alert.setTitle("Delete my note");
        alert.setHeaderText("Are you sure you want to delete this note ?");

        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Subscriber subscriber = new Subscriber();
            Boolean resultD = subscriber.deleteNote(title.getText());
            if(! resultD) {
                titleError.setText("An error occurred while deleting your note!");
            }
            else {
                titleError.setText("Note deleted successfully");
                title.clear();
                description.clear();
                deleteBtn.setDisable(true);
                updateBtn.setDisable(true);
            }
        }
        else {
            titleError.setText("Deletion aborted");
        }
        title.setEditable(true);
    }

    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuSubscriber.fxml");
        menu.setStageTitle("HOME");
    }

    public void showTitles(ActionEvent actionEvent) throws SQLException {
        entities.NoteBook noteBook;
        Subscriber subscriber = new Subscriber();
        ResultSet resultSet;
        resultSet = subscriber.showNotes();
        List<NoteBook> data = new ArrayList<>();
        while (resultSet.next()) {
            noteBook = new entities.NoteBook();
            noteBook.setTitle(resultSet.getString("TITLE"));
            data.add(noteBook);
        }
        initColumn();
        ObservableList<NoteBook> result = FXCollections.observableArrayList(data);
        notesTable.setItems(result);
    }

    public void searchNote(ActionEvent actionEvent) throws SQLException {
        titleError.setText("");
        if (title.getText().isEmpty()) {
            titleError.setText("Please enter the title of your note!");
        }
        else {
            Subscriber subscriber = new Subscriber();
            ResultSet resultSet = subscriber.searchNote(title.getText());
            if (resultSet != null) {
                description.setEditable(false);
                description.setText(resultSet.getString("description"));
                updateBtn.setDisable(false);
                deleteBtn.setDisable(false);
                title.setEditable(false);
            } else {
                titleError.setText("Wrong title please check it in the table above!");
            }
        }
    }

    public void clearAll(ActionEvent actionEvent) {
        description.setText("");
        title.setText("");
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        validateBtn.setDisable(true);
        validateBtn.setVisible(false);
        title.setEditable(true);
        description.setEditable(true);
    }

    public void updateNote(ActionEvent actionEvent) throws SQLException {
        titleError.setText("");
        if(description.getText().isEmpty()) {
            titleError.setText("Please fill the description field!");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The modification is not reversible!");
            alert.setTitle("Update my note");
            alert.setHeaderText("Are you sure you want to update your note?");

            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getDialogPane().getButtonTypes().add(cancelButtonType);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                NoteBook noteBook = new NoteBook(title.getText(), description.getText());
                Subscriber subscriber = new Subscriber();
                Boolean resultD = subscriber.modifyNote(noteBook);
                if(! resultD) {
                    titleError.setText("An error occurred while updating your note");
                }
                else {
                    titleError.setText("Note updated successfully");
                    title.clear();
                    description.clear();
                }
            }
            else {
                titleError.setText("Update aborted");
            }
        }
        validateBtn.setVisible(false);
        validateBtn.setDisable(true);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        title.setEditable(true);
        description.setEditable(true);
    }
}
