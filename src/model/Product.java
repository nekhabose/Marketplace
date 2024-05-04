/*package model;

public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private Float price;
    private int quantity;
    private String product_SKU;
    private int user_id;

    public Product(int product_id, String product_name, String description, Float price, int quantity, String product_SKU, int user_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.product_SKU = product_SKU;
        this.user_id = user_id;
    }

    // Getters and setters
}*/

package model;

public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private Float price;
    private int quantity;
    private String product_SKU;

    public Product(int product_id, String product_name, String description, Float price, int quantity, String product_SKU) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.product_SKU = product_SKU;
    }

    // Getters (important for PropertyValueFactory)
    public int getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductSKU() {
        return product_SKU;
    }
}