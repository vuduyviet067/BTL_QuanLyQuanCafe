
package Com.Model;

public class Model_Product {
    private String productID;
    private String productName;
    private byte[] image;
    private String status;
    private Model_Product_Category productCategory;

    public Model_Product() {
    }
    
    
    public Model_Product(String productID, String productName, byte[] image, String status, Model_Product_Category productCategory) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.status = status;
        this.productCategory = productCategory;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Model_Product_Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Model_Product_Category productCategory) {
        this.productCategory = productCategory;
    }
    
    
}
