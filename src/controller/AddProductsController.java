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

	private AddProductsDaoModel addProductDAO = new AddProductsDaoModel(); // Create an instance of AddProductsDaoModel

	@FXML
	public void addProduct(ActionEvent event) {
		try {
			if (allFieldsAreFilled()) {
				int userId = SessionManager.getCurrentUserId(); // Fetch the current user's ID from the session manager
				if (addProductDAO.insertProduct(productName.getText(), description.getText(), price.getText(),
						quantity.getText(), productSKU.getText(), userId)) {
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
		return !productName.getText().isEmpty() && !description.getText().isEmpty() && !price.getText().isEmpty()
				&& !quantity.getText().isEmpty() && !productSKU.getText().isEmpty();
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
		// Navigate back to the previous scene
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
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
