/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import Exception.NoProductException;
import java.util.ArrayList;
import model.product.OrderedProduct;

/**
 *
 * @author User
 */
public class OrderList {

    ArrayList<OrderedProduct> items = new ArrayList<>(); //Arraylist of ordered products.
    private CustomerAccount CA; //Cart owner.
    private OrderedProduct product; //Ordered product.

    public OrderList(CustomerAccount CA) {
        this.CA = CA;
    }

    //Add product in item.
    public void add(OrderedProduct product) {
        //If the added product already in the orderList;
        if (checkIdProduct(product.getId()) != null) {
            OrderedProduct temp;
            temp = checkIdProduct(product.getId());
            temp.setAmount((temp.getAmount() + product.getAmount()));
        } else {
            items.add(product);
        }
    }

    public void clear() {
        items = new ArrayList<>();
    }

    //Check product by ID of product.
    public OrderedProduct checkIdProduct(int id) {
        for (OrderedProduct item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    //Return Total price of product
    public int sumOfPrice() {
        int sum = 0;
        for (OrderedProduct item : items) {
            sum += (item.getAmount() * item.getProduct().getPrice());
        }
        return sum;
    }

    //Remove product in items by ID
    public void remove(int id) throws NoProductException {
        for (OrderedProduct item : items) {
            if (item.getId() == id) {
                items.remove(item);
                return;
            }
        }
        throw new NoProductException("This product id doesn't in the order.");
    }

    //Return all ordered product in items
    public ArrayList<OrderedProduct> getItems() {
        return items;
    }

}
