/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author homes
 */
public class MonAn {
   private String tenMon;
    private double gia;
    private String loai; // Ví dụ: "Cafe", "Nước Trái Cây", "Bánh ngọt"

    public MonAn(String tenMon, double gia, String loai) {
        this.tenMon = tenMon;
        this.gia = gia;
        this.loai = loai;
    }

    // --- Getters ---
    public String getTenMon() {
        return tenMon;
    }

    public double getGia() {
        return gia;
    }

    public String getLoai() {
        return loai;
    }

    // (Tùy chọn) Setters nếu bạn cần thay đổi thông tin món ăn sau khi tạo
    // public void setGia(double gia) { this.gia = gia; }
}
