/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author flynn
 */
public class CartManager {
    Connection conn;
    
    public CartManager(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<CartItem> getCart() {
        ArrayList<CartItem> cart = new ArrayList();
        PreparedStatement statement;
        ResultSet cartItems;

        try {
            String sql = "SELECT product_id, quantity FROM cart_items WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, DatabaseManager.getActiveCustomer());
            cartItems = statement.executeQuery();

            while (cartItems.next()) {
                int productQuantity = cartItems.getInt("quantity");
                Product p = DatabaseManager.getInstance().getProductFromId(cartItems.getInt("product_id"));
                cart.add(new CartItem(p, productQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    
    public void clearCart() {
        PreparedStatement statement;

        try {
            String sql = "DELETE FROM cart_items WHERE customer_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, DatabaseManager.getActiveCustomer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addToCart(int product_id) {
        try {
            PreparedStatement statement;
            int currentQuantity = countCartQuantity(product_id);
            System.out.println("Current quantity for product " + product_id + ": " + currentQuantity);
            if (currentQuantity > 0) {
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                statement = conn.prepareStatement(updateSql);
                statement.setInt(1, currentQuantity + 1);
                statement.setInt(2, DatabaseManager.getActiveCustomer());
                statement.setInt(3, product_id);
                statement.executeUpdate();
                System.out.println("Product already in cart, incrementing quantity");
                return;
            }

            String query = "INSERT INTO cart_items (customer_id, product_id, quantity) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, DatabaseManager.getActiveCustomer());
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
            if (ccq > 1) {
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                statement = conn.prepareStatement(updateSql);
                statement.setInt(1, ccq - 1);
                statement.setInt(2, DatabaseManager.getActiveCustomer());
                statement.setInt(3, product_id);
                statement.executeUpdate();
            } else if (ccq == 1) {
                String sql = "DELETE FROM cart_items WHERE product_id = ? AND customer_id = ? ";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, product_id);
                statement.setInt(2, DatabaseManager.getActiveCustomer());
                statement.executeUpdate();
            } else {
                System.out.println("ERROR - TRIED TO DELETE NON-EXISTENT ITEM IN CART");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Helper method
    private int countCartQuantity(int product_id) {
        PreparedStatement statement;
        ResultSet resultSet = null;
        int quantity = 0;
        try {

            String query = "SELECT quantity FROM cart_items WHERE customer_id = ? AND product_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, DatabaseManager.getActiveCustomer());
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
}
