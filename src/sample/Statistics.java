package sample;

import entities.Admin;
import entities.Subscriber;
import entities.TvSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Statistics {

    @FXML
    PieChart pieChart;

    public void showMenu(ActionEvent actionEvent) throws IOException {
        Main menu = new Main();
        menu.changeScene("menuAdmin.fxml");
        menu.setStageTitle("HOME");
    }

    public void loadData(ActionEvent actionEvent) throws SQLException {
        Admin admin = new Admin();
        ResultSet resultSet1 = admin.showMaleNumber();
        ResultSet resultSet2 = admin.showFemaleNumber();
        if((resultSet1 != null) && (resultSet2 != null)) {
            ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                    new PieChart.Data("male", resultSet1.getDouble(1)),
                    new PieChart.Data("female",resultSet2.getDouble(1))
            );

            pieChart.setData(list);
        }

    }
}
