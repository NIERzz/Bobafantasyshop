
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
import model.CustomerAccount;
import model.Person;
import model.StaffAccount;

public class App {

    static int select;
    static Scanner input = new Scanner(System.in);
    static String menu = "Menu:\n"
            + "1. sign-in\n"
            + "2. sign-up \n"
            + "3. exits \n"
            + "Select menu: ";
    private static boolean bool = true;

    public static void main(String[] args) {
        CustInfomation custInfomation = new CustInfomation();
        custInfomation.createTable();
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
        System.out.println("Thank u, See you");
    }

    public static void signin() {
        System.out.println("WELCOME -TO- SIGN-IN");
        CheckUsernameForSignIn();
    }

    public static void signup() {
        System.out.println("WELCOME -TO- SIGN-UP");
        CheckUsernameForSignUp();
    }

    public static int menu() {
        System.out.print("\n-----------WELCOME2BOBASHOP-------------\n");
        System.out.print(menu);
        select = input.nextInt();
        return select;
    }

    public static void CheckUsernameForSignIn() {
        boolean check;
        do {
            check = false;
            System.out.println("Enter your username: ");
            String usn = input.next();
            System.out.println("Enter your password: ");
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
            }
        } while (!check);
    }

    public static void admin() {
//        createshop name staff(username,pass,new Person(name,email,phone)) max_capacity
//        logout
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

    public static String CheckUsernameForSignUp2() {
        String username, name, firstname, lastname, phone, email;
        boolean checkTemp;
        do {
            checkTemp = false;
            System.out.println("enter username");
            username = input.next();
            checkTemp = XXX.checkUsername(username);
        } while (!checkTemp);

        System.out.println("Choose a password");
        String passw = input.next();
        System.out.println("Enter first name");
        firstname = input.nextLine();
        System.out.println("Enter last name");
        lastname = input.nextLine();
        System.out.println("Enter your phone numbers");
        phone = input.nextLine();
        System.out.println("Enter your email");
        email = input.nextLine();
        name = (firstname + " " + lastname);
        String SUBSCRIBED = AccountStatus.SUBSCRIBED.toString();

        System.out.println("Sign up succesfully added");

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
