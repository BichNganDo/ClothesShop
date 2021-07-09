/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import helper.DBHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ngan Do
 */
public class ProductModel {

    private static DBHelper dbHelper = new DBHelper();

    public static int addProduct(String name, String image, int price, String status) {
        String sql = "INSERT INTO `product_tbl` (`name`, `image`, `price`, `status`) VALUES ('" + name + "', '" + image + "', '" + price + "', '" + status + "')";

        return dbHelper.executePut(sql);

    }

    public static List<Product> getListProducts() {
        List<Product> listProducts = new ArrayList<>();

        try {
            String sql = "SELECT * FROM product_tbl";
            listProducts = dbHelper.executeGet2(sql);
        } catch (Exception e) {
        }
        return listProducts;

    }

    public static int editProduct(int id, String name, String image, int price, String status) {
        String sql = "UPDATE `product_tbl` SET `name`='" + name + "',`image`='" + image + "',`price`='" + price + "', `status`='" + status + "' WHERE `id`='" + id + "' ";
        return dbHelper.executePut(sql);
    }

    public static Product getProductById(int id) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM product_tbl WHERE `id` = '" + id + "'";
            product = dbHelper.getProductById(sql);
        } catch (Exception e) {
        }
        return product;
    }

    public static int removeProduct(int id) {
        String sql = "DELETE FROM `product_tbl` WHERE id = '" + id + "' ";
        return dbHelper.executePut(sql);

    }

    public static List<Product> searchProduct(String valueSearch) {
        List<Product> listSearchProducts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product_tbl` WHERE name = '"+valueSearch+"'";
            listSearchProducts = dbHelper.executeGet2(sql);
        } catch (Exception e) {
        }
        return listSearchProducts;
    }

}
