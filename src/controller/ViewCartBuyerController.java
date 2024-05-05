package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CartDaoModel;
import model.Product;
import model.SessionManager;

import java.io.IOException;

public class ViewCartBuyerController {

	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, Integer> productID;
	@FXML
	private TableColumn<Product, String> prodName;
	@FXML
	private TableColumn<Product, Integer> noItems;
	@FXML
	private TableColumn<Product, Float> priceTax;
	@FXML
	private Label errorMessage;
	@FXML
	private Label successMessage;
	@FXML
	private Button logOutButton;
	@FXML
	private Button backToProductViewButton;
	@FXML
	private Button viewOrdersButton;
	@FXML
	private Button removeItemButton;
	@FXML
	private Button checkoutButton;

	private CartDaoModel cartDao = new CartDaoModel();

	@FXML
	public void initialize() {
		setupTableColumns();
		loadCartItems();
	}

	private void setupTableColumns() {
		productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
		prodName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		noItems.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceTax.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
				cellData.getValue().getPrice() * cellData.getValue().getQuantity()));
	}

	private void loadCartItems() {
		tableView.setItems(cartDao.loadCartItems(SessionManager.getCurrentUserId()));
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		SessionManager.clearCurrentSession();
		loadScene("/view/LoginPage.fxml");
	}

	@FXML
	private void backToProductView(ActionEvent event) throws IOException {
		loadScene("/view/ViewProductsBuyer.fxml");
	}

	@FXML
	private void viewOrders(ActionEvent event) throws IOException {
		loadScene("/view/ViewOrdersBuyer.fxml");
	}

	@FXML
	private void removeItem(ActionEvent event) {
		Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
		if (selectedProduct == null) {
			errorMessage.setText("Select an item to remove.");
			return;
		}
		if (cartDao.removeItemFromCart(selectedProduct.getProductId())) {
			successMessage.setText("Item removed successfully.");
			loadCartItems(); // Reload the cart items after removal
		} else {
			errorMessage.setText("Failed to remove item.");
		}
	}

	@FXML
	private void checkoutItems(ActionEvent event) {
		if (cartDao.checkoutCart(SessionManager.getCurrentUserId())) {
			successMessage.setText("Order confirmed successfully.");
			loadCartItems(); // Clear the cart view after checkout
		} else {
			errorMessage.setText("Failed to confirm the order.");
		}
	}

	private void loadScene(String fxmlPath) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = loader.load();
		Stage stage = (Stage) tableView.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
}