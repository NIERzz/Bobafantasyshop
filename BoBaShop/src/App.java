
import Account.AccountStatus;
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
        try ( Connection conn = DBConnection.getConnection();  Statement stm = conn.createStatement()) {

            System.out.println("Enter your username: ");
            String signin = user.next();
            System.out.println("Enter your password: ");
            String pass2 = user.next();
            String sql = "SELECT * FROM  customer WHERE cust_username LIKE " + signin;
            ResultSet rs = stm.executeQuery(sql);
            if ((rs != null) && (rs.next())) {
                //next method
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username;
    }

    public static String CheckUsernameForSignUp() {
        dup = true;
        String sql = "SELECT * FROM  customer WHERE cust_username LIKE %" + username;
        String name, firstname, lastname, phone, email;
        System.out.println("Choose your username: ");
        username = choose.next();
        try ( Connection conn = DBConnection.getConnection()) {
            Statement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            if (rs != null) {
                {
                    dup = false;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (dup) {
            try ( Connection conn = DBConnection.getConnection();  
                    PreparedStatement ppsm = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)")) {

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
                AccountStatus SUBSCRIBED = AccountStatus.SUBSCRIBED;

                ppsm.setString(1, name);
                ppsm.setString(2, email);
                ppsm.setString(3, phone);
                ppsm.setString(4, username);
                ppsm.setString(5, passw);
                ppsm.setObject(6, SUBSCRIBED);
                ppsm.executeUpdate();
//              (name + ", " + email + ", " + phone + ", " + username + ", " + passw + ", " + SUBSCRIBED);
                System.out.println("Sign up succesfully added");

            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("That username already exists");
        }

        return username;
    }
}
