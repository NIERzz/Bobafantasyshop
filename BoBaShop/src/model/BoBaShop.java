package model;

//import Exception.ExceedMaxCapacityException;
import Account.Account;
import Exception.AuthFailedException;
import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import dbaccess.Stock;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//import dbaccess.Stock;
public class BoBaShop {

    private String name;
//    private CustomerAccount[] customers;
    private ArrayList<CustomerAccount> customers;
    private StaffAccount staff;
    private Stock stock;

    public BoBaShop(String name, StaffAccount staff) {
        this.name = name;
        this.stock = new Stock(300);
        this.customers = new ArrayList<>();
        this.staff = staff;
    }
    
    public void addCustomer(CustomerAccount ca){
        customers.add(ca);
    }
    
    public void topUp(CustomerAccount ca, int amount){
        for (CustomerAccount customer : customers) {
            if(customer.equals(ca)){
                customer.topupAccMoney(amount);
            }
        }
    }
    
    public boolean checkUsername(String username){
        for (Account customer : customers) {
            if(customer.getId().equals(username)){
                return true;
            }
        }
        return false;
    }

//    public void addNewProduct(Product p) {
//            stock.insertProduct(p);
//    }
    public void addNewProduct(StaffAccount staff, Product p) throws AuthFailedException {
        if (auth(staff)) {
            stock.insertProduct(p);
        } else {
            throw new AuthFailedException("Staff account doesn't match");
        }
    }
    
    public void removeProductFromStock(int id){
        stock.removeProduct(id);
    }

    public void showMenu() {
        stock.showAll();
    }
    
    public Product getProductFromStock(int id){
        return stock.getProductById(id);
    }

    public void updateStock(int id, int amount){
            try {
                stock.update(id, amount);
            } catch (NEIAException ex) {
                System.out.println(ex);
            }
    }
//    public void updateStock(StaffAccount staff, int id, int amount) throws AuthFailedException {
//        if (auth(staff)) {
//            try {
//                stock.update(id, amount);
//            } catch (NEIAException ex) {
//                System.out.println(ex);
//            }
//        } else {
//            throw new AuthFailedException("Staff account doesn't match");
//        }
//    }

    public void restock(int id, int amount) {
            try {
                stock.restock(id, amount);
            } catch (ExceedMaxCapacityException ex) {
                System.out.println("Can't restock because: " + ex.getMessage());
            }
    }
//    public void restock(StaffAccount staff, int id, int amount) throws AuthFailedException {
//        if (auth(staff)) {
//            try {
//                stock.restock(id, amount);
//            } catch (ExceedMaxCapacityException ex) {
//                System.out.println("Can't restock because: " + ex.getMessage());
//            }
//        } else {
//            throw new AuthFailedException("Staff account doesn't match");
//        }
//    }

    public boolean isStockFull() {
        return stock.isFull();
    }

    private boolean auth(StaffAccount staff) {
        return this.staff.equals(staff);
    }

}
