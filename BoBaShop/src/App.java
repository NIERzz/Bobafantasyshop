
import Account.AccountStatus;
import dbaccess.CustInfomation;
import dbaccess.DBConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.BoBaShop;
import model.CustomerAccount;
import model.Person;
import model.StaffAccount;

public class App {

    static int select;
    static Scanner input = new Scanner(System.in);
    static String menu = " =====   Select Your Menu:   ===== \n"
            + "           1. • Sign-in •            \n"
            + "           2. • Sign-up •            \n"
            + "           3. • Exits   •            \n"
            + " ==================================  ";
    
    
    public static void main(String[] args) {
        CustInfomation custInfomation = new CustInfomation();
        custInfomation.createTable();
        do {
            select = menu();
            switch (select) {
                case 1://sign-in
                    login();
                    break;
                case 2://sign-up
                    register();

                    break;
            }
        } while (select != 3);
         System.out.println("********************************************************");
         System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
         System.out.println("********************************************************");
    }

    public static void login() {
        System.out.println("******   LOGIN YOUR ACCOUNT   ******");
        CheckUsernameForLogIn();
    }

    public static void register() {
        System.out.println("******   REGISTER YOUR ACCOUNT   ******");
        CheckUsernameForRegistered();
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

    public static void CheckUsernameForLogIn() {
        boolean check;
        do {
            check = false;
            System.out.print("Enter your username: ");
            String usn = input.next();
            System.out.print("Enter your password: ");
            String psw = input.next();

            if (checkAdmin(usn, psw)) {
                admin();
                check = true;
            }
            if (checkStaff(usn, psw)) {
                staff();
                check = true;
            }
            if (checkCustomer(usn, psw) != null) {
                customer(checkCustomer(usn, psw));
                check = true;
            }else {
              System.out.println(" ==== FAILED TO LOGIN! ==== ");
              System.out.println("      PLEASE TRY AGAIN      ");
              System.out.println(" ========================== ");
            }
        } while (!check);
    }

    public static void admin() {
//        createshop name staff(username,pass,new Person(name,email,phone)) max_capacity
//        logout
    }
    public void shopcreate() {
        System.out.println("***************************************");
        System.out.println("*******  CREATE YOUR SHOP NAME  *******");
        System.out.println("***************************************");

        System.out.print("Enter your shop name: ");
        String shopname = input.next();
        System.out.print("Enter maximum capacity of your shop: ");
        int mcp = input.nextInt();
        System.out.println("********************************");
        System.out.println("***** CREATE STAFF ACCOUNT *****");
        System.out.println("********************************");
        System.out.print("Enter your username: ");
        String staffusn = input.next();
        System.out.print("Enter your password: ");
        String staffpsw = input.next();
        System.out.println("Enter your name: ");
        String staffname = input.next();
        System.out.print("Enter your e-mail: ");
        String email = input.next();
        System.out.print("Enter your phone-number: ");
        String phonenum = input.next();
         BoBaShop boba = new BoBaShop(shopname, new StaffAccount(staffusn, staffpsw, new Person(staffname, email, phonenum)),mcp);
       
    
}
    public static void staff() {
//        addproduct
//        removeproduct
//        restock
//      blacklist
    }

    public static void customer(CustomerAccount ca) {
//        topup
//        order
//          pay
    }

    public static String CheckUsernameForRegistered() {
        String username, name, firstname, lastname, phone, email;
        boolean checkTemp;
        do {
            checkTemp = false;
            System.out.print("Create your username: ");
            username = input.next();
            checkTemp = XXX.checkUsername(username);
        } while (!checkTemp);

        System.out.print("Create your password: ");
        String passw = input.next();
        System.out.println("Enter your first name: ");
        firstname = input.nextLine();
        System.out.println("Enter your last name: ");
        lastname = input.nextLine();
        System.out.println("Enter your phone numbers: ");
        phone = input.nextLine();
        System.out.println("Enter your email: ");
        email = input.nextLine();
        name = (firstname + " " + lastname);
        System.out.println(" ****** YOUR ACCOUNT HAS BEEN CREATED SUCCESSFULLY ****** ");
        XXX.addCustomer(new CustomerAccount(username, passw, new Person(name, email, phone)));
    }

    private static boolean checkAdmin(String usn, String psw) {
        return usn.equals("admin") && psw.equals("admin");
    }

    private static boolean checkStaff(String usn, String psw) {
        return XXX.checkStaff(usn, psw);
    }

    private static CustomerAccount checkCustomer(String usn, String psw) {
        return XXX.checkCustomer(usn, psw);
    }
}
