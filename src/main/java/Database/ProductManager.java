/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import com.mycompany.assignment2.Product.LaptopProduct;
import com.mycompany.assignment2.Product.PhoneProduct;
import com.mycompany.assignment2.Product.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ProductManager {
    Connection conn;
    
    public ProductManager(Connection conn) {
        this.conn = conn;
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
}
