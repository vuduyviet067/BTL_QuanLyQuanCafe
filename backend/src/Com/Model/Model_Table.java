
package Com.Model;

public class Model_Table {
    private String tableID;
    private String tableName;
    private String status;

    public Model_Table() {
    }

    public Model_Table(String tableID, String tableName, String status) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.status = status;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
