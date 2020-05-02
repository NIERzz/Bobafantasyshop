/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import dbaccess.Stock;
/**
 *
 * @author User
 */
public class OrderList {
    ArrayList<Product> items = new ArrayList<Product>();
    private CustomerAccount CA;
    private Product product;
   
    
    public OrderList(CustomerAccount CA) {
        this.CA = CA;
    }

    public void add(Product product) {
       items.add(product);
    }

    public Product checkIdProduct(int id){
        for (Product Item : items) {
            if (Item.) {
           
                return Item;
            }
        }
    
    
    return null;
    }
    
    public void remove(Product prod) {
       items.remove(prod);
    }

    public ArrayList<Product> getItems() {
        return items;
    }
    
    
}