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
public class CheckoutPanel extends JPanel {
    HashMap<Integer,Integer> map = new HashMap();
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private MainFrame mainFrame;
    private Double cartTotal = 0.0;
    
    public CheckoutPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        refreshCheckout();
    }
    
    public void refreshCheckout() {
        removeAll();
        cartTotal = 0.0;
        map = dbManager.getCart();
        if (!map.isEmpty()) {
            setLayout(new GridLayout(0, 3));
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
                        refreshCheckout();
                    }
                });
                add(name);
                add(quantity);
                add(removeButton);
            }
            JLabel cartTotalLabel = new JLabel("Cart total: $" + String.valueOf(cartTotal));
            add(cartTotalLabel);
            JButton checkoutButton = new JButton("Pay & Exit");
            checkoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    mainFrame.showPanel("Home");
                    dbManager.savePurchases();
                    dbManager.clearCart();
                }
            });
            add(checkoutButton);
        } else {
            setLayout(new GridLayout(0, 1));
            JLabel message = new JLabel("There are no items in your cart to checkout.");
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
