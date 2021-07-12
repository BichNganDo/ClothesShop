/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import entity.Categogy;
import entity.Product;
import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ngando
 */
public class DBHelper {

    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/clothes_shop?useUnicode=true&characterEncoding=UTF-8"; //INSERT DATABASE NAME
    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "123456";

    private Connection conn = null;
    private Statement stmt = null;

    public DBHelper() {
        //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testGet() {
        try {
            String sql;
            sql = "SELECT * FROM product_tbl";
            ResultSet rs = stmt.executeQuery(sql); // DML
            // stmt.executeUpdate(sql); // DDL

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Display values
                System.out.print(rs.getInt(1));
                System.out.print(rs.getString(2));
                System.out.print(rs.getString(3));
                System.out.print(rs.getString(4));
                System.out.print(rs.getString(5));
                System.out.println("");
            }
            //STEP 6: Clean-up environment
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int executePut(String sql) {
        try {
            open();
            int executeUpdate = stmt.executeUpdate(sql); // DDL
            return executeUpdate;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            close();
        }
        return -1;
    }

    public List<Product> executeGet(String sql) {
        List<Product> listProducts = new ArrayList<>();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setIdCate(rs.getInt("idCate"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getInt("price"));
                product.setStatus(rs.getString("status"));
                product.setCateName(rs.getString("cate_name"));
                listProducts.add(product);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return listProducts;

    }

    public Product getProductById(String sql) {
        Product product = new Product();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                product.setId(rs.getInt(1));
                 product.setIdCate(rs.getInt(2));
                product.setName(rs.getString(3));
                product.setImage(rs.getString(4));
                product.setPrice(rs.getInt(5));
                product.setStatus(rs.getString(6));

            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return product;

    }

    public List<User> executeGetUser(String sql) {
        List<User> listUsers = new ArrayList<>();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
//                user.setPassword(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setAddress(rs.getString(5));

                listUsers.add(user);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return listUsers;

    }

    public User getUserById(String sql) {
        User user = new User();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setAddress(rs.getString(5));
            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return user;

    }

    public List<Categogy> executeGetCate(String sql) {
        List<Categogy> listCategogies = new ArrayList<>();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                Categogy categogy = new Categogy();
                categogy.setIdCate(rs.getInt(1));
                categogy.setName(rs.getString(2));

                listCategogies.add(categogy);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return listCategogies;

    }

    public Categogy getCateById(String sql) {
        Categogy categogy = new Categogy();
        try {
            open();
            ResultSet rs = stmt.executeQuery(sql); // DML

            while (rs.next()) {
                categogy.setIdCate(rs.getInt(1));
                categogy.setName(rs.getString(2));
            }

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return categogy;

    }

    public boolean checkLogin(String phone, String password) {
        open();
        String sql = "SELECT `phone`, `password` FROM `user_tbl` WHERE phone = '" + phone + "' AND password = '" + password + "'";

        try {
            ResultSet rs = stmt.executeQuery(sql); // DML
            while (rs.next()) {
                if (rs.getString(1).equals(phone) && rs.getString(2).equals(password)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return false;
    }

}
