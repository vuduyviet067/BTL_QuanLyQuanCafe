

package Com.Model;

import java.time.LocalDateTime;

public class Model_Order {
    private String orderID;
    private String employeeID;
    private Model_Table modelTable;
    private String customerID;
    private LocalDateTime createDate;
    private double price;
    private String status;

    public Model_Order(String orderID, String employeeID, Model_Table modelTable, String customerID, LocalDateTime createDate, double price, String status) {
        this.orderID = orderID;
        this.employeeID = employeeID;
        this.modelTable = modelTable;
        this.customerID = customerID;
        this.createDate = createDate;
        this.price = price;
        this.status = status;
    }

    public Model_Order(String employeeID, Model_Table modelTable, String customerID, LocalDateTime createDate, double price, String status) {
        this.employeeID = employeeID;
        this.modelTable = modelTable;
        this.customerID = customerID;
        this.createDate = createDate;
        this.price = price;
        this.status = status;
    }

    public Model_Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Model_Table getModelTable() {
        return modelTable;
    }

    public void setModelTable(Model_Table modelTable) {
        this.modelTable = modelTable;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
