/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.DatabaseManager;
import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.CartPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;

/**
 *
 * @author flynn
 */
public class CartController extends BaseController<CartPanel> {
    
    public CartController(MasterController master, CartPanel view) {
        super(master, view);
    }
    
    public void refreshCartPanel() {
        double cartTotal = 0.0;
        ArrayList<CartItem> cartItems = model.getCart();
        
        for(CartItem item : cartItems) {
            cartTotal += (item.getProduct().getPrice() * item.getQuantity());
        }

        view.setCartData(cartItems, cartTotal);

        ArrayList<JButton> buttons = view.getRemoveButtons();
        int index = 0;
        for (CartItem item : cartItems) {
            final int productId = item.getProduct().getSku();
            buttons.get(index).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.removeFromCart(productId);
                    refreshCartPanel();
                }
            });
            index++;
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
