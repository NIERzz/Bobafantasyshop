/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Exception.NotEnoughMoneyException;
import Account.Account;
import Exception.NoProductException;

/**
 *
 * @author User
 */
public class CustomerAccount extends Account {

    private int accMoney;
    private OrderList cusCart;
    private boolean status;

    public CustomerAccount(String id, String password, Person person) {
        super(id, password, person);
        this.status = true;
        cusCart = new OrderList(this);
    }

    //Shows the price of the product that the customer bought.
    public int getTotalPrice() {
        return cusCart.sumOfPrice();
    }

    //Shows money in customer account.
    public int getAccmoney() {
        return accMoney;
    }

    //Add product to cart.
    public void addToCart(OrderedProduct op) {
        cusCart.add(op);
    }

    //Remove product in cart.
    public void removeProduct(int id) throws NoProductException {
        cusCart.remove(id);
    }

    //Show all products that the customer has ordered.
    public GeneralList<OrderedProduct> printCartItems() throws NoProductException {
        if (cusCart.items == null) {
            throw new NoProductException("Nothing in cart.");
        }
        GeneralList<OrderedProduct> prod = new GeneralList<>();
        for (int i = 0; i < cusCart.items.size(); i++) {
            prod.add(cusCart.items.get(i));
        }
        return prod;
    }

    //Top up money to customer account
    public void topupAccMoney(int money) {
        if (status) {
            this.accMoney += money;
        }
    }

    //make payment.
    public boolean makePayment() throws NotEnoughMoneyException {
        if(this.accMoney < getTotalPrice()){
            return false;
        }
        accMoney -= getTotalPrice();
        /*
            for making log file.
        */
        
        
        return true;
    }

}
