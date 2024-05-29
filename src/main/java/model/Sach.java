/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Sach {

    private String maS;
    private String tenS;
    private String tacGia;
    private String namXB;
    private float dinhGia;
    private String maNXB;

    public Sach() {
    }

    public Sach(String maS, String tenS, String tacGia, String namXB, float dinhGia, String maNXB) {
        this.maS = maS;
        this.tenS = tenS;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.dinhGia = dinhGia;
        this.maNXB = maNXB;
    }

    // Getters and setters
    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNamXB() {
        return namXB;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public float getDinhGia() {
        return dinhGia;
    }

    public void setDinhGia(float dinhGia) {
        this.dinhGia = dinhGia;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public static List<Sach> layDanhSachSach() {
        List<Sach> danhSachSach = new ArrayList<>();

        try (ResultSet rs = Conn.getData("SELECT [MaS], [TenS], [TacGia], [NamXB], [DinhGia], [MaNXB] FROM [dbo].[Sach]")) {

            while (rs.next()) {
                String maS = rs.getString("MaS");
                String tenS = rs.getString("TenS");
                String tacGia = rs.getString("TacGia");
                String namXB = rs.getString("NamXB");
                float dinhGia = rs.getFloat("DinhGia");
                String maNXB = rs.getString("MaNXB");

                Sach sach = new Sach(maS, tenS, tacGia, namXB, dinhGia, maNXB);
                danhSachSach.add(sach);
            }
        } catch (SQLException e) {

        }

        return danhSachSach;
    }

    public int add() {//-2 la du lieu chu du 0 la khong them duoc -3 la loi cau lenh
        if (!kiemTraTatCaKhongRong()) {
            return -2;
        }
        String query = "insert into Sach values " + this.toString();
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
            String query = "UPDATE Sach SET tenS=?, TacGia=?, NamXB=?, dinhgia=?, MaNXB=? WHERE mas=?";
            Connection con = Conn.conn();
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, this.tenS);
            preparedStatement.setString(2, this.tacGia);
            preparedStatement.setString(3, this.namXB); // Assuming namXB is a String
            preparedStatement.setFloat(4, this.dinhGia); // Assuming dinhGia is a float
            preparedStatement.setString(5, this.maNXB); // Assuming maNXB is a String
            preparedStatement.setString(6, this.maS); // Assuming maS is a String
            int i = preparedStatement.executeUpdate();
            con.commit();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }

    }

    public int delete() {
        String query = "delete from Sach where maS = '" + this.maS + "'";
        try {
            return Conn.update(query);
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
// toString method for debugging

    @Override
    public String toString() {
        return "("
                + "'" + this.maS + "',"
                + "N'" + this.tenS + "',"
                + "N'" + this.tacGia + "',"
                + "'" + this.namXB + "',"
                + "" + this.dinhGia + ","
                + "'" + this.maNXB + "'"
                + ")";
    }
}
