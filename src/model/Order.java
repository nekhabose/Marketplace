package model;

public class Order {
    private int orderId;
    private int userId;
    private int productId;
    private String productName;
    private String status;
    private int quantity;
    private float totalPrice;

    public Order(int orderId, int userId, int productId, String productName, String status, int quantity, float totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}