/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import com.mycompany.assignment2.Controllers.MasterController;

/**
 *
 * @author flynn
 */
public class ShoppingProgram {
    public static void main(String[] args) {
        MainFrame view = new MainFrame();
        MasterController controller = new MasterController(view);
        view.setVisible(true);
    }
    
}
