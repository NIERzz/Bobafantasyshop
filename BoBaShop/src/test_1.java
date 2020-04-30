
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class test {
    static int select;
    static Scanner user = new Scanner(System.in);
    static Scanner choose = new Scanner(System.in);
    private static String username;
    static Boolean dup;
    static String menu = "Menu:\n"
            + "1. sign-in\n"
            + "2. sign-up \n"
            + "3. exits"
            + "Select menu: ";
    private static boolean bool = true;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(menu());
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
        try {
            System.out.println("Enter your username: ");
            String testingsignin = user.next();
            System.out.println("Enter your password: ");
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
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            System.out.println("There is a input/output error that was caught");
        }

        return username;
    }

    public static String CheckUsernameForSignUp() {
        dup = true;
        System.out.println("Choose your username: ");
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
                System.out.println("Choose a password");
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
            System.out.println("That username already exists");
        }

        return username;
    }
}
