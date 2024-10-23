/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Product.CartItem;
import com.mycompany.assignment2.Views.CartPanel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author flynn
 */
public class CartController extends BaseAbstractController<CartPanel> {
    
    public CartController(MasterController master, CartPanel view) {
        super(master, view);
    }
    
    @Override
    public void refreshPanel() {
        double cartTotal = 0.0;
        ArrayList<CartItem> cartItems = model.getCart();

        for (CartItem item : cartItems) {
            cartTotal += (item.getProduct().getPrice() * item.getQuantity());
        }

        view.setCartData(cartItems, cartTotal);

        ArrayList<JButton> buttons = view.getRemoveButtons();
        int index = 0;
        for (CartItem item : cartItems) {
            final int productId = item.getProduct().getSku();
            buttons.get(index).addActionListener((ActionEvent e) -> {
                model.removeFromCart(productId);
                refreshPanel();
            });
            index++;
        }

        view.getBackButton().addActionListener((ActionEvent e) -> {
            master.showPanel("Home");
        });
        view.getQuitButton().addActionListener((ActionEvent e) -> {
            master.globalQuit();
        });
    }
}
