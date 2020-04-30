
import model.Beverage;
import model.BoBaShop;
import model.Dessert;
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
        Product p05 = new Dessert(99, "Chocolate cake");
        Product p06 = new Dessert(999, "Cream pie");
        Product p07 = new Beverage(666, "Grandma's saliva");
        Product p08 = new Dessert(999, "LardNhar");
        Product p09 = new Beverage(10, "Lactazoi 10 Bath");
        
        boba.addNewMenu(p01);
        boba.addNewMenu(p02);
        boba.addNewMenu(p03);
        boba.addNewMenu(p04);
        boba.addNewMenu(p05);
        boba.addNewMenu(p06);
        boba.addNewMenu(p07);
        boba.addNewMenu(p08);
        boba.addNewMenu(p09);
        boba.showMenu();
        boba.restock(1, 30);
        boba.restock(2, 40);
        boba.restock(3, 90);
        boba.restock(4, 80);
        boba.restock(5, 15);
        boba.restock(6, 22);
        boba.restock(7, 19);
        boba.restock(8, 3);
        boba.restock(9, 1);
        
        
//        System.out.println(p04.getClass().getSimpleName());
//        System.out.println("");
//        System.out.println("");
        
        System.out.println("===== Menu =====");
        boba.showMenu();
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
        
    }

    

   
}
