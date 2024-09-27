/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flynn
 */
public final class DatabaseManager {

    private static DatabaseManager instance;
    private static final String URL = "jdbc:derby:ShopDB;create=true";
    Connection conn;

    public DatabaseManager() {
        establishConnection();
    }

//    public static void main(String[] args) {
//        Statement statement = null;
//
//        try {
//            conn = DriverManager.getConnection(URL);
//            statement = conn.createStatement();
//
//            String query = "SELECT * FROM products";
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                // Assuming the 'testing' table has columns 'id', 'name', and 'age'
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                BigDecimal price = resultSet.getBigDecimal("price");
//            
//                // Print the result of each row
//                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
//            }
//    }
//    catch (SQLException ex
//
//    
//        ) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
//    }
//
//    
//        finally {
//            // Clean up the resources
//            try {
//            if (statement != null) {
//                statement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//    public void closeConnection() {
//        this.closeConnections();
//    }
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
