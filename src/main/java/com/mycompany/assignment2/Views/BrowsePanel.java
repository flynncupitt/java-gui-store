/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author flynn
 */
public class BrowsePanel extends JPanel {
    private ProductInfoPanel pip;
    private ArrayList<JButton> productButtons = new ArrayList();
    JLabel title = new JLabel("Browsing");
    JButton backButton;

    public BrowsePanel(ProductInfoPanel pip) {
        this.pip = pip;
        setLayout(new GridLayout(0, 1));
        add(title);
    }
    
    public ArrayList<JButton> getProductButtons() {
        return productButtons;
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }


    public void setProducts(ArrayList<Product> prods) {
        removeAll();
        for (Product product : prods) {
            JButton productButton = new JButton(product.getName());
            productButtons.add(productButton);
            add(productButton);
        }
        backButton = new JButton("Back");
        add(backButton);
        revalidate();
        repaint();
    }

}
