/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import Database.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.Product;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JButton quitButton;
    
    public CheckoutPanel() {
//        refreshCheckout();
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public JButton getCheckoutButton() {
        return this.checkoutButton;
    }
    
    public JButton getQuitButton() {
        return this.quitButton;
    }
    
    public void setCheckoutData(ArrayList<CartItem> cart, double total) {
        removeAll();
        if (!cart.isEmpty()) {
            setLayout(new GridLayout(0, 2));
            for (CartItem item : cart) {
                JLabel name = new JLabel(item.getProduct().getName());
                JLabel quantity = new JLabel(String.valueOf(item.getQuantity()) + " x $" + String.valueOf(item.getProduct().getPrice()));
                add(name);
                add(quantity);
            }
            JLabel cartTotalLabel = new JLabel("Cart total: $" + String.format("%,.2f", total));
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
        quitButton = new JButton("Quit");
        add(quitButton);
        revalidate();
        repaint();
    }
}
