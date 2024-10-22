/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.DatabaseManager;
import com.mycompany.assignment2.MainFrame;
import com.mycompany.assignment2.Product.Product;
import com.mycompany.assignment2.Views.BrowsePanel;
import com.mycompany.assignment2.Views.CartPanel;
import com.mycompany.assignment2.Views.CheckoutPanel;
import com.mycompany.assignment2.Views.HomePanel;
import com.mycompany.assignment2.Views.LoginPanel;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import com.mycompany.assignment2.Views.PurchasesPanel;

/**
 *
 * @author flynn
 */
public class MasterController {
    MainFrame frame;
    DatabaseManager model = DatabaseManager.getInstance();
    
    CartController cartController;
    HomeController homeController;
    CheckoutController checkoutController;
    BrowseController  browseController;
    PurchasesController purchasesController;
    PipController pipController;
    LoginController loginController;
    
    public MasterController(MainFrame frame) {
        this.frame = frame;
        cartController = new CartController(this, (CartPanel) frame.getPanel("Cart"));
        homeController = new HomeController(this, (HomePanel) frame.getPanel("Home"));
        checkoutController = new CheckoutController(this, (CheckoutPanel) frame.getPanel("Checkout"));
        browseController = new BrowseController(this, (BrowsePanel) frame.getPanel("Browse"));
        purchasesController = new PurchasesController(this, (PurchasesPanel) frame.getPanel("Purchases"));
        pipController = new PipController(this, (ProductInfoPanel) frame.getPanel("ProductInfo"));
        loginController = new LoginController(this, (LoginPanel) frame.getPanel("Login"));
    }
    
    public void updatePip(Product p) {
        pipController.setProductInfo(p);
    }
    
    public void globalQuit() {
        System.exit(0);
    }
    
    public void setActiveUserGreeting() {
        homeController.setActiveUser();
    }
    public void showPanel(String panelName) {
        switch (panelName) {
            //Only needs case if other function gets called, else default
            case "Cart":
                cartController.refreshCartPanel();
                break;
            case "Checkout":
                checkoutController.refreshCheckoutPanel();
                break;
            case "Purchases":
                purchasesController.refreshPurchasesPanel();
                break;
            case "Browse.Phones":
                browseController.refreshBrowsePanel(model.getPhoneProducts());
                panelName = "Browse";
                break;
            case "Browse.Laptops":
                browseController.refreshBrowsePanel(model.getLaptopProducts());
                panelName = "Browse";
                break;                
        }
        frame.setActivePanel(panelName);
        
    }
}
