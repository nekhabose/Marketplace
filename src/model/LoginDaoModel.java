/*package model;

import java.sql.*;

public class LoginDaoModel {

    public boolean Validation(String username, String password, String role) {
        String query = "SELECT user_id FROM user_table WHERE user_name = ? AND user_password = ? AND user_role = ?";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}
*/

package model;

import java.sql.*;

public class LoginDaoModel {

    /**
     * Validates the user's login credentials and retrieves the user ID.
     * @param username the user's username
     * @param password the user's password
     * @param role the user's role (buyer or seller)
     * @return the user ID if credentials are valid, -1 if not valid
     */
    public int validateAndRetrieveUserId(String username, String password, String role) {
        String query = "SELECT user_id FROM user_table WHERE user_name = ? AND user_password = ? AND user_role = ?";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");  // Retrieve user ID from the result set
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;  // Return -1 if no user is found or if an error occurs
    }
}

/*package model;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDaoModel {

    public boolean Validation(String username, String password, String role) {
        String query = "SELECT user_password FROM user_table WHERE user_name = ? AND user_role = ?";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, role);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("user_password");
                return BCrypt.checkpw(password, storedPassword);
            }
            return false;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}*/



