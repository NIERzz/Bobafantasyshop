
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Beverage;
import model.BoBaShop;
import model.Dessert;
import model.Product;


public class View {
    static int select;
    static Scanner user = new Scanner(System.in);
    static Scanner choose = new Scanner(System.in);
    static Scanner menuselection = new Scanner(System.in);
    private static String username;
    static Boolean dup;
    static String menu = " =====   Select Your Menu:   ===== \n"
            + "           1. • Sign-in             \n"
            + "           2. • Sign-up             \n"
            + "           3. • Exits               \n"
            + " ==================================  ";     
    private static boolean bool = true;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
        do {
            select = menu();
            switch (select) {
                case 1://sign-in
                    signin();
                    break;
                case 2://sign-up
                    signup();
                    break;
            }
        } while (select != 3);
         System.out.println("********************************************************");
         System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
         System.out.println("********************************************************");
    }

    public static void signin() {
        CheckUsernameForSignIn();
    }

    public static void signup() {
        System.out.println("******   Register your ID   ******");
        CheckUsernameForSignUp();
    }

    public static int menu() {
         System.out.println("*******************************************");
         System.out.println("*********   WELCOME TO BOBASHOP   *********");
         System.out.println("*******************************************");
         System.out.println(menu);
         System.out.print(" • Enter your menu: ");
        select = input.nextInt();
        return select;
    }

    public static String CheckUsernameForSignIn() {
        try {
            System.out.print("Enter your username: ");
            String testingsignin = user.next();
            System.out.print("Enter your password: ");
            String pass2 = user.next();
            FileReader fr = new FileReader("password.txt");
            BufferedReader br = new BufferedReader(fr);
            String stringRead = br.readLine();

            while (stringRead != null) {
                String[] tempArray = stringRead.split(",");
                if (testingsignin.equals(tempArray[0]) && pass2.equals(tempArray[1])) {

                }
                stringRead = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            System.out.println("There is a input/output error that was caught");
        }

        return username;
    }

    public static String CheckUsernameForSignUp() {
        dup = true;
        System.out.print("Enter your username: ");
        username = choose.next();
        try {
            FileReader duplicate = new FileReader("Users.txt");
            BufferedReader duplicate1 = new BufferedReader(duplicate);
            String stringread = duplicate1.readLine();
            while (stringread != null) {
                if (username.equals(stringread)) {
                    dup = false;
                }
                stringread = duplicate1.readLine();
            }

            duplicate1.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Unable to find file!");
        } catch (IOException ioe) {
            System.out.println("There is a input/output error that was caught");
        }

        if (dup) {
            try {
                FileWriter customer1 = new FileWriter("Users.txt", true);
                BufferedWriter cust1 = new BufferedWriter(customer1);
                cust1.newLine();
                cust1.write(username);
                cust1.close();
                System.out.print("Enter your password: ");
                String passw = choose.next();
                FileWriter pass = new FileWriter("password.txt", true);
                BufferedWriter pass1 = new BufferedWriter(pass);
                pass1.newLine();
                pass1.write(username + "," + passw);
                pass1.close();
                System.out.println("Username succesfully added");
            } catch (FileNotFoundException fnfe) {
                System.out.println("Unable to find file!");
            } catch (IOException ioe) {
                System.out.println("There is a input/output error that was caught");
            }
        } else {
            System.out.println("**** This username already in system ****");
        }

        return username;
    }
}

//    public static void main(String[] args) {
//        createTable();
//        insertData();
//        BoBaShop boba = new BoBaShop("BobaFamily");
//        System.out.println(boba.isStockFull());
//        Product p01 = new Beverage(30, "Kratom");
//        Product p02 = new Beverage(20, "Papzi");
//        Product p03 = new Beverage(9999, "Namjai");
//        Product p04 = new Beverage(1, "Nam");
//        Product p05 = new Dessert(99, "Chocolate cake");
//        Product p06 = new Dessert(999, "Cream pie");
//        Product p07 = new Beverage(666, "Grandma's saliva");
//        Product p08 = new Dessert(999, "LardNhar");
//        Product p09 = new Beverage(10, "Lactazoi 10 Bath");
//        
//        boba.addNewMenu(p01);
//        boba.addNewMenu(p02);
//        boba.addNewMenu(p03);
//        boba.addNewMenu(p04);
//        boba.addNewMenu(p05);
//        boba.addNewMenu(p06);
//        boba.addNewMenu(p07);
//        boba.addNewMenu(p08);
//        boba.addNewMenu(p09);
//        boba.showMenu();
//        boba.restock(1, 30);
//        boba.restock(2, 40);
//        boba.restock(3, 90);
//        boba.restock(4, 80);
//        boba.restock(5, 15);
//        boba.restock(6, 22);
//        boba.restock(7, 19);
//        boba.restock(8, 3);
//        boba.restock(9, 1);
        
        
//        System.out.println(p04.getClass().getSimpleName());
//        System.out.println("");
//        System.out.println("");
        
//        System.out.println("===== Menu =====");
//        boba.showMenu();
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
        
 //   }

    

   
//}

