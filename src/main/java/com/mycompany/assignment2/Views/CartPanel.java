/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.Product;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class CartPanel extends JPanel {
    private Double cartTotal = 0.0;
    private ArrayList<JButton> removeButtons = new ArrayList();
    private JButton backButton;
    
    public CartPanel() {
    }
    
    public ArrayList<JButton> getRemoveButtons() {
        return removeButtons;
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public void setCartData(HashMap<Product, Integer> cart, double total) {
        int tempCount = 0;
        System.out.println("Setting new cart data");
        removeAll();
        removeButtons.clear();
        if (!cart.isEmpty()) {
            setLayout(new GridLayout(0, 3));
            for (HashMap.Entry<Product, Integer> entry : cart.entrySet()) {
                JLabel name = new JLabel(entry.getKey().getName());
                JLabel quantity = new JLabel(String.valueOf(entry.getValue()) + " x $" + String.valueOf(entry.getKey().getPrice()));
                JButton removeButton = new JButton((entry.getValue() > 1) ? "Subtract" : "Remove");
                add(name);
                add(quantity);
                add(removeButton);
                removeButtons.add(removeButton);
                System.out.println("ADDED button " + tempCount + " for " + entry.getKey().getSku());
                tempCount++;
            }
            JLabel cartTotalLabel = new JLabel("Cart total: $" + String.valueOf(cartTotal));
            add(cartTotalLabel);

        } else {
            setLayout(new GridLayout(0, 1));
            JLabel message = new JLabel("Your cart is currently empty.");
            add(message);
        }
        backButton = new JButton("Back");
        add(backButton);
        revalidate();
        repaint();
    }
}
