/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OrderList {
    ArrayList<Product> Items = new ArrayList<Product>();
    private CustomerAccount CA;

    public OrderList(CustomerAccount CA) {
        this.CA = CA;
    }

    public void add(Product product) {
       Items.add(product);
    }

    public void remove(Product prod) {
       Items.remove(prod);
    }

    public ArrayList<Product> getItems() {
        return Items;
    }
    
 
}