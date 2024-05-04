package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ViewOrdersBuyerDaoModel;
import model.Order;
import model.SessionManager;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class ViewOrdersBuyerController {

    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Integer> orderID;
    @FXML
    private TableColumn<Order, Integer> userID;  // Added user ID column setup
    @FXML
    private TableColumn<Order, Integer> productID;
    @FXML
    private TableColumn<Order, String> productName;
    @FXML
    private TableColumn<Order, String> orderStatus;
    @FXML
    private TableColumn<Order, Integer> quantity;
    @FXML
    private TableColumn<Order, Float> price;

    private ViewOrdersBuyerDaoModel orderDao = new ViewOrdersBuyerDaoModel();

    public void initialize() {
        setupTableColumns();
        loadOrders();
    }

    private void setupTableColumns() {
        orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        userID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(SessionManager.getCurrentUserId())); // Dynamically fetches and displays the current user ID
        productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void loadOrders() {
        tableView.setItems(orderDao.loadOrders(SessionManager.getCurrentUserId()));
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        SessionManager.clearCurrentSession();
        loadScene("/view/LoginPage.fxml");
    }

    @FXML
    private void backToShop(ActionEvent event) throws IOException {
        loadScene("/view/ViewProductsBuyer.fxml");
    }
    
    @FXML
    private void previous(ActionEvent event) throws IOException {
        loadScene("/view/ViewCartBuyer.fxml");
    }

    @FXML
    private void cancelOrder(ActionEvent event) {
        Order selectedOrder = tableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("No order selected", "Please select an order to cancel.");
            return;
        }
        if (selectedOrder.getStatus().equals("confirmed")) {
            showAlert("Order cannot be cancelled", "Confirmed orders cannot be cancelled.");
            return;
        }
        if (orderDao.cancelOrder(selectedOrder.getOrderId(), selectedOrder.getProductId(), selectedOrder.getQuantity())) {
            showAlert("Order Cancelled", "The order has been cancelled successfully.");
            loadOrders(); // Reload the orders
        } else {
            showAlert("Cancellation Failed", "Failed to cancel the order.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}