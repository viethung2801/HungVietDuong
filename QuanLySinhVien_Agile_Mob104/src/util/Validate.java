/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author laptop
 */
public class Validate {
    public String checkMa(String s){
        s = s.trim();
        if (s.equals("")) {
            return "Mã không được trống";
        }
        if (!s.matches("[a-zA-Z0-9]+")) {
            return "Mã phải là chữ và số không dấu";
        }
        return "";
    }
    public String checkTen(String s){
        s = s.trim();
        if (s.equals("")) {
            return "Tên không được trống";
        }
        if (!s.matches("[a-zA-Z ]+")) {
            return "Tên phải là chữ không dấu";
        }
        return "";
    }
    public String checkNgSinh(String s){
        s = s.trim();
        if (s.equals("")) {
            return "Ngày sinh không được trống";
        }
        if (!s.matches("^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$")) {
            return "Ngày sinh không hợp lệ hoặc có dạng dd-mm-yyyy";
        }
        return "";
    }
    public String checkNganhHoc(String s){
        s = s.trim();
        if (s.equals("")) {
            return "Nganh học không được trống";
        }
        return "";
    }
    public String checkDiem(String s){
        s = s.trim();
        try {
            double diem = Double.parseDouble(s);
            if (diem < 0 || diem > 10) {
                return "Điểm phải từ 0 - 10";
            }
            return "";
        } catch (Exception e) {
            return "Điểm không hợp lệ";
        }
    }
}
