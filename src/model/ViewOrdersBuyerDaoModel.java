package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrdersBuyerDaoModel {

	public ObservableList<Order> loadOrders(int userId) {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		String query = "SELECT * FROM orders_table WHERE user_id = ?";
		try (Connection conn = new DBConnect().connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("order_id"), rs.getInt("user_id"), // Adding user_id fetched from the
																					// session
						rs.getInt("product_id"), rs.getString("product_name"), rs.getString("status"),
						rs.getInt("quantity"), rs.getFloat("price")));
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
		return orders;
	}

	public boolean cancelOrder(int orderId, int productId, int quantity) {
		Connection conn = null;
		try {
			conn = new DBConnect().connect();
			conn.setAutoCommit(false);

			String deleteQuery = "DELETE FROM orders_table WHERE order_id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
				pstmt.setInt(1, orderId);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows == 0) {
					return false;
				}
			}

			conn.commit();
			return true;
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					System.err.println("SQL Error on rollback: " + ex.getMessage());
				}
			}
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("Error closing connection: " + e.getMessage());
				}
			}
		}
	}

}