/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.NhaXB;

/**
 *
 * @author Admin
 */
public class NXBController {

    public NXBController() {
    }
  public int nxbToTable(JTable tb) {
        List<NhaXB> dsNxb = NhaXB.layDanhSachNhaXB();
        Object[] obj = new Object[]{"Mã NXB","Tên NXB","Điện Thoại","Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(obj,0);
        for (NhaXB s : dsNxb) {
            Object[] item;
            item = new Object[6];
            item[0] = s.getMaNXB();
            item[1] = s.getTenNXB();
            item[2] = s.getDienThoai();
            item[3] = s.getDiaChi();
            model.addRow(item);
        }
        tb.setModel(model);
        return 1;
    }
    public String createMaNXB(){
        String mas = NhaXB.lastMaNXB();
        if(mas == null){
            return "nxb001";
        }
        Integer so = Integer.parseInt(mas.substring(3));
        so +=1;
        return "nxb"+ so.toString();
    }
    
}
