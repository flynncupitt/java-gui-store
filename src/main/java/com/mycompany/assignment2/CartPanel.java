/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

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
public class CartPanel extends JPanel {
    HashMap<Integer,Integer> map = new HashMap();
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private MainFrame mainFrame;
    private Double cartTotal = 0.0;
    
    public CartPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(0, 3));
        
//        JLabel title = new JLabel("Cart");
//        add(title);
        refreshCartPanel();
    }
    public void refreshCartPanel() {
        removeAll();
        cartTotal = 0.0;
        map = dbManager.getCart();
        if (!map.isEmpty()) {
            for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
                Product asProduct = dbManager.getProductFromId(entry.getKey());
                cartTotal += (asProduct.getPrice() * entry.getValue());
                int productId = entry.getKey();
                
                JLabel name = new JLabel(asProduct.getName());
                JLabel quantity = new JLabel(String.valueOf(entry.getValue()) + " x $" + String.valueOf(asProduct.getPrice()));
                JButton removeButton = new JButton((entry.getValue() > 1) ? "Subtract" : "Remove");
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dbManager.removeFromCart(productId);
                        refreshCartPanel();
                    }
                });
                add(name);
                add(quantity);
                add(removeButton);
            }
            JLabel cartTotalLabel = new JLabel("Cart total: $" + String.valueOf(cartTotal));
            add(cartTotalLabel);
        } else {
            JLabel message = new JLabel("Your cart is currently empty.");
            add(message);
        }
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Home");
            }
            });
        add(backButton);
        revalidate();
        repaint();
    }
}
