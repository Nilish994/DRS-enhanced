/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author rxncor
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DatabaseConnection;
import model.Disaster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisasterMonitorController {

    @FXML
    private TableView<Disaster> disasterTableView;
    @FXML
    private TableColumn<Disaster, String> disasterTypeColumn;
    @FXML
    private TableColumn<Disaster, String> locationColumn;
    @FXML
    private TableColumn<Disaster, String> severityColumn;
    @FXML
    private TableColumn<Disaster, String> dateColumn;

    private ObservableList<Disaster> disasterList = FXCollections.observableArrayList();

    public void initialize() {
        // Set up the table columns
        disasterTypeColumn.setCellValueFactory(new PropertyValueFactory<>("disasterType"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load data from the database
        loadDisasterData();
    }

    private void loadDisasterData() {
        String query = "SELECT * FROM disasters";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Disaster disaster = new Disaster(
                    rs.getString("disaster_type"),
                    rs.getString("location"),
                    rs.getString("severity"),
                    rs.getTimestamp("date"),
                    rs.getString("description")
                );
                disasterList.add(disaster);
            }

            disasterTableView.setItems(disasterList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
