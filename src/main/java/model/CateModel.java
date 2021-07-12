/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Categogy;
import helper.DBHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ngan Do
 */
public class CateModel {

    private static DBHelper dbHelper = new DBHelper();

    public static int addCate(String name) {
        String sql = "INSERT INTO `cate_tbl` (`cate_name`) VALUES ('" + name + "')";

        return dbHelper.executePut(sql);

    }

    public static List<Categogy> getListCategogies() {
        List<Categogy> listCategogies = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cate_tbl";
            listCategogies = dbHelper.executeGetCate(sql);
        } catch (Exception e) {
        }
        return listCategogies;

    }

    public static int editCate(int idCate, String name) {
        String sql = "UPDATE `cate_tbl` SET `cate_name`='" + name + "' WHERE `cate_id` = '" + idCate + "'";
        return dbHelper.executePut(sql);
    }

    public static Categogy getCateById(int idCate) {
        Categogy categogy = new Categogy();
        try {
            String sql = "SELECT * FROM cate_tbl WHERE `cate_id` = '" + idCate + "'";
            categogy = dbHelper.getCateById(sql);
        } catch (Exception e) {
        }
        return categogy;
    }

    public static int removeCate(int idCate) {
        String sql = "DELETE FROM `cate_tbl` WHERE cate_id = '" + idCate + "' ";
        return dbHelper.executePut(sql);

    }

   

}
