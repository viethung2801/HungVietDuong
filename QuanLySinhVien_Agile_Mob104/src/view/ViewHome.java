/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Student;
import service.SinhVienService;
import util.Validate;

/**
 *
 * @author laptop
 */
public class ViewHome extends javax.swing.JFrame {

    private List<Student> listStudents = new ArrayList<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel<String> boxModel;
    private SinhVienService svSevice = new SinhVienService();
    private Validate validate = new Validate();
    private String path = "student.txt";

    //private Validate validate = new Validate();
    /**
     * Creates new form ViewHome
     */
    public ViewHome() {
        
        initComponents();
        
        this.setTitle("Quản Lý Sinh Viên - Trang Chủ");
        jDialog1.setSize(1031,439);
        setHeader();
        //setCBX();
        //fakeData();
        docFile();
        showData(listStudents);
        fill(0);
    }

    private void setHeader() {
        String[] headers = {"Mã Sinh Viên", "Họ Tên", "Ngày Sinh", "Giới Tính", "Ngành Học", "Điểm Tổng", "Xếp Loại"};
        dtm.setColumnIdentifiers(headers);
        tblListSinhVien.setModel(dtm);
        
    }

//    private void setCBX() {
//        Object[] ranks = {"Yếu", "Trung Bình", "Khá", "Giỏi"};
//        boxModel = new DefaultComboBoxModel(ranks);
//        cbxRank.setModel(boxModel);
//    }

    private void showData(List<Student> list) {
        dtm.setRowCount(0);
        if (list.size() <= 0) {
            JOptionPane.showMessageDialog(this, list);
        } else {
            for (Student s : list) {
                dtm.addRow(s.toDataRow());
            }
        }
    }

    private void showData(Student student) {
        dtm.setRowCount(0);
        dtm.addRow(student.toDataRow());
    }

