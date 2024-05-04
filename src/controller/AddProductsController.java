
/*package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddProductsController {

    @FXML
    private TextField productName;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField productSKU;

    @FXML
    private Button addButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button viewProductsButton;

    @FXML
    public void addProduct(ActionEvent event) {
        if (productName.getText().isEmpty() || description.getText().isEmpty() || 
            price.getText().isEmpty() || quantity.getText().isEmpty() || productSKU.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill all the text fields.");
            alert.showAndWait();
        } else {
            // Logic to add the product to the database
            System.out.println("Adding product...");
            // Placeholder for database interaction
            clearFields();
        }
    }

    @FXML
    public void goToPrevious(ActionEvent event) {
        loadScene("/view/SellerDashboard.fxml"); // Correct path to your Seller Dashboard FXML
    }

    @FXML
    public void viewProducts(ActionEvent event) {
        loadScene("/view/ViewProductSeller.fxml"); // Correct path to your View Products FXML
    }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) addButton.getScene().getWindow(); // Using addButton to get the current stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        productName.clear();
        description.clear();
        price.clear();
        quantity.clear();
        productSKU.clear();
    }
}*/

/*package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import model.AddProductDAO;
import model.AddProductsDaoModel;

public class AddProductsController {

    @FXML
    private TextField productName;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField productSKU;

    private AddProductsDaoModel addProductDAO = new AddProductsDaoModel();  // Create an instance of AddProductDAO

    @FXML
    public void addProduct(ActionEvent event) {
        if (productName.getText().isEmpty() || description.getText().isEmpty() ||
            price.getText().isEmpty() || quantity.getText().isEmpty() || productSKU.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill all the text fields.");
            alert.showAndWait();
        } else {
            if (addProductDAO.insertProduct(productName.getText(), description.getText(), price.getText(), quantity.getText(), productSKU.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product added successfully!");
                alert.showAndWait();
                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText("Failed to Add Product");
                alert.setContentText("Please try again!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void goToPrevious(ActionEvent event) {
        // Method to load the previous scene, e.g., the seller dashboard
    	loadScene("/view/Dashboard.fxml");
    }

    @FXML
    public void viewProducts(ActionEvent event) {
        // Method to load the scene that displays the list of products
    	loadScene("/view/ViewProductsSeller.fxml");
    }

    private void clearFields() {
        productName.clear();
        description.clear();
        price.clear();
        quantity.clear();
        productSKU.clear();
    }
    private void loadScene(String fxmlPath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Fetch the stage from any node that is currently being displayed
            Stage stage;
            if (root.getScene() != null && root.getScene().getWindow() != null) {
                stage = (Stage) root.getScene().getWindow();
            } else {
                // If there's no existing scene or window, create a new stage
                stage = new Stage();
                stage.setTitle("New Window");
            }

            // Set the scene to the stage and show it
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading scene for FXML path: " + fxmlPath);
            e.printStackTrace();
        }
    }
}*/
/*package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import model.AddProductDAO;
import model.AddProductsDaoModel;



import model.SessionManager;  // This assumes you have a session manager that keeps track of logged-in user


public class AddProductsController {

    @FXML
    private TextField productName;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField productSKU;

    private AddProductsDaoModel addProductDAO = new AddProductsDaoModel();  // Create an instance of AddProductDAO

    @FXML
    public void addProduct(ActionEvent event) {
    	int userId = SessionManager.getCurrentUserId();
        if (productName.getText().isEmpty() || description.getText().isEmpty() ||
            price.getText().isEmpty() || quantity.getText().isEmpty() || productSKU.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill all the text fields.");
            alert.showAndWait();
        } else {
            if (addProductDAO.insertProduct(productName.getText(), description.getText(), price.getText(), quantity.getText(), productSKU.getText(), userId)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product added successfully!");
                alert.showAndWait();
                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText("Failed to Add Product");
                alert.setContentText("Please try again!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void goToPrevious(ActionEvent event) {
        // Method to load the previous scene, e.g., the seller dashboard
    	loadScene("/view/Dashboard.fxml");
    }

    @FXML
    public void viewProducts(ActionEvent event) {
        // Method to load the scene that displays the list of products
    	loadScene("/view/ViewProductsSeller.fxml");
    }

    private void clearFields() {
        productName.clear();
        description.clear();
        price.clear();
        quantity.clear();
        productSKU.clear();
    }
    private void loadScene(String fxmlPath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Fetch the stage from any node that is currently being displayed
            Stage stage;
            if (root.getScene() != null && root.getScene().getWindow() != null) {
                stage = (Stage) root.getScene().getWindow();
            } else {
                // If there's no existing scene or window, create a new stage
                stage = new Stage();
                stage.setTitle("New Window");
            }

            // Set the scene to the stage and show it
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading scene for FXML path: " + fxmlPath);
            e.printStackTrace();
        }
    }
}*/

