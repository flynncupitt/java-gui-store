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
public class PurchasesPanel extends JPanel {
    HashMap<Integer,Integer> map = new HashMap();
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private MainFrame mainFrame;
    
    public PurchasesPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        refreshPurchases();
    }
    
    public void refreshPurchases() {
        removeAll();
        map = dbManager.getPurchases();
        if (!map.isEmpty()) {
            setLayout(new GridLayout(0, 2));
            for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
                Product asProduct = dbManager.getProductFromId(entry.getKey());
                JLabel name = new JLabel(asProduct.getName());
                JLabel quantity = new JLabel(String.valueOf(entry.getValue()) + " x $" + String.valueOf(asProduct.getPrice()));
                add(name);
                add(quantity);
            }
        } else {
            setLayout(new GridLayout(0, 1));
            JLabel message = new JLabel("You have not many any purchases yet.");
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
