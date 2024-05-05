package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrdersSellerDaoModel {

	public ObservableList<Order> loadOrdersForSeller() {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		Integer sellerId = SessionManager.getCurrentUserId(); // Fetch the seller's user ID from the session
		if (sellerId == null) {
			System.err.println("Session not set with user ID.");
			return orders; // Return empty list if no user ID found
		}

		// Query to find all orders for products that belong to the current seller
		String query = "SELECT \r\n" + "    o.order_id,\r\n" + "    o.user_id,\r\n" + "    o.product_id,\r\n"
				+ "    o.product_name,\r\n" + "    o.status,\r\n" + "    o.quantity,\r\n" + "    o.price\r\n"
				+ "FROM \r\n" + "    orders_table o\r\n" + "INNER JOIN \r\n"
				+ "    products_table p ON o.product_id = p.product_id\r\n" + "WHERE \r\n" + "    p.user_id = ?;";

		try (Connection conn = new DBConnect().connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, sellerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("order_id"), rs.getInt("user_id"), rs.getInt("product_id"),
						rs.getString("product_name"), rs.getString("status"), rs.getInt("quantity"),
						rs.getFloat("price")));
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
		return orders;
	}
}