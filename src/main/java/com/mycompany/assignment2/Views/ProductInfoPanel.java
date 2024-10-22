/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import Database.DatabaseManager;
import com.mycompany.assignment2.Product.LaptopProduct;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.PhoneProduct;
import com.mycompany.assignment2.Product.Product;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class ProductInfoPanel extends JPanel {
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private String productType;
    private int productId;
    
    private JLabel nameLabel;
     private JLabel priceLabel;
     JButton backButton;
     JButton cartButton;
     JButton quitButton;
    
    public ProductInfoPanel() {
         setLayout(new GridLayout(0, 3));
         
         nameLabel = new JLabel("Loading...");
         add(nameLabel);
         priceLabel = new JLabel("Loading...");
         add(priceLabel);
         cartButton = new JButton("Add to cart");
        add(cartButton);
         backButton = new JButton("Back");
        add(backButton);
        quitButton = new JButton("Quit");
        add(quitButton);
         
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public JButton getCartButton() {
        return this.cartButton;
    }
    public JButton getQuitButton() {
        return this.quitButton;
    }
    
    public void setProductInfo(int id, String name, double price) {
        productId = id;
        nameLabel.setText(name);
        priceLabel.setText(String.format("$%,.2f", price));
    }
}
