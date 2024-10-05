/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.CheckoutPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;

/**
 *
 * @author flynn
 */
public class CheckoutController extends BaseController<CheckoutPanel> {

    public CheckoutController(MasterController master, CheckoutPanel view) {
        super(master, view);
    }

    public void refreshCheckoutPanel() {

        double cartTotal = 0.0;
        ArrayList<CartItem> cart = model.getCart();

        for(CartItem item : cart) {
            cartTotal += (item.getProduct().getPrice() * item.getQuantity());
        }

        view.setCheckoutData(cart, cartTotal);

        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                master.showPanel("Home");
            }
        });

        view.getCheckoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.savePurchases();
                model.clearCart();
            }
        });
    }

}
