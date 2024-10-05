/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.CartItem;
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
    
    public void setCartData(ArrayList<CartItem> cart, double total) {
        int tempCount = 0;
        System.out.println("Setting new cart data");
        removeAll();
        removeButtons.clear();
        if (!cart.isEmpty()) {
            setLayout(new GridLayout(0, 3));
            for (CartItem item : cart) {
                Product p = item.getProduct();
                JLabel name = new JLabel(p.getName());
                JLabel quantity = new JLabel(String.valueOf(item.getQuantity()) + " x $" + String.valueOf(p.getPrice()));
                JButton removeButton = new JButton((item.getQuantity() > 1) ? "Subtract"+tempCount : "Remove"+tempCount);
                add(name);
                add(quantity);
                add(removeButton);
                removeButtons.add(removeButton);
                tempCount++;
            }
            JLabel cartTotalLabel = new JLabel("Total: $" + String.format("%,.2f", total));
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
