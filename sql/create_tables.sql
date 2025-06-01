-- SQL script init 
DROP DATABASE IF EXISTS CafeManagement;
CREATE DATABASE CafeManagement CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE CafeManagement;

-- Bảng StaffMaBan
CREATE TABLE Staff (
    StaffID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
    Gender VARCHAR(3),
    DateStaff DATE,
    PhoneNumber VARCHAR(10),
    AddressStaff VARCHAR(100) CHARACTER SET utf8mb4,
    UserName VARCHAR(20) NOT NULL UNIQUE,
    PasswordStaff VARCHAR(20) NOT NULL,
    Email VARCHAR(100),
    Position VARCHAR(50) NOT NULL,
    ImageStaff LONGBLOB
);

-- Bảng Customer
CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
    Gender VARCHAR(3),
    DateCustomer  DATE,
    PhoneNumber VARCHAR(10) UNIQUE,
    AddressCustomer VARCHAR(100) CHARACTER SET utf8mb4,
    Email VARCHAR(100)
);

-- Bảng LoaiSanPham
CREATE TABLE LoaiSanPham (
    MaLoaiSP VARCHAR(50) PRIMARY KEY,
    TenLoai VARCHAR(50),
    TrangThai VARCHAR(20)
);

-- Bảng SanPham
CREATE TABLE SanPham (
    MaSP VARCHAR(50) PRIMARY KEY,
    TenSP VARCHAR(50),
    Anh LONGBLOB,
    TrangThai VARCHAR(20),
    MaLoaiSP VARCHAR(50),
    FOREIGN KEY (MaLoaiSP) REFERENCES LoaiSanPham(MaLoaiSP)
);

-- Bảng ChiTietSP
CREATE TABLE ChiTietSP (
    MaCTSP VARCHAR(50) PRIMARY KEY,
    MaSP VARCHAR(50),
    NgayNhap DATE,
    SoLuong INT,
    Gia DECIMAL(18, 2),
    MoTa VARCHAR(200),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- Bảng Ban
CREATE TABLE Ban (
    MaBan VARCHAR(50) PRIMARY KEY,
    TenBan VARCHAR(50),
    TrangThai VARCHAR(20)
);

-- Bảng HoaDon
CREATE TABLE HoaDon (
    MaHD INT AUTO_INCREMENT PRIMARY KEY,
    StaffID INT,
    MaBan VARCHAR(50),
    CustomerID INT,
    NgayTao DATE,
    TongTien DECIMAL(18, 2),
    TrangThai VARCHAR(20),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    MaHD INT,
    MaCTSP VARCHAR(50),
    SoLuong INT,
    TongTien DECIMAL(18, 2),
    PRIMARY KEY (MaHD, MaCTSP),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaCTSP) REFERENCES ChiTietSP(MaCTSP)
);

-- Insert dữ liệu
INSERT INTO Staff (FullName, Gender, DateStaff, PhoneNumber, AddressStaff, UserName, PasswordStaff, Email, Position, ImageStaff) VALUES
('Nguyễn Huy Hoàng', 'Nam', '2005-01-01', '0123456789', 'Hà Nội', 'admin', '123456', 'huyhoang01@gmail.com', 'admin', NULL),
('Nguyễn Huy Khiên', 'Nam', '2005-03-03', '0213456789', 'Hà Nội', 'huykhien', '123456', 'huykhien03@gmail.com', 'nhan vien', NULL),
('Nguyễn Thị Thúy', 'Nữ', '2005-04-04', '0987654321', 'Kon Tum', 'thuynguyen1704', '123456', 'thuynguyen1704@gmail.com', 'nhan vien', NULL);

INSERT INTO Customer (FullName, Gender, DateCustomer , PhoneNumber, AddressCustomer, Email) VALUES
('Vũ Duy Việt', 'Nam', '2005-04-04', '0213456789', 'Hà Nội', 'vietvd99@gmail.com'),
('Lê Văn Sơn', 'Nam', '2005-11-11', '1234567890', 'Thanh Hóa', 'son36@gmail.com');

INSERT INTO LoaiSanPham VALUES
('SP01', 'Nước Uống', 'Còn'),
('SP02', 'Đồ Ăn', 'Còn');

INSERT INTO SanPham VALUES
('TS01', 'Trà Sữa Truyền Thống', NULL, 'Còn', 'SP01'),
('TS02', 'Trà Đào', NULL, 'Còn', 'SP01');

INSERT INTO ChiTietSP VALUES
('SPCT01', 'TS01', '2024-10-15', 30, 10000, NULL),
('SPCT02', 'TS02', '2024-10-15', 40, 15000, NULL);

INSERT INTO Ban VALUES
('01', 'Bàn 1', 'Còn Trống'),
('02', 'Bàn 2', 'Còn Trống'),
('03', 'Bàn 3', 'Còn Trống'),
('04', 'Bàn 4', 'Còn Trống'),
('05', 'Bàn 5', 'Còn Trống'),
('06', 'Bàn 6', 'Còn Trống'),
('07', 'Bàn 7', 'Còn Trống'),
('08', 'Bàn 8', 'Còn Trống'),
('09', 'Bàn 9', 'Còn Trống'),
('10', 'Bàn 10', 'Còn Trống');
