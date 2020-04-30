/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Cart {
    List<Product> Items = new ArrayList<Product>();
    private CustomerAccount CA;

    public Cart(CustomerAccount CA) {
        this.CA = CA;
    }
}