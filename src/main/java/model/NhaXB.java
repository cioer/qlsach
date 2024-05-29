/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.lang.reflect.Field;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public int add() {//-2 la du lieu chu du 0 la khong them duoc -3 la loi cau lenh
        if (!kiemTraTatCaKhongRong()) {
            return -2;
        }
        String query = "insert into nhaXB values " + this.toString();
        try {
            int rs = Conn.update(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }
    }

    public int fix() {
        try {
            String query = "UPDATE nhaXB SET tenNXB=?, dienThoai=?, diaChi=? WHERE maNXB=?";
            Connection con = Conn.conn();
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, this.tenNXB);
            preparedStatement.setString(2, this.dienThoai);
            preparedStatement.setString(3, this.diaChi); 
            preparedStatement.setString(4, this.maNXB); 
            int i = preparedStatement.executeUpdate();
            con.commit();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }

    }

    public int delete() {
        String query1 = "delete from Sach where manxb = '" + this.maNXB+"'";
        String query2 = "delete from nhaXB where manxb = '" + this.maNXB + "'";
        try {
            Conn.update(query1);
            return Conn.update(query2);
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }
    }

    // Phương thức kiểm tra xem tất cả các thuộc tính có giá trị không rỗng không
    public boolean kiemTraTatCaKhongRong() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    return false; // Nếu có ít nhất một thuộc tính rỗng, trả về false
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true; // Nếu tất cả các thuộc tính không rỗng, trả về true
    }
    
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("('");
        str.append(maNXB);
        str.append("',N'");
        str.append(this.tenNXB);
        str.append("','");
        str.append(this.dienThoai);
        str.append("',N'");
        str.append(this.diaChi);
        str.append(")");
        return str.toString();
    }
    
}
