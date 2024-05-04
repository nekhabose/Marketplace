/*package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import util.DBConnect;  // Ensure you have a utility class to handle DB connections

public class AddProductsDaoModel{

    public boolean insertProduct(String name, String description, String price, String quantity, String sku) {
        String query = "INSERT INTO products_table (product_name, description, price, quantity, product_sku) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnect().connect();  // Adjust according to your DB connection method
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBigDecimal(3, new BigDecimal(price));
            ps.setInt(4, Integer.parseInt(quantity));
            ps.setString(5, sku);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}
*/

/*package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductsDaoModel {

    public boolean insertProduct(String name, String description, String price, String quantity, String sku, int userId) {
        String query = "INSERT INTO products_table (product_name, description, price, quantity, product_sku, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnect().connect();  // Adjust according to your DB connection method
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBigDecimal(3, new BigDecimal(price));
            ps.setInt(4, Integer.parseInt(quantity));
            ps.setString(5, sku);
            ps.setInt(6, userId);  // Add the user_id to the insert statement
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}
*/



package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductsDaoModel {

    public boolean insertProduct(String name, String description, String price, String quantity, String sku, int userId) {
        String query = "INSERT INTO products_table (product_name, description, price, quantity, product_SKU, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBigDecimal(3, new BigDecimal(price));
            ps.setInt(4, Integer.parseInt(quantity));
            ps.setString(5, sku);
            ps.setInt(6, userId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}