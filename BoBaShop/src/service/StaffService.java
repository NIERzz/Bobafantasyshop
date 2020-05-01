
package service;

import model.Product;
import model.StaffAccount;

public interface StaffService {
    public void addNewMenu(StaffAccount staff, Product p);
    public void restock(StaffAccount staff, int id, int amount);
}
