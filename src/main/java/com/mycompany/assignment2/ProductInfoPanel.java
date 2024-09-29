/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

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
    
    public ProductInfoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
         setLayout(new GridLayout(0, 2));
         
         nameLabel = new JLabel("Loading...");
         add(nameLabel);
         priceLabel = new JLabel("Loading...");
         add(priceLabel);
         JButton cartButton = new JButton("Add to cart");
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbManager.addToCart(productId);
            }
        });
        add(cartButton);
         JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Browse");
            }
        });
        add(backButton);
         
    }
    
    public void setProductInfo(Product p) {
        productId = p.getSku();
        productType = p.getSku() < 200 ? "phone" : "laptop";
        PhoneProduct pp = null;
        LaptopProduct lp = null;
        if(productType.equals("phone")) {
            pp = (PhoneProduct) p;
        } else if(productType.equals("laptop")) {
            lp = (LaptopProduct) p;
        }
        nameLabel.setText(productType.equals("phone") ? p.getName() + ", " + pp.getColour() : p.getName() + ", " + lp.getScreenSize() + " inch");
        priceLabel.setText("$" + String.valueOf(p.getPrice()));
    }
}
