//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Com.Controller;

import Com.Model.Model_Table;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TableDAO {
    DAO dao = new DAO();
    ArrayList<Model_Table> dsTable = this.getListTable();

    public boolean addTable(Model_Table modelTable) {
        String sql = "INSERT INTO Ban(MaBan, TenBan, TrangThai)VALUES(?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, modelTable.getTableID());
            ps.setString(2, modelTable.getTableName());
            ps.setString(3, modelTable.getStatus());
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (SQLException | HeadlessException var4) {
            return false;
        }
    }

    public ArrayList<Model_Table> getListTable() {
        ArrayList<Model_Table> dsTables = new ArrayList();
        String sql = "SELECT * FROM Ban";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Table s = new Model_Table();
                s.setTableID(rs.getString("MaBan"));
                s.setTableName(rs.getString("TenBan"));
                s.setStatus(rs.getString("TrangThai"));
                dsTables.add(s);
            }
        } catch (SQLException var6) {
        }

        return dsTables;
    }

    public boolean updateTable(Model_Table modelTable) {
        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE Ban SET MaBan=?, TenBan=?, TrangThai=? WHERE MaBan = '" + modelTable.getTableID() + "'");
            ps.setString(1, modelTable.getTableID());
            ps.setString(2, modelTable.getTableName());
            ps.setString(3, modelTable.getStatus());
            ps.execute();
            return true;
        } catch (SQLException var3) {
            JOptionPane.showMessageDialog((Component)null, "update not successful");
            return false;
        }
    }

    public boolean deleteTable(String maBan) throws SQLException {
        String sqlDeleteChiTietHoaDon = "DELETE FROM ChiTietHoaDon WHERE MaHD IN (SELECT MaHD FROM HoaDon WHERE MaBan = ?)";

        try (PreparedStatement stmt0 = this.dao.getConn().prepareStatement(sqlDeleteChiTietHoaDon)) {
            stmt0.setString(1, maBan);
            stmt0.executeUpdate();
        }

        String sqlDeleteHoaDon = "DELETE FROM HoaDon WHERE MaBan = ?";

        try (PreparedStatement stmt1 = this.dao.getConn().prepareStatement(sqlDeleteHoaDon)) {
            stmt1.setString(1, maBan);
            stmt1.executeUpdate();
        }

        String sqlDeleteBan = "DELETE FROM Ban WHERE MaBan = ?";

        boolean var6;
        try (PreparedStatement stmt2 = this.dao.getConn().prepareStatement(sqlDeleteBan)) {
            stmt2.setString(1, maBan);
            var6 = stmt2.executeUpdate() > 0;
        }

        return var6;
    }
}
