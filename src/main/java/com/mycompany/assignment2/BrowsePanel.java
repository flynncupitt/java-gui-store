/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class BrowsePanel extends JPanel {
        private DatabaseManager dbManager = DatabaseManager.getInstance();

        public BrowsePanel() {
        setLayout(new GridLayout(0, 1)); // Vertically stacked buttons

//        listPhoneProducts();
    }
        private void listPhoneProducts() {
        try {
            ResultSet phones = dbManager.getPhoneProducts();

            // Loop through the result set to create buttons for each product
            while (phones.next()) {
                String productName = phones.getString("name"); // Assuming "name" is the product's name column
                int productId = phones.getInt("id");

                JButton productButton = new JButton(productName);

                // Add action listener for each button (e.g., to view product details)
                productButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Action to be performed when button is clicked
                        System.out.println("Button for product ID " + productId + " clicked!");
                    }
                });

                // Add button to the panel
                add(productButton);
            }

        } catch (SQLException e) {
            System.out.println("Issue printing products");
            e.printStackTrace();
        }
    }
    
}
