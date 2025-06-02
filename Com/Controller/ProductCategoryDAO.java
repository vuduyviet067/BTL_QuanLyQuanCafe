

package Com.Controller;

import Com.Model.Model_Product_Category;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductCategoryDAO {
    DAO dao = new DAO();
    ArrayList<Model_Product_Category> dsCategorys = this.getListProductCategory();

    public boolean addProductCategory(Model_Product_Category modelProductCategory) {
        String sql = "INSERT INTO LoaiSanPham(MaLoaiSP, TenLoai, TrangThai)VALUES(?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, modelProductCategory.getCategoryID());
            ps.setString(2, modelProductCategory.getCategoryName());
            ps.setString(3, modelProductCategory.getStatus());
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (SQLException | HeadlessException ex) {
            ((Exception)ex).printStackTrace();
            return false;
        }
    }

    public ArrayList<Model_Product_Category> getListProductCategory() {
        ArrayList<Model_Product_Category> dsCategorys = new ArrayList();
        String sql = "SELECT * FROM LoaiSanPham";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Product_Category s = new Model_Product_Category();
                s.setCategoryID(rs.getString("MaLoaiSP"));
                s.setCategoryName(rs.getString("TenLoai"));
                s.setStatus(rs.getString("TrangThai"));
                dsCategorys.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsCategorys;
    }

    public boolean updateProductCategory(Model_Product_Category modelProductCategory) {
        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE LoaiSanPham SET MaLoaiSP=?, TenLoai=?, TrangThai=? WHERE MaLoaiSP = '" + modelProductCategory.getCategoryID() + "'");
            ps.setString(1, modelProductCategory.getCategoryID());
            ps.setString(2, modelProductCategory.getCategoryName());
            ps.setString(3, modelProductCategory.getStatus());
            ps.execute();
            JOptionPane.showMessageDialog((Component)null, "Updated");
            return true;
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "update not successful");
            return false;
        }
    }

    public boolean deleteLoaiSanPham(String maLoaiSP) throws SQLException {
        String sqlDeleteChiTietHoaDon = "DELETE FROM ChiTietHoaDon WHERE MaCTSP IN (SELECT MaCTSP FROM ChiTietSP WHERE MaSP IN (SELECT MaSP FROM SanPham WHERE MaLoaiSP = ?))";

        try (PreparedStatement stmt0 = this.dao.getConn().prepareStatement(sqlDeleteChiTietHoaDon)) {
            stmt0.setString(1, maLoaiSP);
            stmt0.executeUpdate();
        }

        String sqlDeleteChiTietSP = "DELETE FROM ChiTietSP WHERE MaSP IN (SELECT MaSP FROM SanPham WHERE MaLoaiSP = ?)";

        try (PreparedStatement stmt1 = this.dao.getConn().prepareStatement(sqlDeleteChiTietSP)) {
            stmt1.setString(1, maLoaiSP);
            stmt1.executeUpdate();
        }

        String sqlDeleteSanPham = "DELETE FROM SanPham WHERE MaLoaiSP = ?";

        try (PreparedStatement stmt2 = this.dao.getConn().prepareStatement(sqlDeleteSanPham)) {
            stmt2.setString(1, maLoaiSP);
            stmt2.executeUpdate();
        }

        String sqlDeleteLoaiSP = "DELETE FROM LoaiSanPham WHERE MaLoaiSP = ?";

        boolean var7;
        try (PreparedStatement stmt3 = this.dao.getConn().prepareStatement(sqlDeleteLoaiSP)) {
            stmt3.setString(1, maLoaiSP);
            var7 = stmt3.executeUpdate() > 0;
        }

        return var7;
    }
}
