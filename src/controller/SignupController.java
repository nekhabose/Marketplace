	package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SignupDaoModel;

public class SignupController {
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField contactNumber;
    @FXML
    private TextField gender;
    @FXML
    private TextField address;
    @FXML
    private Button sellerButton;
    @FXML
    private Button buyerButton;
    @FXML
    private Button loginButton;

    public void handleUserSignup(ActionEvent event) {
        SignupDaoModel dao = new SignupDaoModel();
        String userType = event.getSource() == sellerButton ? "seller" : "buyer";
        boolean success = dao.addUser(userName.getText(), password.getText(), email.getText(), contactNumber.getText(), gender.getText(), address.getText(), userType);
        
        if (success) {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Signup successful! Press OK to continue");
            infoAlert.showAndWait();
            
            redirectLogin();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Error during signup. Please check your information and try again!");
            errorAlert.showAndWait();
        }
    }

    public void redirectLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) userName.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}