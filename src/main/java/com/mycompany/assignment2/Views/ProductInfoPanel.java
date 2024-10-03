/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.DatabaseManager;
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
    private MainFrame mainFrame;
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private String productType;
    private int productId;
    
    private JLabel nameLabel;
     private JLabel priceLabel;
     JButton backButton;
     JButton cartButton;
    
    public ProductInfoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
         setLayout(new GridLayout(0, 2));
         
         nameLabel = new JLabel("Loading...");
         add(nameLabel);
         priceLabel = new JLabel("Loading...");
         add(priceLabel);
         cartButton = new JButton("Add to cart");
        add(cartButton);
         backButton = new JButton("Back");
        add(backButton);
         
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public JButton getCartButton() {
        return this.cartButton;
    }
    
    public void setProductInfo(int id, String name, double price) {
        productId = id;
        nameLabel.setText(name);
        priceLabel.setText(String.valueOf(price));
    }
}
