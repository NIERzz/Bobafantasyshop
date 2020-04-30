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

    private int accmoney;
    private Cart cuscart;
    private boolean status;

    public CustomerAccount(String id, String password, Person person) {
        super(id, password, person);
        this.status = true;
        cuscart = new Cart(this);
    }

    public int CheckMoney() {
        return accmoney;
    }

    public int getAccmoney() {
        return accmoney;
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
        cuscart.Items.add(product);
    }

    public void removeProduct(String menu) {
        Product prod = getProduct(menu);
        cuscart.Items.remove(prod);
    }

    public void printCartItems() throws NoProduct{
//        int totle=0;
        if (cuscart.Items==null) {
           throw new NoProduct("Nothing in cart ");
        }
        for (Product prod : cuscart.Items) {
            System.out.println(prod.getName() + +prod.getPrice());
        }
//            totle += prod.getPrice();
//                    }
//            System.out.println("Total price is : "+totle);
        
    }

    public void TopupAccmoney(int money) {
        if (status) {
            this.accmoney += money;
        }
    }

    public boolean Unsubscribe1() {
        super.Unsubscribe();
        this.status = false;
        return true;
    }

    public boolean MakePayment() throws NullPointerException, NoProduct{
        if (status) {
            printCartItems();
            BoBaShop b1 = new BoBaShop();
        }
        return false;
    }

}
