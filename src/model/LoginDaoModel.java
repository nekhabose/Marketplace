package model;

import java.sql.*;

public class LoginDaoModel {

	public int validateAndRetrieveUserId(String username, String password, String role) {
		String query = "SELECT user_id FROM user_table WHERE user_name = ? AND user_password = ? AND user_role = ?";
		try (Connection conn = new DBConnect().connect(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, role);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("user_id"); // Retrieve user ID from the result set
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		}
		return -1; // Return -1 if no user is found or if an error occurs
	}
}
