

package Com.Model;

public class Model_Product_Category {
    private String categoryID;
    private String categoryName;
    private String status;

    public Model_Product_Category() {
    }

    public Model_Product_Category(String categoryID, String categoryName, String status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.status = status;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

