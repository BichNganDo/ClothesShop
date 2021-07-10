package model;

import entity.User;
import helper.DBHelper;
import helper.SecurityHelper;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private static DBHelper dbHelper = new DBHelper();

    public static int addUser(String username, String password, String phone, String address) {
        String sqlCheckPhone = "SELECT * FROM `user_tbl` WHERE  `phone`='" + phone + "'";
        List<User> listCheckPhone = dbHelper.executeGetUser(sqlCheckPhone);
        if (listCheckPhone.size() > 0) {
            return -6;
        } else {
            String sql = " INSERT INTO `user_tbl`(`username`, `password`, `phone`, `address`) VALUES ('" + username + "','" + password + "','" + phone + "','" + address + "')";
            return dbHelper.executePut(sql);

        }

    }

    public static List<User> getListUsers() {
        List<User> listUsers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM user_tbl";
            listUsers = dbHelper.executeGetUser(sql);
        } catch (Exception e) {
        }
        return listUsers;

    }

    public static int editUser(int id, String username, String password, String address) {
        if ("".equals(password)) {
            String sql = "UPDATE `user_tbl` SET `username`='" + username + "', `address`='" + address + "' WHERE `id`='" + id + "' ";
            return dbHelper.executePut(sql);
        } else {
            password = SecurityHelper.getMD5Hash(password);
            String sql = "UPDATE `user_tbl` SET `username`='" + username + "',`password`='" + password + "',`address`='" + address + "' WHERE `id`='" + id + "' ";
            return dbHelper.executePut(sql);
        }

    }

    public static User getUserById(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM user_tbl WHERE `id` = '" + id + "'";
            user = dbHelper.getUserById(sql);
        } catch (Exception e) {
        }
        return user;
    }

    public static int removeUser(int id) {
        String sql = "DELETE FROM `user_tbl` WHERE id = '" + id + "' ";
        return dbHelper.executePut(sql);

    }
    
    public static boolean checkLogin(String phone, String password) {
        return dbHelper.checkLogin(phone, password);
        
    }

}
