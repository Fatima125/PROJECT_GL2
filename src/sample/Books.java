package sample;

import entities.Music;
import entities.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Books {

    ObservableList<String> bookAuthors = FXCollections.observableArrayList("ALL", "Rachid Mimouni", "Kateb Yacine", "Tahar Djaout", "Agatha Cristine", "Robert Louis Stevenson", "Mohammed Dib", "Jules Verne", "Assia Djebar");

    @FXML
    private TableView<entities.Books> booksTable;
    @FXML
    private TableColumn<entities.Books, String> titleColumn;
    @FXML
    private TableColumn<entities.Books, String> descriptionColumn;


    @FXML
    private ComboBox<String> authorsBox;
    @FXML
    private void initialize() {
        authorsBox.setValue("ALL");
        authorsBox.setItems(bookAuthors);
    }

    public void iniColumn() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    public void searchMusic(ActionEvent actionEvent) throws SQLException {
        entities.Books booksResult;
        Subscriber subscriber = new Subscriber();
        ResultSet resultSet;
        if(authorsBox.getValue().equals("ALL")){
            resultSet = subscriber.showBooks();
            subscriber.showBooks();
        }
        else {
            resultSet = subscriber.showSpecificBooks(authorsBox.getValue());
            subscriber.showSpecificBooks(authorsBox.getValue());
        }
        List<entities.Books> data = new ArrayList<>();
        while (resultSet.next()) {
            booksResult = new entities.Books();
            booksResult.setTitle(resultSet.getString("TITLE"));
            booksResult.setDescription(resultSet.getString("DESCRIPTION"));
            data.add(booksResult);
        }
        iniColumn();
        ObservableList<entities.Books> result = FXCollections.observableArrayList(data);
        booksTable.setItems(result);
    }

}
