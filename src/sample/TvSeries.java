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

public class TvSeries {

    ObservableList<String> showGenders = FXCollections.observableArrayList("ALL", "comedy", "romance", "action", "horror");

    @FXML
    private TableView<entities.TvSeries> showsTable;
    @FXML
    private TableColumn<TvSeries, String> titleColumn;
    @FXML
    private TableColumn<entities.TvSeries, String> descriptionColumn;


    @FXML
    private ComboBox<String> showsBox;
    @FXML
    private void initialize() {
        showsBox.setValue("ALL");
        showsBox.setItems(showGenders);
    }

    public void iniColumn() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    public void searchMusicByGenre(ActionEvent actionEvent) throws SQLException {
        entities.TvSeries tvSeries;
        Subscriber subscriber = new Subscriber();
        ResultSet resultSet;
        if(showsBox.getValue().equals("ALL")){
            resultSet = subscriber.showSeries();
            subscriber.showSeries();
        }
        else {
            resultSet = subscriber.showSpecificTypeOfTvSeries(showsBox.getValue());
            subscriber.showSpecificTypeOfTvSeries(showsBox.getValue());
        }
        List<entities.TvSeries> data = new ArrayList<>();
        while (resultSet.next()) {
            tvSeries = new entities.TvSeries();
            tvSeries.setTitle(resultSet.getString("TITLE"));
            tvSeries.setDescription(resultSet.getString("DESCRIPTION"));
            data.add(tvSeries);
        }
        iniColumn();
        ObservableList<entities.TvSeries> result = FXCollections.observableArrayList(data);
        showsTable.setItems(result);
    }
}
