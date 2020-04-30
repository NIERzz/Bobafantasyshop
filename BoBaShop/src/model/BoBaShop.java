
package model;

//import Exception.ExceedMaxCapacityException;

import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import dbaccess.Stock;
import java.util.logging.Level;
import java.util.logging.Logger;

//import dbaccess.Stock;

public class BoBaShop {
private String name;
//    private CustomerAccount[] customers;
//    private StaffAccount staff;
    private Stock stock;

    public BoBaShop(String name) {
        this.name = name;
        this.stock = new Stock(300);
    }
    
    public void addNewMenu(Product p){
        stock.insertProduct(p);
    }
    
    public void showMenu(){
        stock.showAll();
    }
    
    public void updateStock(int id, int amount){
    try {
        stock.update(id, amount);
    } catch (NEIAException ex) {
        System.out.println(ex);
    }
    }
    
    public void restock(int id, int amount){
        try {
            stock.restock(id, amount);
        } catch (ExceedMaxCapacityException ex) {
            System.out.println("Can't restock because: "+ex.getMessage());
        }
    }
}
