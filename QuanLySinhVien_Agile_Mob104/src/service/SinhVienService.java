/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author laptop
 */
public class SinhVienService {

    public List<Student> searchByID(String id, List<Student> list) {
        List<Student> listSearch = new ArrayList<>();
        for (Student student : list) {

            if (student.getMaSV().toLowerCase().contains(id.toLowerCase())) {
                listSearch.add(student);
            }
        }
        return listSearch;
    }

    public String addSinhVienToList(Student student, List<Student> list) {
        for (Student st : list) {
            if (st.getMaSV().equalsIgnoreCase(student.getMaSV())) {
                return "Mã sinh viên đã tồn tại";
            }
        }
        if (list.add(student)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    public String removeStudentInList(String id, List<Student> list) {
        int index = 0;
        try {
            for (Student st : list) {
                if (st.getMaSV().trim().equalsIgnoreCase(id.trim())) {
                    index = list.indexOf(st);
                    break;
                }
            }
            if (index < 0) {
                return "Không tìm thấy";
            }
            list.remove(index);
            return "Xóa thành công";
        } catch (Exception e) {
            return "Xóa thất bại";
        }
    }

    public void sort(List<Student> list) {
        list.sort((Student o1, Student o2) -> {
            return Double.compare(o2.getDiem(), o1.getDiem());
        });
    }

    public String updateStudent(String maSV, String tenSv, String ngSinh, boolean gioiTinh, String nganhHoc, double diem, Student student) {
        try {
            student.setMaSV(maSV);
            student.setTenSv(tenSv);
            student.setNgSinh(ngSinh);
            student.setGioiTinh(gioiTinh);
            student.setNganhHoc(nganhHoc);
            student.setDiem(diem);
            return "Sửa thành công";
        } catch (Exception e) {
            return "Sửa thành công";
        }
    }

    public List<Student> diemHon5(List<Student> list) {
        List<Student> listDiemHon5 = new ArrayList<>();

        for (Student student : list) {
            if (student.getDiem() >= 5) {
                listDiemHon5.add(student);
            }
        }
        return listDiemHon5;
    }
}
