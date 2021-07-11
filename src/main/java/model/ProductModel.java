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

    public static int addProduct(String name, String image, int price, String status, int idCate) {
        String sql = "INSERT INTO `product_tbl` (`idCate`, `name`, `image`, `price`, `status`) "
                + "VALUES ('" + idCate + "','" + name + "', '" + image + "', '" + price + "', '" + status + "')";

        return dbHelper.executePut(sql);

    }

    public static List<Product> getListProducts() {
        List<Product> listProducts = new ArrayList<>();

        try {
            String sql = "SELECT product_tbl.*, cate_tbl.cate_name AS cate_name "
                    + "FROM product_tbl INNER JOIN cate_tbl ON product_tbl.idCate = cate_tbl.cate_id";
            listProducts = dbHelper.executeGet(sql);
        } catch (Exception e) {
        }
        return listProducts;

    }

    public static int editProduct(int id, int idCate, String name, String image, int price, String status) {
        String sql = "UPDATE `product_tbl` "
                + "SET `idCate`='" + idCate + "', `name`='" + name + "',`image`='" + image + "',`price`='" + price + "', `status`='" + status + "' "
                + "WHERE `id`='" + id + "' ";
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

    public static List<Product> searchProduct(String valueSearch, int idCate, String status) {
        List<Product> listSearchProducts = new ArrayList<>();
        try {
            String sql = "SELECT product_tbl.*, cate_tbl.cate_name AS cate_name "
                    + "FROM product_tbl INNER JOIN cate_tbl ON product_tbl.idCate = cate_tbl.cate_id "
                    + "WHERE 1=1 ";
            
            if (!"".equals(valueSearch)) {
                sql = sql + " AND name = '" + valueSearch + "' ";
                
            }

            if (idCate > 0) {
                sql = sql + " AND idCate = " + idCate;
            
            }
            
            if(!"".equals(status)){
                sql = sql + " AND status = '" + status + "' ";
            }


            listSearchProducts = dbHelper.executeGet(sql);
        } catch (Exception e) {
        }
        return listSearchProducts;
    }

}
