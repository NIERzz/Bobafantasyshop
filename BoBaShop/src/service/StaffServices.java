
package service;

import model.account.Account;
import Exception.ExceedMaxCapacityException;
import Exception.NoProductException;
import model.product.Product;

public interface StaffServices {
    public void addNewProduct(Product p);
    public void removeProductFromStock(int id) throws NoProductException;
    public void restock(int id, int amount) throws ExceedMaxCapacityException, NoProductException;
    public boolean blacklistCustomer(Account ca);
    
}
