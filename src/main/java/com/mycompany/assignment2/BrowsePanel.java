/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class BrowsePanel extends JPanel {
        private MainFrame mainFrame;
        private ProductInfoPanel pip;
        private DatabaseManager dbManager = DatabaseManager.getInstance();

        public BrowsePanel(MainFrame mainFrame, ProductInfoPanel pip) {
            this.mainFrame = mainFrame;
            this.pip = pip;
        setLayout(new GridLayout(0, 1));

        listPhoneProducts();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Home");
            }
        });
        add(backButton);
    }
        private void listPhoneProducts() {
            ArrayList<PhoneProduct> phones = dbManager.getPhoneProducts();
            
            for(PhoneProduct phone : phones) {
                JButton productButton = new JButton(phone.getName());
                productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pip.setProductInfo(phone);
                mainFrame.showPanel("ProductInfo");
            }
        });
                add(productButton);
            }
    }
    
}
