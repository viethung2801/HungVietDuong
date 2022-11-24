/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author laptop
 */
public class Student implements Serializable{

    private String maSV;
    private String tenSv;
    private String ngSinh;
    private boolean gioiTinh;
    private String nganhHoc;
    private double diem;

    public Student() {
    }

    public Student(String maSV, String tenSv, String ngSinh, boolean gioiTinh, String nganhHoc, double diem) {
        this.maSV = maSV;
        this.tenSv = tenSv;
        this.ngSinh = ngSinh;
        this.gioiTinh = gioiTinh;
        this.nganhHoc = nganhHoc;
        this.diem = diem;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getNgSinh() {
        return ngSinh;
    }

    public void setNgSinh(String ngSinh) {
        this.ngSinh = ngSinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String xepLoai(double diem) {
        if (diem < 5) {
            return "Yếu";
        } else if (diem <= 6.5) {
            return "Trung bình";
        } else if (diem < 8) {
            return "Khá";
        } else {
            return "Giỏi";
        }
    }

    public Object[] toDataRow() {
        return new Object[]{maSV, tenSv, ngSinh, gioiTinh == true ? "Nam" : "Nữ", nganhHoc, diem, xepLoai(diem)};
    }
}
