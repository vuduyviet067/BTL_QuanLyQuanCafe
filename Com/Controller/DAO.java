//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    private Connection conn;

    public Connection getConn() {
        return this.conn;
    }

    public DAO() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CafeManagement?useSSL=false&serverTimezone=UTC","root","123456a" );
           
        } catch (Exception ex) {
            System.err.println("Lỗi khi thêm khách hàng:");
            ex.printStackTrace();
        }

    }
}
