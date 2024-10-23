/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;
import com.mycompany.assignment2.Product.Product;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class PurchasesPanel extends JPanel {
    JButton backButton;
    JButton quitButton;
    
    public PurchasesPanel() {
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    public JButton getQuitButton() {
        return this.quitButton;
    }
    
    public void setPurchasesData(HashMap<Product, Integer> purchases) {
        removeAll();
        if (!purchases.isEmpty()) {
            setLayout(new GridLayout(0, 2));
            for (HashMap.Entry<Product, Integer> entry : purchases.entrySet()) {
                JLabel name = new JLabel(entry.getKey().getName());
                JLabel quantity = new JLabel(String.valueOf(entry.getValue()) + " x $" + String.valueOf(entry.getKey().getPrice()));
                add(name);
                add(quantity);
            }
        } else {
            setLayout(new GridLayout(0, 1));
            JLabel message = new JLabel("You have not many any purchases yet.");
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
