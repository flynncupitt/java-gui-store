/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import Database.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.BrowsePanel;
import com.mycompany.assignment2.Views.CartPanel;
import com.mycompany.assignment2.Views.CheckoutPanel;
import com.mycompany.assignment2.Views.HomePanel;
import com.mycompany.assignment2.Views.LoginPanel;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import com.mycompany.assignment2.Views.PurchasesPanel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author flynn
 */
public class MasterController {
    MainFrame frame;
    DatabaseManager model = DatabaseManager.getInstance();
    
    private CartController cartController;
    private HomeController homeController;
    private CheckoutController checkoutController;
    private BrowseController browseController;
    private PurchasesController purchasesController;
    private PipController pipController;
    private LoginController loginController;
    
    private Map<String, Runnable> panelActions = new HashMap();
    
    public MasterController(MainFrame frame) {
        this.frame = frame;
        cartController = new CartController(this, (CartPanel) frame.getPanel("Cart"));
        homeController = new HomeController(this, (HomePanel) frame.getPanel("Home"));
        checkoutController = new CheckoutController(this, (CheckoutPanel) frame.getPanel("Checkout"));
        browseController = new BrowseController(this, (BrowsePanel) frame.getPanel("Browse"));
        purchasesController = new PurchasesController(this, (PurchasesPanel) frame.getPanel("Purchases"));
        pipController = new PipController(this, (ProductInfoPanel) frame.getPanel("ProductInfo"));
        loginController = new LoginController(this, (LoginPanel) frame.getPanel("Login"));
        
        //Add showPanel() actions
        panelActions.put("Cart", () -> cartController.refreshPanel());
        panelActions.put("Checkout", () -> checkoutController.refreshPanel());
        panelActions.put("Purchases", () -> purchasesController.refreshPanel());
        panelActions.put("Browse.Phones", () -> {
            browseController.setBrowseProducts(model.getPhoneProducts());
            browseController.refreshPanel();
        });
        panelActions.put("Browse.Laptops", () -> {
            browseController.setBrowseProducts(model.getLaptopProducts());
            browseController.refreshPanel();
        });
    }
    
    //Needs method to update ProductInfoPanel from BrowsePanel via shared MasterController
    public void updatePip(Product p) {
        pipController.setProductInfo(p);
    }
    
    //All quitting from a single method instead of individual system exit calls, in case I want to add more quit functionality later
    public void globalQuit() {
        System.exit(0);
    }
    
    //Change user greeting on login
    public void setActiveUserGreeting() {
        homeController.setActiveUser();
    }
    
    public void showPanel(String panelName) {
        //Run action if panel has one
        if (panelActions.get(panelName) != null) {
            panelActions.get(panelName).run();
        }
        
        //Change phones/laptop browse to just browse
        if (panelName.startsWith("Browse.")) {
            panelName = "Browse";
        }

        frame.setActivePanel(panelName);
    }
}
