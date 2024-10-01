/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flynn
 */
public final class DatabaseManager {

    private static DatabaseManager instance;
    private static final String URL = "jdbc:derby:ShopDB;create=true";
    private static int globalActiveCustomer = 1; //MUST CHANGE TO BE DYNAMIC
    Connection conn;

    public DatabaseManager() {
        establishConnection();
    }
    
    public HashMap<Integer, Integer> getCart() {
        HashMap<Integer, Integer> cart = new HashMap();
        PreparedStatement statement;
        ResultSet cartItems;
        
        try {
            String sql = "SELECT product_id, quantity FROM cart_items WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, globalActiveCustomer);
            cartItems = statement.executeQuery();

            while (cartItems.next()) {
                int productQuantity = cartItems.getInt("quantity");
                cart.put(cartItems.getInt("product_id"), productQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them)
        }
        return cart;
    }
    
    public void clearCart() {
        PreparedStatement statement;
        
        try {
            String sql = "DELETE FROM cart_items WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, globalActiveCustomer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void savePurchases() {
        PreparedStatement statement;
        
        try {
            String sql = "INSERT INTO purchases (customer_id, product_id, quantity) SELECT customer_id, product_id, quantity FROM cart_items WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, globalActiveCustomer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them)
        }
    }
    
    public HashMap<Integer, Integer> getPurchases() {
        HashMap<Integer, Integer> purchases = new HashMap();
        PreparedStatement statement;
        ResultSet resultSet;
        
        try {
            String sql = "SELECT product_id, quantity FROM purchases WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, globalActiveCustomer);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productQuantity = resultSet.getInt("quantity");
                purchases.put(resultSet.getInt("product_id"), productQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them)
        }
        return purchases;
    }
    
    public Product getProductFromId(int productId) {
        PreparedStatement statement;
        ResultSet nameSet;
        
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            nameSet = statement.executeQuery();
            if (nameSet.next()) {
                if (productId < 200) {
                    return new PhoneProduct(productId, nameSet.getString("name"), nameSet.getDouble("price"), nameSet.getString("colour"));
                } else {
                    return new LaptopProduct(productId, nameSet.getString("name"), nameSet.getDouble("price"), nameSet.getDouble("screensize"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them)
        }
        return null;
    }
    
    public void addToCart(int product_id) {
        try {
            PreparedStatement statement;
            int currentQuantity = countCartQuantity(product_id);
            if( currentQuantity > 0) {
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                statement = conn.prepareStatement(updateSql);
                statement.setInt(1,currentQuantity+1);
                statement.setInt(2, globalActiveCustomer);
                statement.setInt(3, product_id);
                statement.executeUpdate();
                System.out.println("Product already in cart, incrementing quantity");
                return;
            }

            String query = "INSERT INTO cart_items (customer_id, product_id, quantity) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, globalActiveCustomer);
            statement.setInt(2, product_id);
            statement.setInt(3, 1);
            statement.executeUpdate();
            System.out.println("Added product to cart for first time");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     public void removeFromCart(int product_id) {
        PreparedStatement statement;
        try {
            int ccq = countCartQuantity(product_id);
            if( ccq > 1) {
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                statement = conn.prepareStatement(updateSql);
                statement.setInt(1,ccq-1);
                statement.setInt(2, globalActiveCustomer);
                statement.setInt(3, product_id);
                statement.executeUpdate();
            } else if(ccq == 1) {
                String sql = "DELETE FROM cart_items WHERE product_id = ? AND customer_id = ? ";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, product_id);
                statement.setInt(2, globalActiveCustomer);
                statement.executeUpdate();
            } else {
                System.out.println("ERROR - TRIED TO DELETE NON-EXISTENT ITEM IN CART");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private int countCartQuantity(int product_id) {
        PreparedStatement statement;
        ResultSet resultSet = null;
        int quantity = 0;
        try {

            String query = "SELECT quantity FROM cart_items WHERE customer_id = ? AND product_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, globalActiveCustomer);
            statement.setInt(2, product_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quantity;
    }
    
    public ArrayList<Product> getPhoneProducts() {
        ArrayList<Product> phones = new ArrayList();
        try {
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM products WHERE id BETWEEN 100 AND 199";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                phones.add(new PhoneProduct(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getString("colour")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phones;
    }
    
    public ArrayList<Product> getLaptopProducts() {
        ArrayList<Product> laptops = new ArrayList();
        try {
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM products WHERE id BETWEEN 200 AND 299";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                laptops.add(new LaptopProduct(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getDouble("screensize")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return laptops;
    }
    
public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL);
                //System.out.println(URL + "   CONNECTED....");

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
//
//    public void closeConnections() {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }

}
