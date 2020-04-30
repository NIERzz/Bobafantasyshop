
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
        System.out.println("WELCOME -TO- SIGN-IN /n");
        CheckUsernameForSignIn();
    }

    public static void signup() {
        System.out.println("WELCOME -TO- SIGN-UP /n");
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
        String signin = user.next();
        System.out.println("Enter your password: ");
        String pass2 = user.next();
        String sql = "SELECT * FROM customer WHERE cus_username LIKE '" + signin + "'";

        try ( Connection conn = DBConnection.getConnection();  Statement stm = conn.createStatement();  ResultSet rs = stm.executeQuery(sql)) {
            while ((rs.next())) {
                //next method
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NULL");
        }

        return username;
    }

    public static String CheckUsernameForSignUp() {
        dup = true;
        String name, firstname, lastname, phone, email;
        try ( Connection conn = DBConnection.getConnection();  Statement stm = conn.createStatement();) {
            
            System.out.println("Choose your username: ");
            username = choose.next();
            String sql = "SELECT * FROM customer WHERE cus_username LIKE '" + username + "'";
            ResultSet rs = stm.executeQuery(sql);

           if (rs != null && rs.next()) {
                {
                    dup = false;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NULL");
        }

        if (dup) {

            try ( Connection conn = DBConnection.getConnection();  PreparedStatement ppsm = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)")) {
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

                ppsm.setString(1, name);
                ppsm.setString(2, email);
                ppsm.setString(3, phone);
                ppsm.setString(4, username);
                ppsm.setString(5, passw);
                ppsm.setString(6, SUBSCRIBED);
                ppsm.executeUpdate();

                System.out.println("Sign up succesfully added");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That username already exists");
        }

        return username;
    }
}
