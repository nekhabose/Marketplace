package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order;
import model.ViewOrdersSellerDaoModel;
import java.io.IOException;

public class ViewOrdersSellerController {

	@FXML
	private TableView<Order> tableView;
	@FXML
	private TableColumn<Order, Integer> orderID;
	@FXML
	private TableColumn<Order, Integer> userId;
	@FXML
	private TableColumn<Order, Integer> productID;
	@FXML
	private TableColumn<Order, String> productName;
	@FXML
	private TableColumn<Order, String> status;
	@FXML
	private TableColumn<Order, Integer> quantity;
	@FXML
	private TableColumn<Order, Float> totalPrice;

	private ViewOrdersSellerDaoModel daoModel = new ViewOrdersSellerDaoModel();

	@FXML
	public void initialize() {
		setupTableColumns();
		loadOrders();
	}

	private void setupTableColumns() {
		orderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
		productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
	}

	private void loadOrders() {
		tableView.setItems(daoModel.loadOrdersForSeller());
	}

	@FXML
	private void goBack() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) tableView.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
}