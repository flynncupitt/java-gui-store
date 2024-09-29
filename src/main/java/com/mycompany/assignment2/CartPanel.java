/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class CartPanel extends JPanel {
    HashMap<String,Integer> map = new HashMap();
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    public CartPanel() {
        setLayout(new GridLayout(0, 2));
        map = dbManager.getCart();
        
//        JLabel title = new JLabel("Cart");
//        add(title);
        for(HashMap.Entry<String,Integer> entry: map.entrySet()) {
            JLabel name = new JLabel(entry.getKey());
            JLabel quantity = new JLabel(String.valueOf(entry.getValue()));
            add(name);
            add(quantity);
        }
    }
}
