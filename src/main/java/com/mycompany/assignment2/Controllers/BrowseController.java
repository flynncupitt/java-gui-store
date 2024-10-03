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
public class BrowseController extends BaseController<BrowsePanel>  {
    
    public BrowseController(MasterController master, BrowsePanel view) {
        super(master, view);
    }
    
    public void refreshBrowsePanel(ArrayList<Product> products) {
        view.setProducts(products);
        
        int index = 0;
        for (JButton b : view.getProductButtons()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    master.updatePip(products.get(index)); //waiting till migrated
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
    }
    
}
