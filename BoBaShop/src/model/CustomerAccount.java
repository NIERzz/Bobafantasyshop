/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Account.Account;
import Exception.NoProduct;
import java.util.List;

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

    public Product getProduct(String menu) {
        Product product = null;
        List<Product> Stock = new Stock().getProducts();
        for (Product prod : Stock) {
            if (prod.getName().equals(menu)) {
                product = prod;
                break;
            }
        }
        return product;
    }

    public void addToCart(Product product) {
        cusCart.add(product);
    }

    public void removeProduct(String menu) {
        Product prod = getProduct(menu);
        cusCart.remove(prod);
    }

    public GeneralList printCartItems() throws NoProduct{
        if (cusCart.Items==null) {
           throw new NoProduct("Nothing in cart ");
        }
        GeneralList<Product> prod= new GeneralList<>();
        for (int i = 0; i < cusCart.Items.size(); i++) {
                prod.add(cusCart.Items.get(i));
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

    public boolean MakePayment() throws NullPointerException, NoProduct{
        if (status) {
            printCartItems();
        }
        return false;
    }

}