    public void fill(int index) {
        //int index = tblDongVat.getSelectedRow();
        //txtid1.setText((String)(model.getValueAt(index,0))); 
        String gioiTinh;
        txtMa.setText((String) dtm.getValueAt(index, 0));
        txtHoTen.setText((String) dtm.getValueAt(index, 1));
        txtNgSinh.setText(String.valueOf(dtm.getValueAt(index, 2)));
        gioiTinh = (String) dtm.getValueAt(index, 3);
        //System.out.println(gioiTinh);
        if (gioiTinh.trim().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNganh.setText(dtm.getValueAt(index, 4).toString());
        txtDiemTong.setText(dtm.getValueAt(index, 5).toString());
        //cbxRank.setSelectedItem(dtm.getValueAt(index, 6));

    }

    private Student newStudent(String maSV, String tenSv, String ngSinh, boolean gioiTinh, String nganhHoc, double diem) {
        if (validateAll()) {
            return new Student(maSV, tenSv, ngSinh, gioiTinh, nganhHoc, diem);
        }
        return null;
    }

//    private Student studentUpdate() {
//        
//        return new Student(txtMa1.getText().trim(), txtHoTen1.getText().trim(),
//                txtNgSinh1.getText().trim(), (rdoNam1.isSelected()),
//                txtNganh1.getText().trim(), Double.parseDouble(txtDiemTong1.getText().trim()));
//    }
    private int getRow() {
        return tblListSinhVien.getSelectedRow();
    }

    private void fakeData() {
        listStudents.add(new Student("SV02", "Duong Viet Hung", "30-01-2003", true, "Web", 8.5));
        listStudents.add(new Student("SV03", "Nguyen Thi B", "30-01-2003", false, "UDPM", 7));
        listStudents.add(new Student("SV01", "Duong Viet Hung", "30-01-2003", true, "UDPM", 8.8));
        listStudents.add(new Student("SV04", "Nguyen Van A", "30-01-2003", true, "Web", 4));
    }
    public void docFile() {
        try {
            File file = new File(path);

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                listStudents.add((Student) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch (Exception e) {
        }
    }

    public boolean validateAll() {
        if (validate.checkMa(getMa()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkMa(getMa()));
            return false;
        }
        if (validate.checkTen(getHoTen()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkTen(getHoTen()));
            return false;
        }
        if (validate.checkNgSinh(getNgSinh()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkNgSinh(getNgSinh()));
            return false;
        }
        if (validate.checkNganhHoc(getNganh()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkNganhHoc(getNganh()));
            return false;
        }
        if (validate.checkDiem(String.valueOf(getDiemTong())) != "") {
            JOptionPane.showMessageDialog(null, validate.checkDiem(String.valueOf(getDiemTong())));
            return false;
        }
        return true;
    }

    public boolean validateJdialog() {
        if (validate.checkMa(getMa1()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkMa(getMa1()));
            return false;
        }
        if (validate.checkTen(getHoTen1()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkTen(getHoTen1()));
            return false;
        }
        if (validate.checkNgSinh(getNgSinh1()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkNgSinh(getNgSinh1()));
            return false;
        }
        if (validate.checkNganhHoc(getNganh1()) != "") {
            JOptionPane.showMessageDialog(null, validate.checkNganhHoc(getNganh1()));
            return false;
        }
        if (validate.checkDiem(String.valueOf(getDiemTong1())) != "") {
            JOptionPane.showMessageDialog(null, validate.checkDiem(String.valueOf(getDiemTong1())));
            return false;
        }
        return true;
    }

//    public String getRank() {
//        return cbxRank.getSelectedItem().toString();
//    }

    public double getDiemTong() {
        try {
            return Double.parseDouble(txtDiemTong.getText().trim());
        } catch (Exception e) {
            return -1;
        }
        
    }

    public double getDiemTong1() {
        try {
            return Double.parseDouble(txtDiemTong1.getText().trim());
        } catch (Exception e) {
            return -1;
        }
        
    }

    public String getHoTen() {
        return txtHoTen.getText();
    }

    public String getHoTen1() {
        return txtHoTen1.getText();
    }

    public String getMa() {
        return txtMa.getText();
    }

    public String getMa1() {
        return txtMa1.getText();
    }

    public String getMaSearch() {
        return txtMaSearch.getText();
    }

    public String getNgSinh() {
        return txtNgSinh.getText();
    }

    public String getNgSinh1() {
        return txtNgSinh1.getText();
    }

    public String getNganh() {
        return txtNganh.getText();
    }

    public String getNganh1() {
        return txtNganh1.getText();
    }

    public boolean getGioiTinh() {
        return rdoNam.isSelected();
    }

    public boolean getGioiTinh1() {
        return rdoNam1.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        txtMa1 = new javax.swing.JTextField();
        txtHoTen1 = new javax.swing.JTextField();
        txtNganh1 = new javax.swing.JTextField();
        txtDiemTong1 = new javax.swing.JTextField();
        txtNgSinh1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        rdoNam1 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rdoNu1 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtMaSearch = new javax.swing.JTextField();
        bntSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        chkDiemHon5 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtNganh = new javax.swing.JTextField();
        txtDiemTong = new javax.swing.JTextField();
        txtNgSinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListSinhVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnExt = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel5.setBackground(new java.awt.Color(255, 204, 51));
        jPanel5.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Giới tính:");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNam1);
        rdoNam1.setText("Nam");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mã SV : ");

        buttonGroup2.add(rdoNu1);
        rdoNu1.setText("Nữ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Ngành Học : ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Ngày Sinh :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Họ Tên :");

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Điểm Tổng Kết :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa1)
                            .addComponent(txtHoTen1)
                            .addComponent(txtNgSinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNganh1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(btnCancel)
                                    .addGap(60, 60, 60)
                                    .addComponent(btnOk))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(rdoNam1)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoNu1)
                                    .addGap(122, 122, 122)))
                            .addComponent(txtDiemTong1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNgSinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rdoNu1)
                    .addComponent(rdoNam1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNganh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtDiemTong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 249, 205));

        jPanel4.setBackground(new java.awt.Color(255, 204, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mã SV : ");

        txtMaSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSearchActionPerformed(evt);
            }
        });

        bntSearch.setBackground(new java.awt.Color(255, 255, 255));
        bntSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/search.png"))); // NOI18N
        bntSearch.setText("Tìm Kiếm ");
        bntSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Trạng Thái : ");

        chkDiemHon5.setText("Điểm >5");
        chkDiemHon5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiemHon5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(chkDiemHon5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntSearch)
                        .addGap(45, 45, 45))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkDiemHon5)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã SV : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Ngành Học : ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Họ Tên :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Điểm Tổng Kết :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Ngày Sinh :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                            .addComponent(txtHoTen)
                            .addComponent(txtNgSinh)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(59, 59, 59)
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiemTong))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(40, 40, 40)
                        .addComponent(txtNganh)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtDiemTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblListSinhVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblListSinhVien.setForeground(new java.awt.Color(255, 126, 33));
        tblListSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Họ Tên", "Ngày Sinh", "Giới Tính", "Ngành Học", "Điểm Tổng Kết ", "Xếp Loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblListSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListSinhVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListSinhVien);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAdd.setBackground(new java.awt.Color(151, 71, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/plus.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(151, 71, 255));
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/trash-bin.png"))); // NOI18N
        btnRemove.setText("Xóa");
        btnRemove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnSort.setBackground(new java.awt.Color(151, 71, 255));
        btnSort.setForeground(new java.awt.Color(255, 255, 255));
        btnSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/sort-down.png"))); // NOI18N
        btnSort.setText("Sắp xếp");
        btnSort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(151, 71, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/office-material.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnExt.setBackground(new java.awt.Color(151, 71, 255));
        btnExt.setForeground(new java.awt.Color(255, 255, 255));
        btnExt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/image/cancel.png"))); // NOI18N
        btnExt.setText("Thoát");
        btnExt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSort, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(72, 178, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Phần Mềm Quản Lý Sinh Viên ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListSinhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListSinhVienMouseClicked
        // TODO add your handling code here:
        if (getRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sinh viên ở bảng");
        } else {
            fill(getRow());
        }
    }//GEN-LAST:event_tblListSinhVienMouseClicked

    private void bntSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSearchActionPerformed
        String id = txtMaSearch.getText().trim();
        if (id.equals("")) {
            showData(listStudents);
        } else {
            List listTam = svSevice.searchByID(id, listStudents);
            if (listTam.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
                return;
            }
            showData(svSevice.searchByID(id, listStudents));
        }
    }//GEN-LAST:event_bntSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Student st = newStudent(getMa(), getHoTen(), getNgSinh(), getGioiTinh(), getNganh(), getDiemTong());
        if (st != null) {
            JOptionPane.showMessageDialog(this, svSevice.addSinhVienToList(st, listStudents));
            showData(listStudents);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (getRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên muốn xóa trong bảng");
        } else {
            JOptionPane.showMessageDialog(this, svSevice.removeStudentInList(dtm.getValueAt(getRow(), 0).toString(), listStudents));
            showData(listStudents);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int index = getRow();
        if (getRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên muốn xóa trong bảng");
        } else {
            jDialog1.setVisible(true);
            jDialog1.setSize(500, 400);
            String gioiTinh;
            txtMa1.setText((String) dtm.getValueAt(index, 0));
            txtHoTen1.setText((String) dtm.getValueAt(index, 1));
            txtNgSinh1.setText(String.valueOf(dtm.getValueAt(index, 2)));
            gioiTinh = (String) dtm.getValueAt(index, 3);
            //System.out.println(gioiTinh);
            if (gioiTinh.trim().equalsIgnoreCase("Nam")) {
                rdoNam1.setSelected(true);
            } else {
                rdoNu1.setSelected(true);
            }
            txtNganh1.setText(dtm.getValueAt(index, 4).toString());
            txtDiemTong1.setText(dtm.getValueAt(index, 5).toString());
            //cbxRank.setSelectedItem(dtm.getValueAt(index, 6));
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        svSevice.sort(listStudents);
        showData(listStudents);
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtActionPerformed
        ghiFile();
        System.exit(0);
    }//GEN-LAST:event_btnExtActionPerformed

    private void chkDiemHon5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiemHon5ActionPerformed
        if (chkDiemHon5.isSelected() == true) {
            showData(svSevice.diemHon5(listStudents));
        } else
            showData(listStudents);
    }//GEN-LAST:event_chkDiemHon5ActionPerformed

    private void txtMaSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSearchActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        //System.out.println(dtm.getValueAt(getRow(),0).toString());
        boolean check = false;
        if (!validateJdialog()) {
            return;
        }
        for (int i = 0; i < tblListSinhVien.getRowCount(); i++) {
            if (i == getRow()) {
                continue;
            }
            if (dtm.getValueAt(i, 0).toString().equalsIgnoreCase(txtMa1.getText())) {
                check = true;
            }
        }
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
        } else {
            Student st = listStudents.get(getRow());
            JOptionPane.showMessageDialog(this, svSevice.updateStudent(getMa1(),getHoTen1(),getNgSinh1(),getGioiTinh1(),getNganh1(),getDiemTong1(), st));
            jDialog1.setVisible(false);
            showData(listStudents);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Update thất bại");
    }//GEN-LAST:event_btnCancelActionPerformed

     private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        // TODO add your handling code here:
        ghiFile();
    } 
     private void ghiFile(){
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Student st : listStudents) {
                oos.writeObject(st);
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ghi thất bại");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSearch;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExt;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkDiemHon5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNam1;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoNu1;
    private javax.swing.JTable tblListSinhVien;
    private javax.swing.JTextField txtDiemTong;
    private javax.swing.JTextField txtDiemTong1;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTen1;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMa1;
    private javax.swing.JTextField txtMaSearch;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtNgSinh1;
    private javax.swing.JTextField txtNganh;
    private javax.swing.JTextField txtNganh1;
    // End of variables declaration//GEN-END:variables
}
