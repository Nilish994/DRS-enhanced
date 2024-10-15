/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;



import javafx.application.Platform;
import model.NotificationModel;
import view.NotificationView;

import java.net.URI;

public class NotificationController implements NotificationModel.NotificationListener {

    private final NotificationModel model;
    private final NotificationView view;

    // Constructor to initialize the controller with the model and view
    public NotificationController(NotificationModel model, NotificationView view) {
        this.model = model;
        this.view = view;

        // Add this controller as a listener to the model
        model.addNotificationListener(this);

        // Connect to the WebSocket server when the controller is initialized
        connectToServer();
    }

    // Method to connect to the WebSocket server
    public void connectToServer() {
        try {
            // Connect the WebSocket client (model)
            model.connect();
            System.out.println("Connected to WebSocket server.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to connect to WebSocket server.");
        }
    }

    // Method to handle received notifications (called by the model)
    @Override
    public void onNotificationReceived(String message) {
        // Ensure the UI update is done on the JavaFX Application thread
        Platform.runLater(() -> {
            // Update the view with the new notification
            view.updateNotification(message);
        });
    }

    // Method to disconnect from the server (for cleanup)
    public void disconnectFromServer() {
        try {
            model.close();  // Close the WebSocket connection
            System.out.println("Disconnected from WebSocket server.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to disconnect from WebSocket server.");
        }
    }
}

