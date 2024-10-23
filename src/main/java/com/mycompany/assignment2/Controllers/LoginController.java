/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.Views.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author flynn
 */
public class LoginController extends BaseController<LoginPanel> {

    public LoginController(MasterController master, LoginPanel view) {
        super(master, view);
        
            view.getQuitButton().addActionListener(e -> master.globalQuit());                    
            
            view.getSubmitButton().addActionListener((ActionEvent e) -> {
                model.userLoginSignup(view.getUsernameText());
                master.setActiveUserGreeting();
                master.showPanel("Home");
        });

    }
    
}
