/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class Conn {
    public static Connection conn() throws SQLException{
        String url = "jdbc:sqlserver://localhost;databaseName=qlsach;user=sa;password=Abc123!@#;trustServerCertificate=true";
            return DriverManager.getConnection(url);
    }
    
    public static ResultSet getData(String query) throws SQLException{
        Statement stm = conn().createStatement();
        return stm.executeQuery(query);
        
    }
    public static int update(String query) throws SQLException{
        Statement stm = conn().createStatement();
        return stm.executeUpdate(query);
    }
    
    
}
