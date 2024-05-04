package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DBConnect;
import model.Product;
import model.SessionManager;
import model.ViewProductsBuyerDaoModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProductsBuyerController {

    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, Integer> itemIdCol;
    @FXML
    private TableColumn<Product, String> itemNameCol;
    @FXML
    private TableColumn<Product, String> itemDescriptionCol;
    @FXML
    private TableColumn<Product, Float> itemPriceCol;
    @FXML
    private TableColumn<Product, Integer> itemQuantityCol;
    @FXML
    private TableColumn<Product, String> itemSKUCol;
    @FXML
    private TextField quantity; // Corrected the fx:id to match FXML
    @FXML
    private Label errorMessage;

    private ViewProductsBuyerDaoModel daoModel = new ViewProductsBuyerDaoModel();

    public void initialize() {
        setupTable();
        loadProducts();
    }

    private void setupTable() {
        itemIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemSKUCol.setCellValueFactory(new PropertyValueFactory<>("productSKU"));
    }

    private void loadProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try (Connection conn =  new DBConnect().connect(); // Assuming DBConnect has a static connect method
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products_table")) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getFloat("price"),
                    rs.getInt("quantity"),
                    rs.getString("product_SKU")
                ));
            }
            tableView.setItems(products);
        } catch (SQLException e) {
            errorMessage.setText("Failed to load products: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
   
    
    @FXML
    private void addToCart(ActionEvent event) {
    	 if (SessionManager.getCurrentUserId() == null) {
    	        errorMessage.setText("No logged-in user. Please log in to continue.");
    	        return;
    	 }
    	
        try {
            Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
 
            if (selectedProduct == null) {
            	
                errorMessage.setText("No product selected. Please select a product.");
                return;
            }
            
            int quantity = Integer.parseInt(this.quantity.getText());
            if (quantity <= 0) {
            	
                errorMessage.setText("Please enter a positive number for quantity.");
                return;
            }

            if (daoModel.addToCart(selectedProduct, quantity)) {
                showAlert("Product added to cart successfully!");
                errorMessage.setText(""); // Clear error message
            } else {
            	
                errorMessage.setText("Failed to add product to cart.");
            }
        } catch (NumberFormatException e) {
        	
            errorMessage.setText("Invalid quantity format.");
        } catch (Exception e) {
        	
            errorMessage.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void viewCart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewCartBuyer.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            errorMessage.setText("Failed to load the cart view: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void viewOrders(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewOrdersBuyer.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            errorMessage.setText("Failed to load the orders view: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void logOut() {
        SessionManager.clearCurrentSession();

        System.out.println("User logged out. Current user ID: " + SessionManager.getCurrentUserId());
        loadScene("/view/LoginPage.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            errorMessage.setText("Failed to load the scene: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


