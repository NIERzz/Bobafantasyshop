/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Account.Account;
import Exception.NoProduct;

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

//    public void removeProduct(String menu) {
//        cusCart.remove(menu);
//    }

    public GeneralList printCartItems() throws NoProduct {
        if (cusCart.items == null) {
            throw new NoProduct("Nothing in cart ");
        }
        GeneralList<Product> prod = new GeneralList<>();
        for (int i = 0; i < cusCart.items.size(); i++) {
//            prod.add(cusCart.items.get(i));
        }
        return prod;
    }

    public void topupAccMoney(int money) {
        if (status) {
            this.accMoney += money;
        }
    }

    public boolean unsubscribe1() {
        super.unsubscribe();
        this.status = false;
        return true;
    }

    public boolean makePayment() {
        
        return false;
    }

}
