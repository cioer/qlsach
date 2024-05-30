/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.NhaXB;
import model.Sach;

/**
 *
 * @author Admin
 */
public class SachController {

    public SachController() {
    }
    
    public int bookToTable(JTable tb) {
        List<Sach> dsSach = Sach.layDanhSachSach();
        Object[] obj = new Object[]{"Mã Sách","Tên Sách","Tác Giả","Năm XB","Đơn giá","Mã XB"};
        DefaultTableModel model = new DefaultTableModel(obj,0);
        for (Sach s : dsSach) {
            Object[] item;
            item = new Object[6];
            item[0] = s.getMaS();
            item[1] = s.getTenS();
            item[2] = s.getTacGia();
            item[3] = s.getNamXB();
            item[4] = s.getDinhGia();
            item[5] = s.getMaNXB();
            model.addRow(item);
        }
        tb.setModel(model);
        return 1;
    }

    public String createMaSach(){
        String mas = Sach.lastMaS();
        if(mas == null){
            return "b1";
        }
        Integer so = Integer.parseInt(mas.substring(1));
        so +=1;
        return "b"+ so.toString();
    }
    
    public void hienListNXB(JComboBox cmb){
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();//select manxb from nhaxb
        List<NhaXB> layDanhSachNhaXB = NhaXB.layDanhSachNhaXB();
        for(NhaXB nxb : layDanhSachNhaXB){
            model.addElement(nxb.getMaNXB());
        }
        cmb.setModel(model);
    }
}
