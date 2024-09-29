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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class BrowsePanel extends JPanel {

    private MainFrame mainFrame;
    private ProductInfoPanel pip;
    private ArrayList<Product> products;
    JLabel title = new JLabel("Browsing");

    public BrowsePanel(MainFrame mainFrame, ProductInfoPanel pip) {
        this.mainFrame = mainFrame;
        this.products = products;
        this.pip = pip;
        this.products = new ArrayList();
        setLayout(new GridLayout(0, 1));
    }

    private void listProducts() {

        for (Product product : products) {
            JButton productButton = new JButton(product.getName());
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pip.setProductInfo(product);
                    mainFrame.showPanel("ProductInfo");
                }
            });
            add(productButton);
        }
    }

    public void setProducts(ArrayList<Product> prods) {
        removeAll();
        add(title);
        this.products = prods;
        listProducts();
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
