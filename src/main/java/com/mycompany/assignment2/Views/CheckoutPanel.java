/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.Product;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class CheckoutPanel extends JPanel {
    HashMap<Integer,Integer> map = new HashMap();
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private MainFrame mainFrame;
    private Double cartTotal = 0.0;
    private JButton backButton;
    private JButton checkoutButton;
    
    public CheckoutPanel() {
//        refreshCheckout();
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public JButton getCheckoutButton() {
        return this.checkoutButton;
    }
    
    public void setCheckoutData(HashMap<Product, Integer> cart, double total) {
        removeAll();
        if (!cart.isEmpty()) {
            setLayout(new GridLayout(0, 3));
            for (HashMap.Entry<Product, Integer> entry : cart.entrySet()) {
                JLabel name = new JLabel(entry.getKey().getName());
                JLabel quantity = new JLabel(String.valueOf(entry.getValue()) + " x $" + String.valueOf(entry.getKey().getPrice()));
                add(name);
                add(quantity);
            }
            JLabel cartTotalLabel = new JLabel("Cart total: $" + String.valueOf(cartTotal));
            add(cartTotalLabel);
            
            checkoutButton = new JButton("Pay & Exit");
            add(checkoutButton);
        } else {
            setLayout(new GridLayout(0, 1));
            JLabel message = new JLabel("There are no items in your cart to checkout.");
            add(message);
        }
        backButton = new JButton("Back");
        add(backButton);
        revalidate();
        repaint();
    }
}
