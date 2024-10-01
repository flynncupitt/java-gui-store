/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.assignment2;

import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author flynn
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private BrowsePanel browsePanel;
    private CartPanel cartPanel;
    private CheckoutPanel checkoutPanel;
    private PurchasesPanel purchasesPanel;

    public MainFrame() {
        setTitle("Shopping System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create CardLayout to switch between different screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the panels for each section
        JPanel homePanel = new HomePanel(this);
        ProductInfoPanel productInfoPanel = new ProductInfoPanel(this);
        browsePanel = new BrowsePanel(this, productInfoPanel);
//        PhonePanel phonePanel = new PhonePanel();
//        LaptopPanel laptopPanel = new LaptopPanel();
        cartPanel = new CartPanel(this);
        checkoutPanel = new CheckoutPanel(this);
        purchasesPanel = new PurchasesPanel(this);
        
//         Add panels to the CardLayout
        mainPanel.add(homePanel, "Home");
         mainPanel.add(browsePanel, "Browse");
         mainPanel.add(productInfoPanel, "ProductInfo");
//        mainPanel.add(phonePanel, "Phones");
//        mainPanel.add(laptopPanel, "Laptops");
        mainPanel.add(cartPanel, "Cart");
        mainPanel.add(checkoutPanel, "Checkout");
        mainPanel.add(purchasesPanel, "Purchases");
//        mainPanel.add(checkoutPanel, "Checkout");

        add(mainPanel);

        // Show the home panel first
        cardLayout.show(mainPanel, "Home");
    }
    
    public void showPanel(String panelName) {
        if(panelName.equals("Cart")) {
            cartPanel.refreshCartPanel();
        } else if(panelName.equals("Checkout")) {
            checkoutPanel.refreshCheckout();
        } else if(panelName.equals("Purchases")) {
            purchasesPanel.refreshPurchases();
        }
        cardLayout.show(mainPanel, panelName);
    }
    
    public void showBrowsePanel(String productType) {
        if(productType.equals("phone")) {
            browsePanel.setProducts(dbManager.getPhoneProducts());
            showPanel("Browse");
        } else if(productType.equals("laptop")) {
            browsePanel.setProducts(dbManager.getLaptopProducts());
            showPanel("Browse");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
