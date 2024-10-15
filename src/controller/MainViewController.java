/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rxncor
 */
public class MainViewController implements Initializable {

    @FXML
    public Pane viewPlaceholderLeft;
    @FXML
    public Pane viewPlaceholderRight;
    @FXML
    public Button startbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadViewLeft("/view/DefultButtons.fxml");
        loadViewRight("/view/DisasterMonitorView.fxml");
        // TODO
    }

    // Method to load FXML into viewPlaceholderLeft
    public void loadViewLeft(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newView = loader.load();

            // If loading DefultButtons.fxml, pass the MainViewController reference
            if (fxmlFile.equals("/view/DefultButtons.fxml")) {
                DefultButtonsController buttonsController = loader.getController();
                buttonsController.setMainViewController(this);  // Pass MainViewController reference
            }
            // Pass MainViewController reference to LoginViewController if login view is being loaded
            if (fxmlFile.equals("/view/LoginView.fxml")) {
                UserController loginController = loader.getController();
                loginController.setMainViewController(this);
            }
                        // Pass MainViewController reference to LoginViewController if login view is being loaded
            if (fxmlFile.equals("/view/DisasterReportView.fxml")) {
                UserController loginController = loader.getController();
                loginController.setMainViewController(this);
            }
                        // Pass MainViewController reference to LoginViewController if login view is being loaded
            if (fxmlFile.equals("/view/DisasterMonitorView.fxml")) {
                UserController loginController = loader.getController();
                loginController.setMainViewController(this);
            }

            viewPlaceholderLeft.getChildren().clear();
            viewPlaceholderLeft.getChildren().add(newView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadViewRight(String fxmlFile) {
        try {
            // Load the new FXML view
            Pane newView2 = FXMLLoader.load(getClass().getResource(fxmlFile));
            // Clear the placeholder and set the new view
            viewPlaceholderRight.getChildren().clear();
            viewPlaceholderRight.getChildren().add(newView2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void start() {

    }

    @FXML
    private void onLoginButtonClick() {
        loadViewLeft("/view/LoginView.fxml");
    }
    @FXML
    public void onReportDesasterBtnClick() {
        System.out.println("3");
        loadViewRight("/view/DisasterReportView.fxml");
        System.out.println("4");
    }

    @FXML
    public void onEvBtnClick() {
        // Your code here
    }

    @FXML
    public void onOnGoingDisastersBtnClick() {
        System.out.println("5");
       loadViewRight("/view/DisasterMonitorView.fxml");
        System.out.println("6");
    }
}
