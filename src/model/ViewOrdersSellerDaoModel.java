/*package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrdersSellerDaoModel {

    public ObservableList<Order> loadOrdersForSeller(int sellerId) {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String query = "SELECT * FROM orders_table WHERE product_id IN (SELECT product_id FROM products_table WHERE user_id = ?)";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("status"),
                    rs.getInt("quantity"),
                    rs.getFloat("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return orders;
    }
}*/

/*package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrdersSellerDaoModel {

    public ObservableList<Order> loadOrdersForSeller(int sellerId) {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        // This query joins the orders_table with products_table to fetch orders for products that are owned by the seller
        String query = "SELECT o.order_id, o.user_id AS customer_id, o.product_id, o.product_name, o.status, o.quantity, o.price " +
                "FROM orders_table o INNER JOIN products_table p ON o.product_id = p.product_id " +
                "WHERE p.user_id = ?";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("status"),
                    rs.getInt("quantity"),
                    rs.getFloat("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return orders;
    }
}*/

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
        String query = "SELECT o.order_id, o.user_id , o.product_id, " +
                       "o.product_name, o.status, o.quantity, o.price " +
                       "FROM orders_table o " +
                       "INNER JOIN products_table p ON o.product_id = p.product_id " +
                       "WHERE p.user_id = ?"; // Ensure AS customer_id that only products belonging to the seller are considered

        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, sellerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"), // This is the user_id from orders_table, who is the customer
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("status"),
                    rs.getInt("quantity"),
                    rs.getFloat("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return orders;
    }
}