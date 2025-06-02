//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Com.Controller;

import Com.Model.Model_Product;
import Com.Model.Model_Product_Category;
import Com.Model.Model_ProductDetail;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductDetailDAO {
    DAO dao = new DAO();
    ArrayList<Model_ProductDetail> dsDetails = this.getListProductDetail();

    public boolean addProductDetail(Model_ProductDetail modelProductDetail) {
        String sql = "INSERT INTO ChiTietSP(MaCTSP, MaSP, NgayNhap, SoLuong, Gia, MoTa)VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, modelProductDetail.getProductDetailId());
            ps.setString(2, modelProductDetail.getProduct().getProductID());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(modelProductDetail.getEntryDate()));
            ps.setString(4, String.valueOf(modelProductDetail.getQuantity()));
            ps.setString(5, String.valueOf(modelProductDetail.getPrice()));
            ps.setString(6, modelProductDetail.getDescription());
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Model_ProductDetail> getListProductDetail() {
        ArrayList<Model_ProductDetail> dsDetails = new ArrayList();
        String sql = "SELECT ChiTietSP.MaCTSP, ChiTietSP.MaSP AS MSP, ChiTietSP.NgayNhap, ChiTietSP.SoLuong, ChiTietSP.Gia, ChiTietSP.MoTa, SanPham.MaSP, SanPham.TenSP, SanPham.Anh, SanPham.TrangThai AS SPTT, SanPham.MaloaiSP AS MLSP, LoaiSanPham.MaloaiSP, LoaiSanPham.TenLoai, LoaiSanPham.TrangThai  FROM ChiTietSP JOIN SanPham ON ChiTietSP.MaSP = SanPham.MaSP JOIN LoaiSanPham ON LoaiSanPham.MaLoaiSP = SanPham.MaLoaiSP";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Product_Category mpc = new Model_Product_Category(rs.getString("MaLoaiSP"), rs.getString("TenLoai"), rs.getString("TrangThai"));
                Model_Product mp = new Model_Product(rs.getString("MaSP"), rs.getString("TenSP"), rs.getBytes("Anh"), rs.getString("SPTT"), mpc);
                Model_ProductDetail s = new Model_ProductDetail();
                s.setProductDetailId(rs.getString("MaCTSP"));
                s.setProduct(mp);
                s.setEntryDate(rs.getTimestamp("NgayNhap").toLocalDateTime()); 
                s.setQuantity(rs.getInt("SoLuong"));
                s.setPrice(rs.getDouble("Gia"));
                s.setDescription(rs.getString("MoTa"));
                dsDetails.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsDetails;
    }

    public boolean updateProductDetail(Model_ProductDetail modelProductDetail) throws SQLException {
        Connection var10000 = this.dao.getConn();
        String var10001 = modelProductDetail.getProductDetailId();
        PreparedStatement ps = var10000.prepareStatement("UPDATE ChiTietSP SET MaCTSP=?, MaSP=?, NgayNhap=?, SoLuong=?, Gia=?, MoTa=? WHERE MaCTSP = '" + var10001 + "'");
        ps.setString(1, modelProductDetail.getProductDetailId());
        ps.setString(2, modelProductDetail.getProduct().getProductID());
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(modelProductDetail.getEntryDate()));
        ps.setString(4, String.valueOf(modelProductDetail.getQuantity()));
        ps.setString(5, String.valueOf(modelProductDetail.getPrice()));
        ps.setString(6, modelProductDetail.getDescription());
        return ps.executeUpdate() > 0;
    }

    public boolean updateProductDetailByMaCTSP(String maCTSP, int soLuong) throws SQLException {
        PreparedStatement ps = this.dao.getConn().prepareStatement("UPDATE ChiTietSP SET SoLuong= SoLuong + ? WHERE MaCTSP = '" + maCTSP + "'");
        ps.setInt(1, soLuong);
        return ps.executeUpdate() > 0;
    }

    public boolean deleteChiTietSPByMaSP(String maSP) throws SQLException {
        String selectSQL = "SELECT MaCTSP FROM ChiTietSP WHERE MaSP = ?";

        boolean var8;
        try (PreparedStatement selectStmt = this.dao.getConn().prepareStatement(selectSQL)) {
            selectStmt.setString(1, maSP);
            ResultSet rs = selectStmt.executeQuery();
            OrderDetailDAO cthdDAO = new OrderDetailDAO();

            while(rs.next()) {
                String maCTSP = rs.getString("MaCTSP");
                cthdDAO.deleteChiTietHoaDonByMaCTSP(maCTSP);
            }

            String deleteSQL = "DELETE FROM ChiTietSP WHERE MaSP = ?";

            try (PreparedStatement deleteStmt = this.dao.getConn().prepareStatement(deleteSQL)) {
                deleteStmt.setString(1, maSP);
                var8 = deleteStmt.executeUpdate() > 0;
            }
        }

        return var8;
    }

    public ArrayList<Model_ProductDetail> searchProduct(String name) {
        ArrayList<Model_ProductDetail> dsDetails = new ArrayList();
        String sql = "SELECT ChiTietSP.MaCTSP, ChiTietSP.MaSP AS MSP, ChiTietSP.NgayNhap, ChiTietSP.SoLuong, ChiTietSP.Gia, ChiTietSP.MoTa, SanPham.MaSP, SanPham.TenSP, SanPham.Anh, SanPham.TrangThai AS SPTT, SanPham.MaloaiSP AS MLSP, LoaiSanPham.MaloaiSP, LoaiSanPham.TenLoai, LoaiSanPham.TrangThai  FROM ChiTietSP JOIN SanPham ON ChiTietSP.MaSP = SanPham.MaSP JOIN LoaiSanPham ON LoaiSanPham.MaLoaiSP = SanPham.MaLoaiSP where (SanPham.TenSP like N'%" + name + "%') or (SanPham.TenSP like N'" + name + "%') or (SanPham.TenSP like N'%" + name + "')";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_Product_Category mpc = new Model_Product_Category(rs.getString("MaLoaiSP"), rs.getString("TenLoai"), rs.getString("TrangThai"));
                Model_Product mp = new Model_Product(rs.getString("MaSP"), rs.getString("TenSP"), rs.getBytes("Anh"), rs.getString("SPTT"), mpc);
                Model_ProductDetail s = new Model_ProductDetail();
                s.setProductDetailId(rs.getString("MaCTSP"));
                s.setProduct(mp);
                s.setEntryDate(rs.getTimestamp("NgayNhap").toLocalDateTime()); 
                s.setQuantity(rs.getInt("SoLuong"));
                s.setPrice(rs.getDouble("Gia"));
                s.setDescription(rs.getString("MoTa"));
                dsDetails.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsDetails;
    }
}
