package model;

//import Exception.ExceedMaxCapacityException;
import Account.Account;
import Account.AccountStatus;
import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import Exception.NoProductException;
import Exception.NotEnoughMoneyException;
import dbaccess.Stock;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import service.CustomerServices;
import service.StaffServices;

public class BoBaShop implements StaffServices, CustomerServices {

    private String name;
    private ArrayList<CustomerAccount> customers;
    private StaffAccount staff;
    private Stock stock;

    public BoBaShop(String name, StaffAccount staff, int max) {
        this.name = name;
        this.stock = new Stock(max);
        this.customers = new ArrayList<>();
        this.staff = staff;
    }

    /////////////////////////////////////
    //////////// CUSTOMER ///////////////
    /////////////////////////////////////
    @Override
    public boolean topUp(CustomerAccount ca, int amount) {
        if (amount <= 0) {
            return false;
        }
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                customer.topupAccMoney(amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean order(Account ca, int id, int amount) {
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                if (stock.getProductById(id) != null) {
                    customer.addToCart(new OrderedProduct(id, amount, stock.getProductById(id)));
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void makePayment(Account ca) throws NotEnoughMoneyException, NoProductException, NEIAException, IOException {
        LocalDateTime timeorder = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");

        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                GeneralList<OrderedProduct> genList = customer.printCartItems();
                // Check whether the product in stock left less than orderlist. 
                for (int i = 0; i < genList.getCount(); i++) {
                    OrderedProduct temp = genList.getItemAt(i);
                    int id = temp.getId();
                    int sAmount = stock.getItemAmountById(id);
                    if (temp.getAmount() > sAmount) {
                        throw new NEIAException("Not Enough Item Available");
                    }
                }
                // Make a payment -- decrease accmoney or throw exception
                if (customer.makePayment()) {
                    // Decrease stock amount
                    for (int i = 0; i < genList.getCount(); i++) {
                        OrderedProduct temp = genList.getItemAt(i);
                        int id = temp.getId();
                        int oAmount = temp.getAmount();
                        stock.update(id, oAmount);
                    }
                    FileWriter fw = new FileWriter("orderLog.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(timeorder.format(format) + " Customer: " + customer.getPerson().getName() + " Order:{");
                    for (int i = 0; i < genList.getCount(); i++) {
                        OrderedProduct temp = genList.getItemAt(i);
                        bw.write("[p:" + temp.getProduct().getName() + " , amount:" + temp.getAmount() + ", price: " + temp.getProduct().getPrice() + "],");
                    }
                    bw.write(" TotalPrice: " + customer.getTotalPrice() + " }");
                    bw.newLine();
                    bw.close();
                } else {
                    throw new NotEnoughMoneyException("Not enough money.");
                }

            }
        }
    }

    @Override
    public void removeOrderFromList(Account ca, int id) throws NoProductException {
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                customer.removeProduct(id);
            }
        }
    }

    @Override
    public void clearOrder(Account ca) {
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                customer.clear();
            }
        }
    }

    @Override
    public GeneralList<OrderedProduct> getOrderList(Account ca) throws NoProductException {
        GeneralList<OrderedProduct> genList = null;
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                genList = customer.printCartItems();
            }
        }
        return genList;
    }

    @Override
    public int getAccMoney(Account ca) {
        int temp = 0;
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                temp = customer.getAccmoney();
            }
        }
        return temp;
    }

    @Override
    public int getTotalPrice(Account ca) {
        int temp = 0;
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                temp = customer.getTotalPrice();
            }
        }
        return temp;
    }

    /////////////////////////////////////
    ////////////// STAFF ////////////////
    /////////////////////////////////////
    @Override
    public void addNewProduct(Product p) {
        stock.insertProduct(p);
    }

    @Override
    public void removeProductFromStock(int id) throws NoProductException {
        stock.removeProduct(id);
    }

    @Override
    public void restock(int id, int amount) throws ExceedMaxCapacityException, NoProductException {
        stock.restock(id, amount);
    }

    @Override
    public boolean blacklistCustomer(Account ca) {
        for (CustomerAccount customer : customers) {
            if (customer.equals(ca)) {
                ca.setStatus(AccountStatus.BLACKLISTED);
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////////
    ////////////// GENERAL /////////////////
    ////////////////////////////////////////
    public void addCustomer(CustomerAccount ca) {
        customers.add(ca);
    }

    public CustomerAccount getCustomerByUsername(String usn) {
        for (CustomerAccount customer : customers) {
            if (customer.getId().equals(usn)) {
                return customer;
            }
        }
        return null;
    }

    public boolean checkUsername(String username) {
        if (username.equals("admin") || username.equals(((Account) staff).getId())) {
            return false;
        }
        for (Account customer : customers) {
            if (customer.getId().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean isStockFull() {
        return stock.isFull();
    }

    public boolean checkStaff(String usn, String psw) {
        return this.staff.getId().equals(usn) && this.staff.getPassword().equals(psw);
    }

    public CustomerAccount checkCustomer(String usn, String psw) {
        for (CustomerAccount customer : customers) {
            if (customer.getId().equals(usn) && customer.getPassword().equals(psw)) {
                return customer;
            }
        }
        return null;
    }

    public Product getProductFromStock(int id) {
        return stock.getProductById(id);
    }

    public GeneralList<OrderedProduct> getMenu() {
        return stock.showAll();
    }

    public void updateStock(int id, int amount) throws NEIAException {
        stock.update(id, amount);
    }

}
