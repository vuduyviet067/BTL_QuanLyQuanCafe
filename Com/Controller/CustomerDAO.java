
package Com.Controller;

import Com.Model.Model_Customer;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CustomerDAO {
    DAO dao = new DAO();
    ArrayList<Model_Customer> dsCustomer = this.getListCustomer();

    public boolean addCustomer(Model_Customer modelCustomer) {
        String sql = "INSERT INTO Customer(FullName, Gender, DateCustomer, PhoneNumber, AddressCustomer, Email)VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            
            ps.setString(1, modelCustomer.getName());
            ps.setString(2, modelCustomer.getGender());
            ps.setString(3, modelCustomer.getBirthDate().toString());
            ps.setString(4, modelCustomer.getPhone());
            ps.setString(5, modelCustomer.getAddress());
            ps.setString(6, modelCustomer.getEmail());
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Model_Customer> getListCustomer() {
        ArrayList<Model_Customer> dsUser = new ArrayList();
        String sql = "SELECT * FROM Customer";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Customer s = new Model_Customer();
                s.setId(rs.getString("CustomerID"));
                s.setName(rs.getString("FullName"));
                s.setGender(rs.getString("Gender"));
                s.setBirthDate(rs.getDate("DateCustomer").toLocalDate());
                s.setPhone(rs.getString("PhoneNumber"));
                s.setAddress(rs.getString("AddressCustomer"));
                s.setEmail(rs.getString("Email"));
                dsUser.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsUser;
    }

    public boolean updateCustomer(Model_Customer modelCustomer) {
        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE Customer SET FullName=?, Gender=?, DateCustomer=?, PhoneNumber=?, AddressCustomer=?, Email=? WHERE CustomerID = " + modelCustomer.getId());
            ps.setString(1, modelCustomer.getName());
            ps.setString(2, modelCustomer.getGender());
            ps.setDate(3, java.sql.Date.valueOf(modelCustomer.getBirthDate()));
            ps.setString(4, modelCustomer.getPhone());
            ps.setString(5, modelCustomer.getAddress());
            ps.setString(6, modelCustomer.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog((Component)null, "Updated");
            return true;
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "update not successful");
            return false;
        }
    }

    public boolean deleteCustomer(String customerID) throws SQLException {
        String sqlDeleteChiTietHoaDon = "DELETE FROM ChiTietHoaDon WHERE MaHD IN (SELECT MaHD FROM HoaDon WHERE CustomerID = ?)";

        try (PreparedStatement stmt0 = this.dao.getConn().prepareStatement(sqlDeleteChiTietHoaDon)) {
            stmt0.setString(1, customerID);
            stmt0.executeUpdate();
        }

        String sqlDeleteHoaDon = "DELETE FROM HoaDon WHERE CustomerID = ?";

        try (PreparedStatement stmt1 = this.dao.getConn().prepareStatement(sqlDeleteHoaDon)) {
            stmt1.setString(1, customerID);
            stmt1.executeUpdate();
        }

        String sqlDeleteCustomer = "DELETE FROM Customer WHERE CustomerID = ?";

        boolean var6;
        try (PreparedStatement stmt2 = this.dao.getConn().prepareStatement(sqlDeleteCustomer)) {
            stmt2.setString(1, customerID);
            var6 = stmt2.executeUpdate() > 0;
        }

        return var6;
    }
}
