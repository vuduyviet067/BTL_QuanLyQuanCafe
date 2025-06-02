
package Com.Controller;

import Com.Model.Model_User;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO {
    DAO dao = new DAO();
    ArrayList<Model_User> dsUser = this.getListUser();

    public boolean addUser(Model_User modelUser) {
        String sql = "INSERT INTO Staff(FullName, Gender, DateStaff, PhoneNumber, AddressStaff, UserName, PassWordStaff, Email, Position, ImageStaff)VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, modelUser.getName());
            ps.setString(2, modelUser.getGender());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(modelUser.getBirthDate().atStartOfDay()));
            ps.setString(4, modelUser.getPhone());
            ps.setString(5, modelUser.getAddress());
            ps.setString(6, modelUser.getUsername());
            ps.setString(7, modelUser.getPassword());
            ps.setString(8, modelUser.getEmail());
            ps.setString(9, modelUser.getPosition());
            if (modelUser.getImage() == null) {
                ps.setNull(10, 2004);
            } else {
                ps.setBytes(10, modelUser.getImage());
            }

            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (SQLException | HeadlessException var4) {
            return false;
        }
    }

    public ArrayList<Model_User> getListUser() {
        ArrayList<Model_User> dsUser = new ArrayList();
        String sql = "SELECT * FROM Staff";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_User s = new Model_User();
                s.setId(rs.getString("StaffID"));
                s.setName(rs.getString("FullName"));
                s.setGender(rs.getString("Gender"));
                s.setBirthDate(rs.getDate("DateStaff").toLocalDate());
                s.setPhone(rs.getString("PhoneNumber"));
                s.setAddress(rs.getString("AddressStaff"));
                s.setUsername(rs.getString("UserName"));
                s.setPassword(rs.getString("PasswordStaff"));
                s.setEmail(rs.getString("Email"));
                s.setPosition(rs.getString("Position"));
                s.setImage(rs.getBytes("ImageStaff"));
                dsUser.add(s);
            }
        } catch (SQLException var6) {
        }

        return dsUser;
    }

    public boolean updateUser(Model_User modelUser) {
        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE Staff SET FullName=?, Gender=?, DateStaff=?, PhoneNumber=?, AddressStaff=?, UserName=?, PassWordStaff=?, Email=?, Position=?, ImageStaff=? WHERE StaffID = " + modelUser.getId());
            ps.setString(1, modelUser.getName());
            ps.setString(2, modelUser.getGender());
            ps.setDate(3, java.sql.Date.valueOf(modelUser.getBirthDate()));
            ps.setString(4, modelUser.getPhone());
            ps.setString(5, modelUser.getAddress());
            ps.setString(6, modelUser.getUsername());
            ps.setString(7, modelUser.getPassword());
            ps.setString(8, modelUser.getEmail());
            ps.setString(9, modelUser.getPosition());
            ps.setBytes(10, modelUser.getImage());
            ps.execute();
            JOptionPane.showMessageDialog((Component)null, "Updated");
            return true;
        } catch (SQLException | HeadlessException var3) {
            JOptionPane.showMessageDialog((Component)null, "update not successful");
            return false;
        }
    }

    public boolean deleteStaff(String staffID) throws SQLException {
        String sqlDeleteChiTietHoaDon = "DELETE FROM ChiTietHoaDon WHERE MaHD IN (SELECT MaHD FROM HoaDon WHERE StaffID = ?)";

        try (PreparedStatement stmt1 = this.dao.getConn().prepareStatement(sqlDeleteChiTietHoaDon)) {
            stmt1.setString(1, staffID);
            stmt1.executeUpdate();
        }

        String sqlDeleteHoaDon = "DELETE FROM HoaDon WHERE StaffID = ?";

        try (PreparedStatement stmt2 = this.dao.getConn().prepareStatement(sqlDeleteHoaDon)) {
            stmt2.setString(1, staffID);
            stmt2.executeUpdate();
        }

        String sqlDeleteStaff = "DELETE FROM Staff WHERE StaffID = ?";

        boolean var6;
        try (PreparedStatement stmt3 = this.dao.getConn().prepareStatement(sqlDeleteStaff)) {
            stmt3.setString(1, staffID);
            var6 = stmt3.executeUpdate() > 0;
        }

        return var6;
    }

    public ArrayList<Model_User> searchUser(String name) {
        ArrayList<Model_User> dsUser = new ArrayList();
        String sql = "SELECT * FROM Staff where (FullName like N'%" + name + "%') or (FullName like N'" + name + "%') or (FullName like N'%" + name + "')";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_User s = new Model_User();
                s.setId(rs.getString("StaffID"));
                s.setName(rs.getString("FullName"));
                s.setGender(rs.getString("Gender"));
                s.setBirthDate(rs.getDate("DateStaff").toLocalDate());
                
                s.setPhone(rs.getString("PhoneNumber"));
                s.setAddress(rs.getString("AddressStaff"));
                s.setUsername(rs.getString("UserName"));
                s.setPassword(rs.getString("PasswordStaff"));
                s.setEmail(rs.getString("Email"));
                s.setPosition(rs.getString("Position"));
                s.setImage(rs.getBytes("ImageStaff"));
                dsUser.add(s);
            }
        } catch (SQLException var7) {
        }

        return dsUser;
    }
}
