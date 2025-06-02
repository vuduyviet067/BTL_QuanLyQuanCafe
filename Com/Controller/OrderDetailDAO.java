

package Com.Controller;

import Com.Model.Model_OrderDetail;
import Com.Model.Model_Product;
import Com.Model.Model_Product_Category;
import Com.Model.Model_ProductDetail;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OrderDetailDAO {
    DAO dao = new DAO();
    ArrayList<Model_OrderDetail> dsOrderDetail;

    public boolean addOrderDetail(Model_OrderDetail modelOrderDetail) {
        String sql = "INSERT INTO ChiTietHoaDon(MaHD, MaCTSP, SoLuong, TongTien)VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ps.setString(1, String.valueOf(modelOrderDetail.getOrderID()));
            ps.setString(2, modelOrderDetail.getProductDetail().getProductDetailId());
            ps.setString(3, String.valueOf(modelOrderDetail.getQuantity()));
            ps.setString(4, String.valueOf(modelOrderDetail.getPrice()));
            JOptionPane.showMessageDialog((Component)null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Model_OrderDetail> getListOrderDetail(int maHD) {
        ArrayList<Model_OrderDetail> dsOrderDetails = new ArrayList();
        String sql = "SELECT MaHD, ChiTietHoaDon.MaCTSP, ChiTietHoaDon.SoLuong, TongTien, ChiTietSP.MaSP, NgayNhap, ChiTietSP.SoLuong, Gia, MoTa, SanPham.TenSP, Anh, SanPham.TrangThai AS TTSP, SanPham.MaLoaiSP, LoaiSanPham.TenLoai, LoaiSanPham.TrangThai FROM ChiTietHoaDon JOIN ChiTietSP ON ChiTietSP.MaCTSP = ChiTietHoaDon.MaCTSP JOIN SanPham ON ChiTietSP.MaSP = SanPham.MaSP JOIN LoaiSanPham ON LoaiSanPham.MaLoaiSP = SanPham.MaLoaiSP WHERE MaHD =" + maHD;

        try {
            PreparedStatement ps = this.dao.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Model_OrderDetail s = new Model_OrderDetail();
                s.setOrderID(rs.getString("MaHD"));
                Model_Product_Category mpc = new Model_Product_Category(rs.getString("MaLoaiSP"), rs.getString("TenLoai"), rs.getString("TrangThai"));
                Model_Product mp = new Model_Product(rs.getString("MaSP"), rs.getString("TenSP"), rs.getBytes("Anh"), rs.getString("TTSP"), mpc);
                Model_ProductDetail mpd = new Model_ProductDetail(rs.getString("MaCTSP"), mp, rs.getTimestamp("NgayNhap").toLocalDateTime(), rs.getInt("SoLuong"), rs.getDouble("Gia"), rs.getString("MoTa"));
                s.setProductDetail(mpd);
                s.setQuantity(rs.getInt("SoLuong"));
                s.setPrice(rs.getDouble("TongTien"));
                dsOrderDetails.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dsOrderDetails;
    }

    public boolean updateOrderDetail(Model_OrderDetail modelOrderDetail) throws SQLException {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong=?, TongTien=? WHERE MaHD=? AND MaCTSP=?";

        boolean var4;
        try (PreparedStatement ps = this.dao.getConn().prepareStatement(sql)) {
            ps.setInt(1, modelOrderDetail.getQuantity());
            ps.setString(2, String.valueOf(modelOrderDetail.getPrice()));
            ps.setString(3, modelOrderDetail.getOrderID());
            ps.setString(4, modelOrderDetail.getProductDetail().getProductDetailId());
            var4 = ps.executeUpdate() > 0;
        }

        return var4;
    }

    public boolean deleteChiTietHoaDon(int maHD, String MaCTSP) throws SQLException {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD = ? AND MaCTSP = ?";

        boolean var5;
        try (PreparedStatement stmt = this.dao.getConn().prepareStatement(sql)) {
            stmt.setInt(1, maHD);
            stmt.setString(2, MaCTSP);
            var5 = stmt.executeUpdate() > 0;
        }

        return var5;
    }

    public boolean deleteChiTietHoaDonByMaHD(int maHD) throws SQLException {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD = ?";

        boolean var4;
        try (PreparedStatement stmt = this.dao.getConn().prepareStatement(sql)) {
            stmt.setInt(1, maHD);
            var4 = stmt.executeUpdate() > 0;
        }

        return var4;
    }

    public boolean deleteChiTietHoaDonByMaCTSP(String maCTSP) throws SQLException {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaCTSP = ?";

        boolean var4;
        try (PreparedStatement stmt = this.dao.getConn().prepareStatement(sql)) {
            stmt.setString(1, maCTSP);
            var4 = stmt.executeUpdate() > 0;
        }

        return var4;
    }
}
