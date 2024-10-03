/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Views.HomePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author flynn
 */
public class HomeController extends BaseController<HomePanel> {
    public HomeController(MasterController master, HomePanel view) {
        super(master, view);
        view.getButton("Browse.Phones").addActionListener(e -> master.showPanel("Browse.Phones"));
        view.getButton("Browse.Laptops").addActionListener(e -> master.showPanel("Browse.Laptops"));
        view.getButton("Cart").addActionListener(e -> master.showPanel("Cart"));
        view.getButton("Checkout").addActionListener(e -> master.showPanel("Checkout"));
        view.getButton("Purchases").addActionListener(e -> master.showPanel("Purchases"));
    }
}