/*package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.AddProductsDaoModel;
import model.SessionManager;

public class AddProductsController {

    @FXML
    private TextField productName;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField productSKU;

    private AddProductsDaoModel addProductDAO = new AddProductsDaoModel();  // Create an instance of AddProductsDaoModel

    @FXML
    public void addProduct(ActionEvent event) {
        if (allFieldsAreFilled()) {
            int userId = SessionManager.getCurrentUserId();  // Fetch the current user's ID from the session manager
            if (addProductDAO.insertProduct(productName.getText(), description.getText(), price.getText(), quantity.getText(), productSKU.getText(), userId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to Add Product");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill all the text fields.");
        }
    }

    private boolean allFieldsAreFilled() {
        return !productName.getText().isEmpty() && !description.getText().isEmpty() &&
               !price.getText().isEmpty() && !quantity.getText().isEmpty() && !productSKU.getText().isEmpty();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void goToPrevious(ActionEvent event) {
        // Method to load the previous scene, e.g., the seller dashboard
        loadScene("/view/Dashboard.fxml");
    }

    @FXML
    public void viewProducts(ActionEvent event) {
        // Method to load the scene that displays the list of products
        loadScene("/view/ViewProductsSeller.fxml");
    }

    private void clearFields() {
        productName.clear();
        description.clear();
        price.clear();
        quantity.clear();
        productSKU.clear();
    }

    private void loadScene(String fxmlPath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Fetch the stage from any node that is currently being displayed
            Stage stage;
            if (root.getScene() != null && root.getScene().getWindow() != null) {
                stage = (Stage) root.getScene().getWindow();
            } else {
                // If there's no existing scene or window, create a new stage
                stage = new Stage();
                stage.setTitle("New Window");
            }

            // Set the scene to the stage and show it
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading scene for FXML path: " + fxmlPath);
            e.printStackTrace();
        }
    }
}*/



package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import model.AddProductsDaoModel;
import model.SessionManager;

public class AddProductsController {

    @FXML
    private TextField productName;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField productSKU;

    private AddProductsDaoModel addProductDAO = new AddProductsDaoModel();  // Create an instance of AddProductsDaoModel

    @FXML
    public void addProduct(ActionEvent event) {
        try {
            if (allFieldsAreFilled()) {
                int userId = SessionManager.getCurrentUserId();  // Fetch the current user's ID from the session manager
                if (addProductDAO.insertProduct(productName.getText(), description.getText(), price.getText(), quantity.getText(), productSKU.getText(), userId)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully!");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to Add Product");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill all the text fields.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean allFieldsAreFilled() {
        return !productName.getText().isEmpty() && !description.getText().isEmpty() &&
               !price.getText().isEmpty() && !quantity.getText().isEmpty() && !productSKU.getText().isEmpty();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        productName.clear();
        description.clear();
        price.clear();
        quantity.clear();
        productSKU.clear();
    }

    @FXML
    public void goToPrevious(ActionEvent event) {
        // Navigate back to the previous scene (adjust the path to your actual previous FXML)
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));  // Assuming 'Dashboard.fxml' is your previous page
            Stage stage = (Stage) productName.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Scene Error", "Failed to load the previous page.");
            e.printStackTrace();
        }
    }

    @FXML
    public void viewProducts(ActionEvent event) {
        // Method to load the scene that displays the list of products
        loadScene("/view/ViewProductsSeller.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) productName.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Scene Error", "Failed to load the scene: " + fxmlPath);
            e.printStackTrace();
        }
    }
}



