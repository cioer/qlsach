/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class NhaXB {
    private String maNXB;
    private String tenNXB;
    private String dienThoai;
    private String diaChi;

    public NhaXB(String maNXB, String tenNXB, String dienThoai, String diaChi) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }

    public NhaXB() {
    }

    public String getMaNXB() {
        return maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    // Hàm để lấy danh sách các nhà xuất bản từ cơ sở dữ liệu
    public static List<NhaXB> layDanhSachNhaXB() {
        List<NhaXB> danhSachNhaXB = new ArrayList<>();
       

        try (ResultSet rs =Conn.getData("SELECT [MaNXB], [TenNXB], [DienThoai], [DiaChi] FROM [dbo].[NhaXB]")) {

            while (rs.next()) {
                String maNXB = rs.getString("MaNXB");
                String tenNXB = rs.getString("TenNXB");
                String dienThoai = rs.getString("DienThoai");
                String diaChi = rs.getString("DiaChi");
                NhaXB nhaXB = new NhaXB(maNXB, tenNXB, dienThoai, diaChi);
                danhSachNhaXB.add(nhaXB);
            }
        } catch (SQLException e) {
            
        }

        return danhSachNhaXB;
    }

    
    
}
