
import dbaccess.CustInfomation;
import model.Beverage;
import model.BoBaShop;
import model.CustomerAccount;
import model.Dessert;
import model.Person;
import model.Product;
import model.StaffAccount;


public class Test {
    public static void main(String[] args) {
//        CustInfomation cust = new CustInfomation();
//        cust.createTable();
//        insertData();
        BoBaShop boba = new BoBaShop("boba", new StaffAccount("11","111", new Person("123", "123", "123")));
        

        Product p01 = new Beverage(30, "Kratom");
        Product p02 = new Beverage(20, "Papzi");
        Product p03 = new Beverage(450, "Nam123");
        Product p04 = new Beverage(1, "Nam");
        Product p05 = new Dessert(99, "Chocolate cake");
        Product p06 = new Dessert(999, "Pineapple pie");
        Product p07 = new Beverage(666, "Oishi");
        Product p08 = new Dessert(999, "LardNhar");
        Product p09 = new Beverage(10, "Lactazoi 10 Bath");
        
        
        
        boba.addNewProduct(p01);
        boba.addNewProduct(p02);
        boba.addNewProduct(p03);
        boba.addNewProduct(p04);
        boba.addNewProduct(p05);
        boba.addNewProduct(p06);
        boba.addNewProduct(p07);
        boba.addNewProduct(p08);
        boba.addNewProduct(p09);
        boba.getMenu();
        boba.restock(1, 30);
        boba.restock(2, 40);
        boba.restock(3, 90);
        boba.restock(4, 80);
        boba.restock(5, 15);
        boba.restock(6, 22);
        boba.restock(7, 19);
        boba.restock(8, 3);
        boba.restock(9, 1);
        System.out.println("===== Menu =====");
        boba.getMenu();
        
        boba.removeProductFromStock(2);
        boba.removeProductFromStock(7);

        System.out.println("");
        boba.getMenu();
        System.out.println("");
        
                boba.addNewProduct(p06);
        boba.addNewProduct(p02);
        
        boba.getMenu();
        
        
//        System.out.println(p04.getClass().getSimpleName());
//        System.out.println("");
//        System.out.println("");
        

//        System.out.println("===== Update decrease "+p04.getName()+" by 30 =====");
//        boba.updateStock(4, 30);
//        System.out.println("==== After Update =====");
//        boba.showMenu();
//        System.out.println("==== Restock  by 100 =====");
//        boba.restock(4, 100);
//        boba.showMenu();
//        System.out.println("==== Restock  by 999 =====");
//        boba.restock(4, 999);
//        boba.showMenu();
//        System.out.println("==== Restock  by 51 =====");
//        boba.restock(4, 51);
//        boba.showMenu();
//        
//        

//        System.out.println(boba.getProduct(8));
//        Product temp = boba.getProduct(8);
//        System.out.println(temp);
//        if(temp==null)System.out.println("null");
    }

    

   
}
