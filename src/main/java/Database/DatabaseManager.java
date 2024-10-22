/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.Product;
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
    private static int globalActiveCustomer;
    Connection conn;
    CartManager cart;
    PurchasesManager purchases;
    ProductManager product;

    public DatabaseManager() {
        establishConnection();
        cart = new CartManager(conn);
        purchases = new PurchasesManager(conn);
        product = new ProductManager(conn);
    }
    
    public static int getActiveCustomer() {
        return globalActiveCustomer;
    }
    
    //CART METHODS
    public ArrayList<CartItem> getCart() {
        return cart.getCart();
    }
    public void clearCart() {
        cart.clearCart();
    }
    public void addToCart(int product_id) {
        System.out.println("calling add for: "  + product_id);
        cart.addToCart(product_id);
    }
    public void removeFromCart(int product_id) {
        cart.removeFromCart(product_id);
    }
    
    //PURCHASES METHODS
    public void savePurchases() {
        purchases.savePurchases();
    }
    public HashMap<Integer, Integer> getPurchases() {
        return purchases.getPurchases();
    }
    //PRODUCT METHODS
    public Product getProductFromId(int productId) {
        return product.getProductFromId(productId);
    }
    public ArrayList<Product> getPhoneProducts() {
        return product.getPhoneProducts();
    }
    public ArrayList<Product> getLaptopProducts() {
        return product.getLaptopProducts();
    }

    //Handles login/sign up process: check if customer exists, then login else create customer
    public void userLoginSignup(String username) {
        PreparedStatement statement;
        ResultSet customer;
        try {
            String selectSql = "SELECT id FROM customers WHERE username = ?";
            statement = conn.prepareStatement(selectSql);
            statement.setString(1, username);
            customer = statement.executeQuery();

            if (customer.next()) {
                globalActiveCustomer = customer.getInt("id");
                System.out.println("customer exists: set active customer: " + customer.getInt("id"));
            } else {
                String insertSql = "INSERT INTO customers (username) VALUES (?)";
                statement = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, username);
                statement.executeUpdate();

                customer = statement.getGeneratedKeys();
                if (customer.next()) {
                    globalActiveCustomer = customer.getInt(1);
                    System.out.println("customer doesn't exist, creating with ID: " + customer.getInt(1));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Return username of curent active user
    public String getActiveUserName() {
        PreparedStatement statement;
        ResultSet user;

        try {
            String sql = "SELECT username FROM customers WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, globalActiveCustomer);
            user = statement.executeQuery();

            if (user.next()) {
                return user.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
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
}
