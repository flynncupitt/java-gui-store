/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.assignment2;

import Database.DatabaseManager;
import com.mycompany.assignment2.Views.BrowsePanel;
import com.mycompany.assignment2.Views.CartPanel;
import com.mycompany.assignment2.Views.CheckoutPanel;
import com.mycompany.assignment2.Views.HomePanel;
import com.mycompany.assignment2.Views.LoginPanel;
import com.mycompany.assignment2.Views.ProductInfoPanel;
import com.mycompany.assignment2.Views.PurchasesPanel;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
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
    private HomePanel homePanel;
    private ProductInfoPanel productInfoPanel;
    private LoginPanel loginPanel;
    private Map<String, JPanel> panels = new HashMap();

    public MainFrame() {
        setTitle("Shopping System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Instantiate panels
        homePanel = new HomePanel();
        productInfoPanel = new ProductInfoPanel();
        browsePanel = new BrowsePanel(productInfoPanel);
        cartPanel = new CartPanel();
        checkoutPanel = new CheckoutPanel();
        purchasesPanel = new PurchasesPanel();
        loginPanel = new LoginPanel();

        //Add panels to frame
        mainPanel.add(homePanel, "Home");
        mainPanel.add(browsePanel, "Browse");
        mainPanel.add(productInfoPanel, "ProductInfo");
        mainPanel.add(cartPanel, "Cart");
        mainPanel.add(checkoutPanel, "Checkout");
        mainPanel.add(purchasesPanel, "Purchases");
        mainPanel.add(loginPanel, "Login");
        add(mainPanel);

        //Add all panels to map for showPanel
        panels.put("Home", homePanel);
        panels.put("Browse", browsePanel);
        panels.put("ProductInfo", productInfoPanel);
        panels.put("Cart", cartPanel);
        panels.put("Purchases", purchasesPanel);
        panels.put("Checkout", checkoutPanel);
        panels.put("Login", loginPanel);

        cardLayout.show(mainPanel, "Login");
    }

    public void setActivePanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public JPanel getPanel(String panelName) {
        return panels.get(panelName);
    }
}
