/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author laptop
 */
public class UserService {
    String path = "users.txt";
    List<User> listUser = new ArrayList<>();
    
    public String themUser(String userName, String password){
        docUser();
        try {
            listUser.add(new User(userName, password));
            ghiUser();
            return "Tạo tài khoản thành công";
        } catch (Exception e) {
            return "Tạo tài khoản thất bại";
        }
    }
    
    public void docUser(){
        try {
            File file = new File(path);

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                listUser.add((User) ois.readObject());
            }

            ois.close();
            fis.close();
        } catch (Exception e) {
        }
    }
    
    public void ghiUser(){
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (User user : listUser) {
                oos.writeObject(user);
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }
}
