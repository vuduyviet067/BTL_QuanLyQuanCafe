//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Com.Controller;

import Com.Model.Model_Product;
import Com.Model.Model_Product_Category;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductDAO {
    DAO dao = new DAO();
    ArrayList<Model_Product> dsProducts = this.getListProduct();

    public boolean addProduct(Model_Product modelProduct) {
        String sql = "INSERT INTO SanPham(MaSP, TenSP, Anh, TrangThai, MaLoaiSP)VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, modelProduct.getProductID());
            ps.setString(2, modelProduct.getProductName());
            if (modelProduct.getImage() == null) {
                ps.setNull(3, 2004);
            } else {
                ps.setBytes(3, modelProduct.getImage());
            }

            ps.setString(4, modelProduct.getStatus());
            ps.setString(5, modelProduct.getProductCategory().getCategoryID());
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (SQLException | HeadlessException var4) {
            return false;
        }
    }

    public ArrayList<Model_Product> getListProduct() {
        ArrayList<Model_Product> dsProduct = new ArrayList();
        String sql = "SELECT SanPham.MaSP, SanPham.TenSP, SanPham.Anh, SanPham.TrangThai AS SPTT, SanPham.MaloaiSP AS MLSP, LoaiSanPham.MaloaiSP,LoaiSanPham.TenLoai, LoaiSanPham.TrangThai  FROM SanPham JOIN LoaiSanPham ON LoaiSanPham.MaLoaiSP = SanPham.MaLoaiSP ";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Product_Category mpc = new Model_Product_Category(rs.getString("MaLoaiSP"), rs.getString("TenLoai"), rs.getString("TrangThai"));
                Model_Product s = new Model_Product();
                s.setProductID(rs.getString("MaSP"));
                s.setProductName(rs.getString("TenSp"));
                s.setImage(rs.getBytes("Anh"));
                s.setStatus(rs.getString("SPTT"));
                s.setProductCategory(mpc);
                dsProduct.add(s);
            }
        } catch (SQLException var7) {
        }

        return dsProduct;
    }

    public boolean updateProduct(Model_Product modelProduct) {
        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE SanPham SET MaSP=?, TenSP=?, Anh=?, TrangThai=?, MaLoaiSP=? WHERE MaSP = '" + modelProduct.getProductID() + "'");
            ps.setString(1, modelProduct.getProductID());
            ps.setString(2, modelProduct.getProductName());
            ps.setBytes(3, modelProduct.getImage());
            ps.setString(4, modelProduct.getStatus());
            ps.setString(5, modelProduct.getProductCategory().getCategoryID());
            ps.execute();
            JOptionPane.showMessageDialog((Component)null, "Updated");
            return true;
        } catch (SQLException | HeadlessException var3) {
            JOptionPane.showMessageDialog((Component)null, "update not successful");
            return false;
        }
    }

    public boolean deleteSanPham(String maSP) throws SQLException {
        ProductDetailDAO ctspDAO = new ProductDetailDAO();
        ctspDAO.deleteChiTietSPByMaSP(maSP);
        String sql = "DELETE FROM SanPham WHERE MaSP = ?";

        boolean var5;
        try (PreparedStatement stmt = this.dao.getConn().prepareStatement(sql)) {
            stmt.setString(1, maSP);
            var5 = stmt.executeUpdate() > 0;
        }

        return var5;
    }
}

