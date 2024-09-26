/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

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
    private MainFrame mainFrame;
    
    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
         setLayout(new GridLayout(5, 1, 10, 10));
            
        JButton browsePhonesButton = new JButton("Browse Phones");
        JButton browseLaptopsButton = new JButton("Browse Laptops");
        JButton viewCartButton = new JButton("View Cart");
        JButton checkoutButton = new JButton("Checkout");
        JButton quitButton = new JButton("Quit");
        
        browsePhonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to BrowsePanel when button is clicked
                mainFrame.showBrowsePanel();
            }
        });
        add(browsePhonesButton);
        add(browseLaptopsButton);
        add(viewCartButton);
        add(checkoutButton);
        add(quitButton);
    }
    
}
