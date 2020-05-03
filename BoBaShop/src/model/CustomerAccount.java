/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public int CheckMoney() {
        return accMoney;
    }

    public int getAccmoney() {
        return accMoney;
    }


    public void addToCart(OrderedProduct op) {
        cusCart.add(op);
    }

    public void removeProduct(int id) throws NoProductException {
        cusCart.remove(id);
    }

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

    public void topupAccMoney(int money) {
        if (status) {
            this.accMoney += money;
        }
    }

    public boolean makePayment() {
        return false;
    }

}
