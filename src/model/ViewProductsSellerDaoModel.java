package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewProductsSellerDaoModel {

	public List<Product> getProductsBySeller(int sellerId) {
		List<Product> products = new ArrayList<>();
		String query = "SELECT * FROM products_table WHERE user_id = ?";
		try (Connection conn = new DBConnect().connect(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, sellerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(
						new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("description"),
								rs.getFloat("price"), rs.getInt("quantity"), rs.getString("product_SKU")

						));
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
		return products;
	}
}
