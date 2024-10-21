/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.LaptopProduct;
import com.mycompany.assignment2.Product.PhoneProduct;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.ProductInfoPanel;

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
        String nameInfo = "";
        if(p.getSku() < 200) {
            PhoneProduct pp = (PhoneProduct) p;
            nameInfo = pp.getName() + ", " + pp.getColour();
        } else {
            LaptopProduct lp = (LaptopProduct) p;
            nameInfo =  lp.getName() + ", " + lp.getScreenSize() + " inch";
        }
        view.setProductInfo(productId, nameInfo, p.getPrice());
        view.getCartButton().addActionListener(e -> model.addToCart(productId));
        view.getQuitButton().addActionListener(e -> master.globalQuit());
    }
}
