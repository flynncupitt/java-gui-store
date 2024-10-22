/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.BrowsePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;


/**
 *
 * @author flynn
 */
public class BrowseController extends BaseAbstractController<BrowsePanel> {
    ArrayList<Product> currentProducts;
    public BrowseController(MasterController master, BrowsePanel view) {
        super(master, view);
    }
    
    public void setBrowseProducts(ArrayList<Product> products) {
        view.setProducts(products);
        currentProducts = products;
    }

    @Override
    public void refreshPanel() {
        ArrayList<JButton> buttons = view.getProductButtons();
        for (int i = 0; i < buttons.size(); i++) {
            final int currentButtonIndex = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    master.updatePip(currentProducts.get(currentButtonIndex));
                    master.showPanel("ProductInfo");
                }
            });
        }

        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                master.showPanel("Home");
            }
        });

        view.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                master.globalQuit();
            }
        });
    }
}
