
package model;

//import Exception.ExceedMaxCapacityException;
//import dbaccess.Stock;

public class BoBaShop {
private String name;
//    private CustomerAccount[] customers;
//    private StaffAccount staff;
    private Stock stock;

    public BoBaShop(String name) {
        this.name = name;
//        this.stock = new Stock(300);
    }
    
    public void addNewMenu(Product p){
//        stock.insertProduct(p);
    }
    
    public void addNewMenu(Product p,int amount){
//        stock.insertProduct(p);
//        restock(p, amount);
    }
    
    public void showMenu(){
//        stock.showAll();
    }
    
    public void updateStock(Product product, int amount){
//        stock.update(product, amount);
    }
    
    public void restock(Product product, int amount){
//        try {
//            stock.restock(product, amount);
//        } catch (ExceedMaxCapacityException ex) {
//            System.out.println("Can't restock because: "+ex.getMessage());
//        }
    }
}
