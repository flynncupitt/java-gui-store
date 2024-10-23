/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.PurchasesPanel;
import java.util.HashMap;

/**
 *
 * @author flynn
 */
public class PurchasesController extends BaseAbstractController<PurchasesPanel> {
    
    public PurchasesController(MasterController master, PurchasesPanel view) {
        super(master, view);
    }
    
    @Override
    public void refreshPanel() {

        HashMap<Integer, Integer> map = model.getPurchases();
        HashMap<Product, Integer> purchases = new HashMap();

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            Product asProduct = model.getProductFromId(entry.getKey());
            purchases.put(asProduct, entry.getValue());
        }

        view.setPurchasesData(purchases);

        view.getBackButton().addActionListener(e -> master.showPanel("Home"));
        view.getQuitButton().addActionListener(e -> master.globalQuit());
    }
    
}
