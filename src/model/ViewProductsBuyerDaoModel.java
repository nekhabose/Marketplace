
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

	public boolean addToCart(Product product, int quantity) {
		Integer userId = SessionManager.getCurrentUserId();
		System.out.println("Attempting to add to cart with user ID: " + userId);

		if (userId == null) {
			System.err.println("User ID is null, cannot proceed with adding to cart.");
			return false;
		}
		String query = "INSERT INTO orders_table (user_id, product_id, product_name, status, quantity, price) VALUES (?, ?, ?, 'in_cart', ?, ?)";
		try (Connection conn = new DBConnect().connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, SessionManager.getCurrentUserId());
			pstmt.setInt(2, product.getProductId());
			pstmt.setString(3, product.getProductName());
			pstmt.setInt(4, quantity);
			pstmt.setFloat(5, product.getPrice() * quantity);

			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			return false;
		}
	}
}