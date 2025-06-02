//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Com.Controller;

import Com.Model.Model_Order;
import Com.Model.Model_Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {
    DAO dao = new DAO();
    ArrayList<Model_Order> dsOrder = this.getListOrder();

    public boolean addOrder(Model_Order modelOrder) {
        String sql = "INSERT INTO HoaDon(StaffID, MaBan, CustomerID, NgayTao, TongTien, TrangThai)VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, String.valueOf(modelOrder.getEmployeeID()));
            ps.setString(2, modelOrder.getModelTable().getTableID());
            ps.setString(3, String.valueOf(modelOrder.getCustomerID()));
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(modelOrder.getCreateDate())); 
            ps.setString(5, String.valueOf(modelOrder.getPrice()));
            ps.setString(6, modelOrder.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Model_Order> getListTableUse() {
        ArrayList<Model_Order> dsTableUses = new ArrayList();
        String sql = "SELECT HoaDon.*, TenBan,Ban.TrangThai AS TTB FROM HoaDon JOIN Ban ON HoaDon.MaBan = Ban.Maban WHERE HoaDon.TrangThai=N'Chưa Thanh Toán'";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Order s = new Model_Order();
                s.setOrderID(rs.getString("MaHD"));
                s.setEmployeeID(rs.getString("StaffID"));
                Model_Table m = new Model_Table(rs.getString("MaBan"), rs.getString("TenBan"), rs.getString("TTB"));
                s.setModelTable(m);
                s.setCustomerID(rs.getString("CustomerID"));
                s.setCreateDate(rs.getTimestamp("NgayTao").toLocalDateTime());
                s.setPrice(rs.getDouble("TongTien"));
                s.setStatus(rs.getString("TrangThai"));
                dsTableUses.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsTableUses;
    }

    public ArrayList<Model_Order> getListOrder() {
        ArrayList<Model_Order> dsOrders = new ArrayList();
        String sql = "SELECT HoaDon.*, TenBan,Ban.TrangThai AS TTB FROM HoaDon JOIN Ban ON HoaDon.MaBan = Ban.Maban";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Order s = new Model_Order();
                s.setOrderID(rs.getString("MaHD"));
                s.setEmployeeID(rs.getString("StaffID"));
                Model_Table m = new Model_Table(rs.getString("MaBan"), rs.getString("TenBan"), rs.getString("TTB"));
                s.setModelTable(m);
                s.setCustomerID(rs.getString("CustomerID"));
                s.setCreateDate(rs.getTimestamp("NgayTao").toLocalDateTime());
                s.setPrice(rs.getDouble("TongTien"));
                s.setStatus(rs.getString("TrangThai"));
                dsOrders.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsOrders;
    }

    public boolean updateOrder(Model_Order modelOrder) throws SQLException {
        Connection var10000 = this.dao.getConn();
        String var10001 = modelOrder.getOrderID();
        PreparedStatement ps = var10000.prepareStatement("UPDATE HoaDon SET StaffID=?, MaBan=?, CustomerID=?, NgayTao=?, TongTien=?, TrangThai=? WHERE MaHD = " + var10001);
        ps.setString(1, String.valueOf(modelOrder.getCustomerID()));
        ps.setString(2, modelOrder.getModelTable().getTableID());
        ps.setString(3, String.valueOf(modelOrder.getEmployeeID()));
        ps.setTimestamp(4, java.sql.Timestamp.valueOf(modelOrder.getCreateDate())); 
        ps.setString(5, String.valueOf(modelOrder.getPrice()));
        ps.setString(6, modelOrder.getStatus());
        return ps.executeUpdate() > 0;
    }

    public boolean deleteHoaDon(int maHD) throws SQLException {
        OrderDetailDAO cthdDAO = new OrderDetailDAO();
        cthdDAO.deleteChiTietHoaDonByMaHD(maHD);
        String sql = "DELETE FROM HoaDon WHERE MaHD = ?";

        boolean var5;
        try (PreparedStatement stmt = this.dao.getConn().prepareStatement(sql)) {
            stmt.setInt(1, maHD);
            var5 = stmt.executeUpdate() > 0;
        }

        return var5;
    }

    public ArrayList<Model_Order> searchOrder(int name) {
        ArrayList<Model_Order> dsOrders = new ArrayList();
        String sql = "SELECT HoaDon.*, TenBan,Ban.TrangThai AS TTB FROM HoaDon JOIN Ban ON HoaDon.MaBan = Ban.Maban where MaHD = " + name;

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Order s = new Model_Order();
                s.setOrderID(rs.getString("MaHD"));
                s.setEmployeeID(rs.getString("StaffID"));
                Model_Table m = new Model_Table(rs.getString("MaBan"), rs.getString("TenBan"), rs.getString("TTB"));
                s.setModelTable(m);
                s.setCustomerID(rs.getString("CustomerID"));
                s.setCreateDate(rs.getTimestamp("NgayTao").toLocalDateTime());
                s.setPrice(rs.getDouble("TongTien"));
                s.setStatus(rs.getString("TrangThai"));
                dsOrders.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsOrders;
    }

    public ArrayList<Model_Order> searchOrderDate(String nbd, String nkt, String tt) {
        ArrayList<Model_Order> dsOrders = new ArrayList();
        String sql = "SELECT HoaDon.*, TenBan,Ban.TrangThai AS TTB FROM HoaDon JOIN Ban ON HoaDon.MaBan = Ban.Maban where HoaDon.TrangThai = N'" + tt + "' AND (NgayTao BETWEEN '" + nbd + "' AND '" + nkt + "')";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Order s = new Model_Order();
                s.setOrderID(rs.getString("MaHD"));
                s.setEmployeeID(rs.getString("StaffID"));
                Model_Table m = new Model_Table(rs.getString("MaBan"), rs.getString("TenBan"), rs.getString("TTB"));
                s.setModelTable(m);
                s.setCustomerID(rs.getString("CustomerID"));
                s.setCreateDate(rs.getTimestamp("NgayTao").toLocalDateTime());
                s.setPrice(rs.getDouble("TongTien"));
                s.setStatus(rs.getString("TrangThai"));
                dsOrders.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsOrders;
    }
}
