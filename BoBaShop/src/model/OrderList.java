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
    ArrayList<OrderedProduct> items = new ArrayList<OrderedProduct>();
    private CustomerAccount CA;
    private OrderedProduct product;
   
    
    public OrderList(CustomerAccount CA) {
        this.CA = CA;
    }

    public void add(OrderedProduct product) {
       items.add(product);
    }

    public OrderedProduct checkIdProduct(int id){
        for (OrderedProduct item : items) {
            if (item.getId()==id) {
                return item;
            }
        }
    return null;
    }
    
    public void remove(Product prod) {
       items.remove(prod);
    }

    public ArrayList<OrderedProduct> getItems() {
        return items;
    }
    
    
}