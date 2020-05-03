
package service;

import Account.Account;
import Exception.NEIAException;
import Exception.NoProductException;
import Exception.NotEnoughMoneyException;
import java.io.IOException;
import model.CustomerAccount;
import model.GeneralList;
import model.OrderedProduct;

public interface CustomerServices {
    public boolean topUp(CustomerAccount ca, int amount);
    public boolean order(Account ca, int id, int amount);
    public void makePayment(Account ca) throws NotEnoughMoneyException, NoProductException, NEIAException, IOException;
    public void removeOrderFromList(Account ca, int id) throws NoProductException;
    public void clearOrder(Account ca);
    public GeneralList<OrderedProduct> getOrderList(Account ca) throws NoProductException;
    public int getAccMoney(Account ca);
    public int getTotalPrice(Account ca);
}
