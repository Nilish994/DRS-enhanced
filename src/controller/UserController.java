package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DatabaseConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;

public class UserController {

    private MainViewController mainViewController;

    @FXML
    private Button loginSubmitButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Max login attempts before locking the account
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 0; // Track login attempts

    // This method is triggered when the login button is clicked
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText(); // In a real app, hash this password

        if (authenticateUser(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            mainViewController.loadViewLeft("/view/WelcomeView.fxml");
            mainViewController.loadViewRight("/view/DisasterReportView.fxml");
            resetLoginAttempts(); // Reset attempts on successful login
        } else {
            loginAttempts++;
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                showAlert(Alert.AlertType.ERROR, "Account Locked", "Too many failed login attempts. Your account is locked.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password. Please try again.");
            }
        }
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    // Authenticate the user by checking username and hashed password in the database
    private boolean authenticateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            // Check if the user exists
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                // Hash the input password and compare it to the stored hash
                String hashedPassword = hashPassword(password);
                return storedHashedPassword.equals(hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString(); // Return hashed password
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Utility method to show alerts for feedback (success or error)
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Reset login attempts after successful login
    private void resetLoginAttempts() {
        loginAttempts = 0;
    }
}
