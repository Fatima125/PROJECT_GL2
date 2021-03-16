package sample;

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

public class Music {


    ObservableList<String> musicStyles = FXCollections.observableArrayList("ALL", "pop", "rock", "country", "rap", "R&B", "classical", "Jazz", "k-pop");

    @FXML
    private TableView<entities.Music> musicTable;
    @FXML
    private TableColumn<entities.Music, String> titleColumn;
    @FXML
    private TableColumn<entities.Music, String> descriptionColumn;


    @FXML
    private ComboBox<String> musicBox;
    @FXML
    private void initialize() {
        musicBox.setValue("ALL");
        musicBox.setItems(musicStyles);
    }

    public void iniColumn() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    public void searchMusicByGenre(ActionEvent actionEvent) throws SQLException {
        entities.Music musicResult;
        Subscriber subscriber = new Subscriber();
        ResultSet resultSet;
        if(musicBox.getValue().equals("ALL")){
            resultSet = subscriber.showMusic();
            subscriber.showMusic();
        }
        else {
            resultSet = subscriber.showSpecificTypeOfMusic(musicBox.getValue());
            subscriber.showSpecificTypeOfMusic(musicBox.getValue());
        }
        List<entities.Music> data = new ArrayList<>();
        while (resultSet.next()) {
            musicResult = new entities.Music();
            musicResult.setTitle(resultSet.getString("TITLE"));
            musicResult.setDescription(resultSet.getString("DESCRIPTION"));
            data.add(musicResult);
        }
        iniColumn();
        ObservableList<entities.Music> result = FXCollections.observableArrayList(data);
        musicTable.setItems(result);
    }
}
