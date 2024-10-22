/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import Database.DatabaseManager;

/**
 *
 * @author flynn
 */
public abstract class BaseAbstractController<E> {
    protected MasterController master;
    protected DatabaseManager model = DatabaseManager.getInstance();
    protected E view;
    
    public BaseAbstractController(MasterController master, E view) {
        this.master = master;
        this.view = view;
    }
    
    public abstract void refreshPanel();
}
