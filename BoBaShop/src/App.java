
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
    static Scanner user = new Scanner(System.in);
    static Scanner choose = new Scanner(System.in);
    private static String username;
    static Boolean dup;
    static String menu = "Menu:\n"
            + "1. sign-in\n"
            + "2. sign-up \n"
            + "3. exits \n"
            + "Select menu: ";
    private static boolean bool = true;
    static Scanner input = new Scanner(System.in);

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

    public static String CheckUsernameForSignIn() {

        System.out.println("Enter your username: ");
        String usn = user.next();
        System.out.println("Enter your password: ");
        String psw = user.next();
        
        checkAdmin(usn,psw);
        checkStaff(usn,psw);
        checkCustomer(usn,psw);
        
        

        return username;
    }
    
    public static void admin(){
//        createshop name staff(username,pass,new Person(name,email,phone)) max_capacity
//        logout
    }
    
    public static void staff(StaffAccount sa){
//        addproduct
//        removeproduct
//        restock
    }
    
    public static void customer(CustomerAccount ca){
//        topup
//        order
//          pay
    }

    public static String CheckUsernameForSignUp() {
        String name, firstname, lastname, phone, email;
                
        do{
            System.out.println("enter username");
            String username = choose.next();
            boolean temp = boba.checkUsername(username);
        }while()
        
                
                System.out.println("Choose a password");
                String passw = choose.next();
                System.out.println("Enter first name");
                firstname = user.nextLine();
                System.out.println("Enter last name");
                lastname = user.nextLine();
                System.out.println("Enter your phone numbers");
                phone = user.nextLine();
                System.out.println("Enter your email");
                email = user.nextLine();
                name = (firstname + " " + lastname);
                String SUBSCRIBED = AccountStatus.SUBSCRIBED.toString();


                System.out.println("Sign up succesfully added");
                
                boba.addCustomer(new CustomerAccount(username, passw, new Person(name, email, phone)));

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That username already exists");
        }

        return username;
    }
}
