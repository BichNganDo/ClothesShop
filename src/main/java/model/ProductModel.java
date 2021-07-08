/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.DBHelper;

/**
 *
 * @author Ngan Do
 */
public class ProductModel {
    private static DBHelper dbHelper = new DBHelper();
    
    public static int addProduct (String name, String image, int price, String status) {
        String sql = "INSERT INTO `product_tbl` (`name`, `image`, `price`, `status`) VALUES ('" + name + "', '" + image + "', '" + price + "', '" + status + "')";
        
        return dbHelper.executePut(sql);
        
    }
}
