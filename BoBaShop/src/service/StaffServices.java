
package service;

import Account.Account;
import Exception.ExceedMaxCapacityException;
import Exception.NoProductException;
import model.Product;

public interface StaffServices {
    public void addNewProduct(Product p);
    public void removeProductFromStock(int id) throws NoProductException;
    public void restock(int id, int amount) throws ExceedMaxCapacityException;
    public boolean blacklistCustomer(Account ca);
    
}
