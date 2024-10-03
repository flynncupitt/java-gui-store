/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Product;

/**
 *
 * @author flynn
 */
public class PhoneProduct extends Product {
    private String colour;
    
    public PhoneProduct(int sku, String name, double price, String colour) {
        super(sku, name, price);
        this.colour = colour;
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public void printProductDetails() {
        System.out.println(String.format("%s (%s): $%.2f", super.getName(), colour, super.getPrice()));
    }
    
    
}
