/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author flynn
 */
public class PurchasesManager {
    Connection conn;
    
    public PurchasesManager(Connection conn) {
        this.conn = conn;
    }
    
    //Saves items in cart to purchases, used when user checks out
    public void savePurchases() {
        PreparedStatement statement;
        PreparedStatement updateStatement;
        PreparedStatement insertStatement;
        try {
                // Update existing quantities
                String updateSql = "UPDATE purchases SET quantity = quantity + (SELECT quantity FROM cart_items WHERE customer_id = ? AND product_id = purchases.product_id) "
                        + "WHERE customer_id = ? AND product_id IN (SELECT product_id FROM cart_items WHERE customer_id = ?)";
                updateStatement = conn.prepareStatement(updateSql);
                int customer = DatabaseManager.getActiveCustomer();
                updateStatement.setInt(1, customer);
                updateStatement.setInt(2, customer);
                updateStatement.setInt(3, customer);
                int updatedRows = updateStatement.executeUpdate();
                System.out.println("Rows updated: " + updatedRows);

                // Now insert the new records from cart_items that don't exist in purchases
                String insertSql = "INSERT INTO purchases (customer_id, product_id, quantity) "
                        + "SELECT customer_id, product_id, quantity FROM cart_items WHERE customer_id = ? "
                        + "AND product_id NOT IN (SELECT product_id FROM purchases WHERE customer_id = ?)";
                insertStatement = conn.prepareStatement(insertSql);
                insertStatement.setInt(1, DatabaseManager.getActiveCustomer());
                insertStatement.setInt(2, DatabaseManager.getActiveCustomer());
                int insertedRows = insertStatement.executeUpdate();
                System.out.println("Rows inserted: " + insertedRows);
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
            statement.setInt(1, DatabaseManager.getActiveCustomer());
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
}
