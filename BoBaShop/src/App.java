
import java.util.Scanner;
import model.BoBaShop;
import model.CustomerAccount;
import model.Person;
import model.StaffAccount;

public class App {
    private static BoBaShop boba;
    static int selecthome;
    static int selectcust;
    static Scanner input = new Scanner(System.in);
    static String menuhomepage = " =====   Select Your Menu:   ===== \n"
            + "           1. • Sign-in •            \n"
            + "           2. • Sign-up •            \n"
            + "           3. •  Exits  •            \n"
            + " ==================================  ";
    static String menucustomer = " =====   Select Your Menu:   ===== \n"
            + "           1. • Topup •            \n"
            + "           2. • Order •            \n"
            + "           3. •  Pay  •            \n"
            + " ==================================  ";
    
    
    public static void main(String[] args) {
        
    }
    public static void homepage(){
        do {
            selecthome = menu();
            switch (selecthome) {
                case 1:
                    login();
                    break;
                case 2:
                    register();

                    break;
            }
        } while (selecthome != 3);
         System.out.println("********************************************************");
         System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
         System.out.println("********************************************************");
    }

    public static void login() {
        System.out.println("******   LOGIN YOUR ACCOUNT   ******");
        CheckUsernameForLogIn();
        System.out.println(" === Login Sucessfully === ");
    }
    public static void logout(){
        homepage();
    }

    public static void register() {
        System.out.println("******   REGISTER YOUR ACCOUNT   ******");
        CheckUsernameForRegistered();
    }

    public static int menu() {
         System.out.println("*******************************************");
         System.out.println("*********   WELCOME TO BOBASHOP   *********");
         System.out.println("*******************************************");
         System.out.println(menuhomepage);
         System.out.print(" • Enter your menu: ");
        selecthome = input.nextInt();
        return selecthome;
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
        shopcreate();
        logout();
    }
    public static void shopcreate() {
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
        boba = new BoBaShop(shopname, new StaffAccount(staffusn, staffpsw, new Person(staffname, email, phonenum)),mcp);
       
    
}
    public static void staff() {
//        addproduct
//        removeproduct
//        restock
//      blacklist
    }
    public static void addproduct(){
        System.out.println("Product Id: ");
        int productid = input.nextInt();
       
    }

    public static void customer(CustomerAccount ca) {
        CustomerAccount customer = ca;
        {
            selectcust = menu();
            switch (selectcust) {
                case 1:
                    topup(ca);
                    break;
                case 2:
                    order(ca);
                    break;
                case 3:
                    pay(ca);
                    break;
                    // can add more but not yet
            }
        } while (selectcust != 3); // can add more but not yet
                 System.out.println("********************************************************");
                 System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
                 System.out.println("********************************************************");

//          pay
    }
    public static void topup(CustomerAccount ca){
        System.out.print("Enter your money: ");
        int money = input.nextInt();
        boba.topUp(ca, money);
    }
    
    public static void order(CustomerAccount ca){
        System.out.print("Select your drink: ");
        int drinkmenu = input.nextInt();
        System.out.println("Amount: ");
        int amount = input.nextInt();
        boba.order(ca, drinkmenu, amount);
    }

    public static void pay(CustomerAccount ca){ //*********** not success ***********
        boba.makePayment(ca);
    }
    public static String CheckUsernameForRegistered() {
        String username, name, firstname, lastname, phone, email;
        boolean checkTemp;
        do {
            checkTemp = false;
            System.out.print("Create your username: ");
            username = input.next();
            checkTemp = boba.checkUsername(username);
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
        System.out.println(" ****** YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED  ****** ");
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
