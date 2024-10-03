/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.MainFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class HomePanel extends JPanel {
     JButton browsePhonesButton;
    JButton browseLaptopsButton;
    JButton viewCartButton;
    JButton checkoutButton;
    JButton purchasesButton;
    JButton quitButton;
    
    public HomePanel() {
         setLayout(new GridLayout(0, 1, 10, 10));
            
        browsePhonesButton = new JButton("Browse Phones");
        browseLaptopsButton = new JButton("Browse Laptops");
        viewCartButton = new JButton("View Cart");
        checkoutButton = new JButton("Checkout");
        purchasesButton = new JButton("View Purchase History");
        quitButton = new JButton("Quit");
        
        add(browsePhonesButton);
        add(browseLaptopsButton);
        add(viewCartButton);
        add(checkoutButton);
        add(purchasesButton);
        add(quitButton);
        
    }
    
    public JButton getButton(String name) {
        switch(name) {
            case "Browse.Phones":
                return browsePhonesButton;
            case "Browse.Laptops":
                return browseLaptopsButton;
            case "Cart":
                return viewCartButton;
            case "Checkout":
                return checkoutButton;
            case "Purchases":
                return purchasesButton;
            case "Quit":
                return quitButton;
            default:
                System.out.println("Error: Couldn't find specified home panel button");
                return null;
        }
    }
    
}
