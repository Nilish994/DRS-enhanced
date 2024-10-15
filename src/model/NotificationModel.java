/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class NotificationModel {

    // Observable list of notifications (will be bound to the UI in the View)
    private final ObservableList<String> notifications;

    // Listeners for changes to the notifications (typically the Controller or View)
    private final List<NotificationListener> listeners;

    // Constructor to initialize the notification list and listeners
    public NotificationModel() {
        this.notifications = FXCollections.observableArrayList();  // Observable list for JavaFX binding
        this.listeners = new ArrayList<>();
    }

    // Method to add a new notification to the list
    public void addNotification(String message) {
        notifications.add(message);  // Add new notification

        // Notify listeners (e.g., Controller or View) that a new notification is added
        for (NotificationListener listener : listeners) {
            listener.onNotificationAdded(message);
        }
    }

    // Method to get the observable list of notifications
    public ObservableList<String> getNotifications() {
        return notifications;
    }

    // Add a listener that will be notified when a new notification is added
    public void addNotificationListener(NotificationListener listener) {
        listeners.add(listener);
    }

    // Remove a listener (if needed)
    public void removeNotificationListener(NotificationListener listener) {
        listeners.remove(listener);
    }

    // Interface for notification listeners (Controller or View implements this)
    public interface NotificationListener {
        void onNotificationAdded(String message);
    }
}
