/*package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

    public ObservableList<Product> fetchAllProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM products_table";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getFloat("price"),
                    rs.getInt("quantity"),
                    rs.getString("product_SKU")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public boolean addToCart(Product product, int quantity) {
        String query = "INSERT INTO cart (product_id, quantity) VALUES (?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setInt(2, quantity);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}*/
/*
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

    public boolean addToCart(Product product, int quantity) {
        // You will need to implement this method to insert a record into the cart table
        String query = "INSERT INTO cart (product_id, user_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setInt(2, SessionManager.getCurrentUserId());
            pstmt.setInt(3, quantity);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
    
}*/
/*package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

    public boolean addToCart(Product product, int quantity) {
        // Assuming orders_table has a status column where 'in_cart' signifies that the item is added to the cart but not purchased
        String query = "INSERT INTO orders_table (user_id, product_id, product_name,status, quantity, price) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(2, SessionManager.getCurrentUserId()); // User ID from session
            pstmt.setInt(3, product.getProductId()); // Product ID
            pstmt.setString(4, product.getProductName()); 
            pstmt.setString(5, "in_cart");                      // Status as in cart
            pstmt.setInt(6, quantity);                          // Quantity
            pstmt.setFloat(7, product.getPrice() * quantity);   // Price calculated as product price * quantity
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}*/
/*package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

    public boolean addToCart(Product product, int quantity) {
    	 Integer userId = SessionManager.getCurrentUserId();
    	    if (userId == null) {
    	        System.err.println("User ID is null. Please ensure the user is logged in.");
    	        return false;
    	    }

        // Assuming orders_table has a status column where 'in_cart' signifies that the item is added to the cart but not purchased
        // 'order_id' is omitted in the query as it's likely an auto-increment column in the database
        String query = "INSERT INTO orders_table (user_id, product_id, product_name, status, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, SessionManager.getCurrentUserId()); // User ID from session
            pstmt.setInt(2, product.getProductId()); // Product ID
            pstmt.setString(3, product.getProductName()); // Product Name
            pstmt.setString(4, "in_cart"); // Status as in cart
            pstmt.setInt(5, quantity); // Quantity
            pstmt.setFloat(6, product.getPrice() * quantity); // Price calculated as product price * quantity
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}*/

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewProductsBuyerDaoModel {

    public boolean addToCart(Product product, int quantity) {
    	Integer userId = SessionManager.getCurrentUserId();
        System.out.println("Attempting to add to cart with user ID: " + userId); // Debug statement

        if (userId == null) {
            System.err.println("User ID is null, cannot proceed with adding to cart.");
            return false;
        }
        String query = "INSERT INTO orders_table (user_id, product_id, product_name, status, quantity, price) VALUES (?, ?, ?, 'in_cart', ?, ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
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