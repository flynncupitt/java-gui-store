/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.MainFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class HomePanel extends JPanel {
    JLabel greeting;
    JButton browsePhonesButton;
    JButton browseLaptopsButton;
    JButton viewCartButton;
    JButton checkoutButton;
    JButton purchasesButton;
    JButton quitButton;
    Map<String, JButton> buttons = new HashMap();
    
    public HomePanel() {
         setLayout(new GridLayout(0, 1, 10, 10));
        greeting = new JLabel("Welcome");
        browsePhonesButton = new JButton("Browse Phones");
        browseLaptopsButton = new JButton("Browse Laptops");
        viewCartButton = new JButton("View Cart");
        checkoutButton = new JButton("Checkout");
        purchasesButton = new JButton("View Purchase History");
        quitButton = new JButton("Quit");
        
        add(greeting);
        add(browsePhonesButton);
        add(browseLaptopsButton);
        add(viewCartButton);
        add(checkoutButton);
        add(purchasesButton);
        add(quitButton);
        
        buttons.put("Browse.Phones", browsePhonesButton);
        buttons.put("Browse.Laptops", browseLaptopsButton);
        buttons.put("Cart", viewCartButton);
        buttons.put("Checkout", checkoutButton);
        buttons.put("Purchases", purchasesButton);
        buttons.put("Quit", quitButton);
        
        
    }
    
    public void setGreetingText(String s) {
        this.greeting.setText(s);
    }
    
    public String getGreetingText() {
        return this.greeting.getText();
    }
    
    public JButton getButton(String name) {
        return buttons.get(name);
    }
    
}
