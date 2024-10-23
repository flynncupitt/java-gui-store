/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.LaptopProduct;
import com.mycompany.assignment2.Product.PhoneProduct;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import java.awt.event.ActionListener;

/**
 *
 * @author flynn
 */
public class PipController extends BaseController<ProductInfoPanel> {
    public PipController(MasterController master, ProductInfoPanel view) {
        super(master, view);
        
        view.getBackButton().addActionListener(e -> master.showPanel("Browse"));
    }
    
    public void setProductInfo(Product p) {
        int productId = p.getSku();
        System.out.println("set info for: " + productId);
        String nameInfo = "";
        if(p.getSku() < 200) {
            PhoneProduct pp = (PhoneProduct) p;
            nameInfo = pp.getName() + ", " + pp.getColour();
        } else {
            LaptopProduct lp = (LaptopProduct) p;
            nameInfo =  lp.getName() + ", " + lp.getScreenSize() + " inch";
        }
        view.setProductInfo(productId, nameInfo, p.getPrice());
        
        //Need to remove previous add to cart actions to avoid double triggers
        for (ActionListener al : view.getCartButton().getActionListeners()) {
            view.getCartButton().removeActionListener(al);
            System.out.println("removed an action listener");
        }
        view.getCartButton().addActionListener(e -> model.addToCart(productId));
        view.getQuitButton().addActionListener(e -> master.globalQuit());
    }
}
