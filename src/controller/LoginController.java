
/*package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.LoginDaoModel;


public class LoginController {
    @FXML
    private TextField usrnm;
    @FXML
    private TextField pswd;
    @FXML
    private Button buyerButton;
    @FXML
    private Button sellerButton;
    @FXML
    private Button signUpButton;

    public void handleLogin(ActionEvent event) {
        String usernm = usrnm.getText();
        String psword = pswd.getText();
        String role = (event.getSource() == buyerButton) ? "buyer" : "seller";

        
        LoginDaoModel dao = new LoginDaoModel();
        boolean valid = dao.Validation(usernm, psword,role);
        if (valid) {
            try {

                Parent root;
                if (event.getSource() == buyerButton) {
                    root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                }
                Scene scene = new Scene(root);
                Stage stage = (Stage) usrnm.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Invalid credentials. Please try again!");
            errorAlert.showAndWait();
        }
    }

    public void handleSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup_page.fxml"));
            Parent signUproot = loader.load();
            Scene signUpScene = new Scene(signUproot);
            Stage stage = (Stage) usrnm.getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Signup");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/

package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.LoginDaoModel;
import model.SessionManager; // Make sure to import the SessionManager

public class LoginController {
    @FXML
    private TextField usrnm;
    @FXML
    private TextField pswd;
    @FXML
    private Button buyerButton;
    @FXML
    private Button sellerButton;
    @FXML
    private Button signUpButton;

    public void handleLogin(ActionEvent event) {
        String usernm = usrnm.getText();
        String psword = pswd.getText();
        String role = (event.getSource() == buyerButton) ? "buyer" : "seller";
        
        LoginDaoModel dao = new LoginDaoModel();
        int userId = dao.validateAndRetrieveUserId(usernm, psword, role); // Modified to retrieve user ID
     // Example: Assuming you have a method that handles login and receives a user ID upon success
        

            
            // Proceed with loading the main application screen
        

        if (userId != -1) { // Assuming -1 denotes an invalid user
            SessionManager.setCurrentUserId(userId); // Set user ID in session
            System.out.println("Logged in with user ID: " + SessionManager.getCurrentUserId()); // Debug statement

            try {
                Parent root;
                if (event.getSource() == buyerButton) {
                    root = FXMLLoader.load(getClass().getResource("/view/ViewProductsBuyer.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                }
                Scene scene = new Scene(root);
                Stage stage = (Stage) usrnm.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Invalid credentials. Please try again!");
            errorAlert.showAndWait();
        }
    }

    public void handleSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup_page.fxml"));
            Parent signUproot = loader.load();
            Scene signUpScene = new Scene(signUproot);
            Stage stage = (Stage) usrnm.getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Signup");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

