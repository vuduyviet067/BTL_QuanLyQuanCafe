
package Com.Model;

public class Model_OrderDetail {
    private String orderID;
    private Model_ProductDetail productDetail;
    private int quantity;
    private double price;

    public Model_OrderDetail(String orderID, Model_ProductDetail productDetail, int quantity, double price) {
        this.orderID = orderID;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Model_ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(Model_ProductDetail productDetail) {
        this.productDetail = productDetail;
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
    
    
}
