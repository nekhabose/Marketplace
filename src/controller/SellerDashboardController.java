package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SellerDashboardController {

	@FXML
	public void addProduct(ActionEvent event) {
		loadScene("/view/AddProducts.fxml");
	}

	@FXML
	public void viewProduct(ActionEvent event) {
		loadScene("/view/ViewProductsSeller.fxml");
	}

	@FXML
	public void viewOrder(ActionEvent event) {
		loadScene("/view/ViewOrdersSeller.fxml");
	}

	@FXML
	public void viewProfile(ActionEvent event) {
		loadScene("/view/ProfileSeller.fxml");
	}

	@FXML
	public void viewAbout(ActionEvent event) {
		loadScene("/view/Aboutpage.fxml");
	}

	@FXML
	public void logOut(ActionEvent event) {
		loadScene("/view/Loginpage.fxml"); 
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
}