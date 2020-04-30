package model;

//import Exception.ExceedMaxCapacityException;
import Exception.AuthFailedException;
import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import dbaccess.Stock;
import java.util.logging.Level;
import java.util.logging.Logger;

//import dbaccess.Stock;
public class BoBaShop {

    private String name;
    private CustomerAccount[] customers;
    private StaffAccount staff;
    private Stock stock;

    public BoBaShop(String name) {
        this.name = name;
        this.stock = new Stock(300);
    }

    public void addNewMenu(StaffAccount staff, Product p) throws AuthFailedException {
        if (auth(staff)) {
            stock.insertProduct(p);
        } else {
            throw new AuthFailedException("Staff account doesn't match");
        }

    }

    public void showMenu() {
        stock.showAll();
    }

    public void updateStock(StaffAccount staff, int id, int amount) throws AuthFailedException {
        if (auth(staff)) {
            try {
                stock.update(id, amount);
            } catch (NEIAException ex) {
                System.out.println(ex);
            }
        } else {
            throw new AuthFailedException("Staff account doesn't match");
        }
    }

    public void restock(StaffAccount staff,int id, int amount) throws AuthFailedException {
        if (auth(staff)) {
        try {
            stock.restock(id, amount);
        } catch (ExceedMaxCapacityException ex) {
            System.out.println("Can't restock because: " + ex.getMessage());
        }
        } else {
            throw new AuthFailedException("Staff account doesn't match");
        }
    }

    public boolean isStockFull() {
        return stock.isFull();
    }

    private boolean auth(StaffAccount staff) {
        return this.staff.equals(staff);
    }
}
