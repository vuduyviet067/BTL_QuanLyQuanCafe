
package Com.Model;

import java.time.LocalDateTime;

public class Model_ProductDetail {
    private String productDetailId;
    private Model_Product product;
    private LocalDateTime entryDate;
    private int quantity;
    private double price;
    private String description;

    public Model_ProductDetail() {
    }

    public Model_ProductDetail(String productDetailId, Model_Product product, LocalDateTime entryDate, int quantity, double price, String description) {
        this.productDetailId = productDetailId;
        this.product = product;
        this.entryDate = entryDate;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Model_Product getProduct() {
        return product;
    }

    public void setProduct(Model_Product product) {
        this.product = product;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
