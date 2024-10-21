/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Views;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author flynn
 */
public class LoginPanel extends JPanel {
    JLabel title = new JLabel("Welcome to the store.");
    JLabel title2 = new JLabel("Enter a username, then submit to log in or sign up.");
    JTextField username = new JTextField();
    JButton quitButton = new JButton("Quit");
    JButton submitButton = new JButton("Submit");
    
    public String getUsernameText() {
        return this.username.getText();
    }
    
    public JButton getSubmitButton() {
        return this.submitButton;
    }
    
    public JButton getQuitButton() {
        return this.quitButton;
    }
    
    public LoginPanel() {
        setLayout(new GridLayout(0, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(title);
        add(title2);
        add(username);
        add(submitButton);
        add(quitButton);
    }
}
