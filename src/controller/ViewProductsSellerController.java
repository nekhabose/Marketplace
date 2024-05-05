
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DBConnect;
import model.Product;
import model.SessionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProductsSellerController {

	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, Integer> itemIdCol;
	@FXML
	private TableColumn<Product, String> itemNameCol;
	@FXML
	private TableColumn<Product, String> itemDescriptionCol;
	@FXML
	private TableColumn<Product, String> itemPriceCol;
	@FXML
	private TableColumn<Product, Integer> itemQuantityCol;
	@FXML
	private TableColumn<Product, String> itemSKUCol;

	@FXML
	public void initialize() {
		itemIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
		itemNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		itemSKUCol.setCellValueFactory(new PropertyValueFactory<>("productSKU"));

		tableView.setItems(loadProducts());
	}

	@FXML
	private void goToPrevious() {
		loadScene("/view/Dashboard.fxml");
	}

	private ObservableList<Product> loadProducts() {
		ObservableList<Product> products = FXCollections.observableArrayList();
		String query = "SELECT * FROM products_table WHERE user_id = ?";

		try (Connection conn = new DBConnect().connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, SessionManager.getCurrentUserId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				products.add(
						new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("description"),
								rs.getFloat("price"), rs.getInt("quantity"), rs.getString("product_SKU")
//                   rs.getInt("user_id")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	private void loadScene(String fxmlPath) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			Stage stage = (Stage) tableView.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}