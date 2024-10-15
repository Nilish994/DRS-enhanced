/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author rxncor
 */

import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.DatabaseConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.text.html.ListView;
import org.graalvm.nativeimage.Platform;

public class DisasterController implements Initializable {

    @FXML
    private ComboBox<String> disasterTypeComboBox;

    @FXML
    private TextField disasterTypeField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField severityField;

    @FXML
    private TextArea descriptionField;
private ListView notificationListView;
    /**
     *
     * @param url
     * @param resourceBundle
     */
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the disaster type ComboBox when the form is loaded
        loadDisasterTypes();
        
        // Add listener to ComboBox for when a disaster type is selected
        disasterTypeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            // Set the selected disaster type in the disasterTypeField
            disasterTypeField.setText(newValue);
        });
    }
    // Method to load disaster types from the database and populate the ComboBox
    private void loadDisasterTypes() {
        String query = "SELECT disaster_name FROM disaster_types";
        ObservableList<String> disasterTypes = FXCollections.observableArrayList();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Add all disaster types from the database to the ComboBox
            while (rs.next()) {
                disasterTypes.add(rs.getString("disaster_name"));
            }

            disasterTypeComboBox.setItems(disasterTypes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Save reported disaster to the database
    public void handleReportDisaster() {
        String disasterType = disasterTypeField.getText();
        String location = locationField.getText();
        String severity = severityField.getText();
        String description = descriptionField.getText();

        String query = "INSERT INTO disasters (disaster_type, location, severity, description, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, disasterType);
            stmt.setString(2, location);
            stmt.setString(3, severity);
            stmt.setString(4, description);
            stmt.setDate(5, new java.sql.Date(new Date().getTime()));

            stmt.executeUpdate();
            System.out.println("Disaster reported: " + disasterType);
            // Prepare the notification message
            String notificationMessage = "New disaster reported: " + disasterType + " at " + location + " with severity " + severity;

            // Add the notification to the ListView
            Platform.runLater(() -> notificationListView.getItems().add(notificationMessage));  // Updating UI on the JavaFX thread

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Setter method to inject the ListView from FXML (or initialize it in your controller)
    public void setNotificationListView(ListView<String> notificationListView) {
        this.notificationListView = notificationListView;
    }
    // Update disaster details
    public void updateDisaster(int id, String disasterType, String location, String severity, String description) {
        String query = "UPDATE disasters SET disaster_type = ?, location = ?, severity = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, disasterType);
            stmt.setString(2, location);
            stmt.setString(3, severity);
            stmt.setString(4, description);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            System.out.println("Disaster updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a disaster
    public void deleteDisaster(int id) {
        String query = "DELETE FROM disasters WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Disaster deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
