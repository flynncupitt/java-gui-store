/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2.Product;
/**
 *
 * @author flynn
 */
public class LaptopProduct extends Product {
    private double screenSize;
    
    public LaptopProduct(int sku, String name, double price, double screenSize) {
        super(sku, name, price);
        this.screenSize = screenSize;
    }
    
    public double getScreenSize() {
        return this.screenSize;
    }
    
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
    
    @Override
    public void printProductDetails() {
        System.out.println(String.format("%s - %.1finch: $%.2f", super.getName(), screenSize, super.getPrice()));
    }
}
