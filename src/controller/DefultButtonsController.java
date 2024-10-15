/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author rxncor
 */
public class DefultButtonsController implements Initializable {

    @FXML
    private Button reportButton;
    @FXML
    private Button loginBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Button ReportDesasterBtn;
    @FXML
    private Button EvBtn;
    @FXML
    private Button OnGoingDisastersBtn;

    private MainViewController mainViewController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @FXML
    public void onLoginButtonClick() {
        System.out.println("1");
        mainViewController.loadViewLeft("/view/LoginView.fxml");
        System.out.println("2");
    }

    @FXML
    public void onReportDesasterBtnClick() {
        System.out.println("3");
        mainViewController.loadViewRight("/view/DisasterReportView.fxml");
        System.out.println("4");
    }

    @FXML
    public void onEvBtnClick() {
        // Your code here
    }

    @FXML
    public void onOnGoingDisastersBtnClick() {
        System.out.println("5");
        mainViewController.loadViewRight("/view/DisasterMonitorView.fxml");
        System.out.println("6");
    }
}
