/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Controllers;

import com.mycompany.assignment2.DatabaseManager;

/**
 *
 * @author flynn
 * @param <E>
 */
public class BaseController<E> {
    protected MasterController master;
    protected DatabaseManager model;
    protected E view;
    
    public BaseController(MasterController master, E view) {
        this.master = master;
        this.view = view;
    }
}
