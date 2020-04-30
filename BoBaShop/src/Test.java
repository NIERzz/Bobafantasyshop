
import dbaccess.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Beverage;
import model.BoBaShop;
import model.Product;


public class Test {
    public static void main(String[] args) {
//        createTable();
//        insertData();
        BoBaShop boba = new BoBaShop("BobaFamily");

        Product p01 = new Beverage(30, "Kratom");
        Product p02 = new Beverage(20, "Papzi");
        Product p03 = new Beverage(9999, "Namjai");
        Product p04 = new Beverage(1, "Nam");
        
        boba.addNewMenu(p01, 20);
        boba.addNewMenu(p02, 40);
        
        boba.showMenu();
        boba.addNewMenu(p03, 90);
        boba.addNewMenu(p04, 99);
        System.out.println("===== Menu =====");
        boba.showMenu();
        System.out.println("===== Update decrease "+p04.getName()+" by 30 =====");
        boba.updateStock(p04, 30);
        System.out.println("==== After Update =====");
        boba.showMenu();
        System.out.println("==== Restock  by 100 =====");
        boba.restock(p04, 100);
        boba.showMenu();
        System.out.println("==== Restock  by 999 =====");
        boba.restock(p04, 999);
        boba.showMenu();
        System.out.println("==== Restock  by 51 =====");
        boba.restock(p04, 51);
        boba.showMenu();
        
        
        
    }

    

   
}
