package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDaoModel {

    /**
     * Load the cart items for the given user.
     */
    public ObservableList<Product> loadCartItems(int userId) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM orders_table WHERE user_id = ? AND status = 'in_cart'";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    null,
                    rs.getFloat("price"),
                    rs.getInt("quantity"),
                    null
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return products;
    }

    /**
     * Remove a single item from the cart.
     */
    public boolean removeItemFromCart(int productId) {
        String query = "DELETE FROM orders_table WHERE product_id = ? AND status = 'in_cart'";
        try (Connection conn = new DBConnect().connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checkout the cart, confirming all items and updating inventory.
     */
    public boolean checkoutCart(int userId) {
        Connection conn = null;
        try {
            conn = new DBConnect().connect();
            conn.setAutoCommit(false);  // Use transaction to ensure data integrity
            ObservableList<Product> cartItems = loadCartItems(userId);
            for (Product product : cartItems) {
                // Update the product quantity in products_table
                updateProductQuantity(conn, product.getProductId(), -product.getQuantity());

                // Update the order status in orders_table
                String updateOrder = "UPDATE orders_table SET status = 'confirmed' WHERE user_id = ? AND product_id = ? AND status = 'in_cart'";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateOrder)) {
                    updateStmt.setInt(1, userId);
                    updateStmt.setInt(2, product.getProductId());
                    updateStmt.executeUpdate();
                }
            }
            conn.commit();  // Commit the transaction
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback the transaction on error
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

    /**
     * Update the quantity of a product in products_table.
     */
    private void updateProductQuantity(Connection conn, int productId, int quantityChange) throws SQLException {
        String query = "UPDATE products_table SET quantity = quantity + ? WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, quantityChange);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }
}