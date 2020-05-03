/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exception.NoProductException;
import java.util.ArrayList;
import dbaccess.Stock;
/**
 *
 * @author User
 */
public class OrderList {
    ArrayList<OrderedProduct> items = new ArrayList<>();
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
    
    public int sumOfPrice(){
        int sum = 0;
        for (OrderedProduct item : items) {
            sum += (item.getAmount() * item.getProduct().getPrice());
        }
        return sum;
    }
    
    public void remove(int id) throws NoProductException {
        for (OrderedProduct item : items) {
            if(item.getId()==id){
                items.remove(item);
                return;
            }
        }
        throw new NoProductException("This product id doesn't in the order.");
    }

    public ArrayList<OrderedProduct> getItems() {
        return items;
    }
    
    
}