
import Account.AccountStatus;
import Exception.ExceedMaxCapacityException;
import Exception.NEIAException;
import Exception.NoProductException;
import Exception.NotEnoughMoneyException;
import java.io.IOException;
import java.util.Scanner;
import model.Beverage;
import model.BoBaShop;
import model.CustomerAccount;
import model.Dessert;
import model.GeneralList;
import model.OrderedProduct;
import model.Person;
import model.StaffAccount;

public class App {

    private static BoBaShop boba;
    static int selecthome;
    static int selectcust;
    static int selectstaff;
    private static StaffAccount staff;
    static Scanner input = new Scanner(System.in);
    static String menuhomepage = " =====   Select Your Menu:   ===== \n"
            + "           1. • Sign-in •            \n"
            + "           2. • Sign-up •            \n"
            + "           3. •  Exits  •            \n"
            + " ==================================  ";
    static String menucustomer = " =====   Select Your Menu:   ===== \n"
            + "           1.   • Topup •            \n"
            + "           2. • View Money •            \n"
            + "           3.  • Show Menu •            \n"
            + "           4.   • Order •            \n"
            + "           5.•  Remove Order  •            \n"
            + "           6. • Clear Order  •       \n"
            + "           7.  • Order List  •           \n"
            + "           8.  • Total Price •           \n"
            + "           9.    • Pay •           \n"
            + "           10.  • Logout •           \n"
            + " ==================================  ";
    static String menustaff = " =====   Select Your Menu:   ===== \n"
            + "           1. •  Addproduct •            \n"
            + "           2. • Removeproduct •            \n"
            + "           3. •   Restock  •            \n"
            + "           4. •  Blacklist  •            \n"
            + "           5. •  Showmenu  •            \n"
            + "           6. •   Logout  •            \n"
            + " ==================================  ";

    public static void main(String[] args) {
        homepage();
    }

    public static void homepage() {
        do {
            selecthome = menu();
            switch (selecthome) {
                case 1:
                    login();
                    break;
                case 2:
                    if (boba == null) {
                        System.out.println("     ======================     ");
                        System.out.println("  YOUR SHOP DOESN'T CREATE YET  ");
                        System.out.println("     PLEASE CREATE THE SHOP    ");
                        System.out.println("     ======================     ");
                        break;
                    }
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

    public static void logout() {
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
        input.nextLine();
        return selecthome;
    }

    public static int menustaff() {
        System.out.println("*******************************************");
        System.out.println("*********     LOGGIN AS STAFF     *********");
        System.out.println("*******************************************");
        System.out.println(menustaff);
        System.out.print(" • Enter your menu: ");
        selectstaff = input.nextInt();
        input.nextLine();
        return selectstaff;
    }

    public static int menucustomer() {
        System.out.println("******************************************");
        System.out.println("*********   LOGGIN AS CUSTOMER   *********");
        System.out.println("******************************************");
        System.out.println(menucustomer);
        System.out.print(" • Enter your menu: ");
        selectcust = input.nextInt();
        input.nextLine();
        return selectcust;
    }

    public static void CheckUsernameForLogIn() {
        boolean check;
        check = false;
        System.out.print("Enter your username: ");
        String usn = input.nextLine();
        System.out.print("Enter your password: ");
        String psw = input.nextLine();

        if (boba == null) {
            if (checkAdmin(usn, psw)) {
                admin();
                check = true;
            } else {
                System.out.println(" ==== FAILED TO LOGIN! ==== ");
                System.out.println("   PLEASE CREATE THE SHOP   ");
                System.out.println("     BY SIGN IN AS ADMIN    ");
                System.out.println("       USERNAME: admin      ");
                System.out.println("       PASSWORD: admin      ");
                System.out.println(" ========================== ");
            }
        } else {
            if (checkStaff(usn, psw)) {
                staff(staff);
                check = true;
            }
            if (checkCustomer(usn, psw) != null) {
                if (checkCustomer(usn, psw).getStatus().equals(AccountStatus.BLACKLISTED)) {
                    System.out.println("====================================");
                    System.out.println("===            Sorry             ===");
                    System.out.println("=== Your account was blacklisted ===");
                    System.out.println("====================================");
                } else {
                    customer(checkCustomer(usn, psw));
                    check = true;
                }
            } else {
                System.out.println(" ==== FAILED TO LOGIN! ==== ");
                System.out.println("      PLEASE TRY AGAIN      ");
                System.out.println(" ========================== ");
            }
        }
        homepage();
    }

    /*                                                                           ADMIN ZONE !!!!!!!                                                                                 */
    public static void admin() {
        shopcreate();
        logout();
    }

    public static void shopcreate() {
        System.out.println("***************************************");
        System.out.println("*******  CREATE YOUR SHOP NAME  *******");
        System.out.println("***************************************");

        System.out.print("Enter your shop name: ");
        String shopname = input.nextLine();
        System.out.print("Enter maximum capacity of your shop: ");
        int mcp = input.nextInt();
        input.nextLine();
        System.out.println("********************************");
        System.out.println("***** CREATE STAFF ACCOUNT *****");
        System.out.println("********************************");
        System.out.print("Enter your username: ");
        String staffusn = input.nextLine();
        System.out.print("Enter your password: ");
        String staffpsw = input.nextLine();
        System.out.print("Enter your name: ");
        String staffname = input.nextLine();
        System.out.print("Enter your e-mail: ");
        String email = input.nextLine();
        System.out.print("Enter your phone-number: ");
        String phonenum = input.nextLine();
        staff = new StaffAccount(staffusn, staffpsw, new Person(staffname, email, phonenum));
        boba = new BoBaShop(shopname, staff, mcp);
    }

    /*                                                                          STAFF ZONE !!!!!!!                                                                                 */
    public static void staff(StaffAccount st) {
        do {
            selectstaff = menustaff();
            StaffAccount staff = st;

            switch (selectstaff) {
                case 1:
                    addproduct(staff);
                    break;
                case 2:
                    removeproduct(staff);
                    break;
                case 3:
                    restock(staff);
                    break;
                case 4:
                    blacklist(st);
                    break;
                case 5:
                    showmenu();
                    break;
                case 6:
                    break;
            }
        } while (selectstaff != 6);

    }

    public static void addproduct(StaffAccount st) {
        System.out.println(" ***** SELECT TYPE OF PRODUCTS ****** ");
        System.out.println(" • 1. Beverage • ");
        System.out.println(" • 2. Dessert  • ");
        System.out.print(" === Enter type: ");
        int typeselect = input.nextInt();
        input.nextLine();
        switch (typeselect) {
            case 1:
                System.out.print("Enter the product: ");
                String Bname = input.nextLine();
                System.out.print("Enter price of product: ");
                int Bprice = input.nextInt();
                input.nextLine();
                boba.addNewProduct(new Beverage(Bprice, Bname));
                break;
            case 2:
                System.out.print("Enter the product: ");
                String Dname = input.nextLine();
                System.out.print("Enter price of product: ");
                int Dprice = input.nextInt();
                input.nextLine();
                boba.addNewProduct(new Dessert(Dprice, Dname));
                break;
        }

    }

    public static void removeproduct(StaffAccount st) {
        try {
            showmenu();
            System.out.print("Enter ID to remove the products: ");
            int rmproductid = input.nextInt();
            input.nextLine();
            boba.removeProductFromStock(rmproductid);
        } catch (NoProductException ex) {
            System.out.println(ex);
        }
    }

    public static void restock(StaffAccount st) {
        try {
            showmenu();
            System.out.print("Enter Id to restock the products: ");
            int addproductid = input.nextInt();
            input.nextLine();
            System.out.print("Enter amount of the products: ");
            int amountproduct = input.nextInt();
            input.nextLine();
            boba.restock(addproductid, amountproduct);
        } catch (ExceedMaxCapacityException m) {
            System.out.println(m.getMessage());
            System.out.println("Please try again");
        }
    }

    public static void blacklist(StaffAccount st) {
        System.out.print("Enter username: ");
        String blacklist = input.nextLine();
        if (boba.blacklistCustomer(boba.getCustomerByUsername(blacklist))) {
            System.out.println("Succesfully blacklisted customer: " + boba.getCustomerByUsername(blacklist).getId());
        } else {
            System.out.println("Not have customer " + boba.getCustomerByUsername(blacklist).getId() + ".");
        }
    }

    /*                                                                           CUSTOMER ZONE !!!!!!!                                                                                 */
    public static void customer(CustomerAccount ca) {
        CustomerAccount customer = ca;
        do {
            selectcust = menucustomer();
            switch (selectcust) {
                case 1:
                    topup(customer);
                    break;
                case 2:
                    getAccMoney(customer);
                    break;
                case 3:
                    showmenu();
                    break;
                case 4:
                    order(customer);
                    break;
                case 5:
                    removeOrder(customer);
                    break;
                case 6:
                    clear(customer);
                    break;
                case 7:
                    showOrderList(customer);
                    break;
                case 8:
                    getTotalPrice(customer);
                    break;
                case 9:
                    pay(customer);
                    break;
                case 10:
                    break;
            }
        } while (selectcust != 10);

    }

    public static void getTotalPrice(CustomerAccount ca) {
        int money = boba.getTotalPrice(ca);
        System.out.println("=================");
        System.out.println(" Total Price: " + money);
        System.out.println("=================");
    }

    public static void getAccMoney(CustomerAccount ca) {
        int money = boba.getAccMoney(ca);
        System.out.println("=================");
        System.out.println(" Balance: " + money);
        System.out.println("=================");
    }

    public static void removeOrder(CustomerAccount ca) {
        try {
            showOrderList(ca);
            System.out.println(" ***** REMOVE PRODUCTS ***** ");
            System.out.print("Enter product id: ");
            int productid = input.nextInt();
            input.nextLine();
            boba.removeOrderFromList(ca, productid);
        } catch (NoProductException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void clear(CustomerAccount ca) {
        boba.clearOrder(ca);
        System.out.println("=== Succesfully clear the order list ===");
    }

    public static void topup(CustomerAccount ca) {
        System.out.print("Enter your money: ");
        int money = input.nextInt();
        input.nextLine();
        if (boba.topUp(ca, money)) {
            System.out.println("=== Succesfully added money to account ===");
        } else {
            System.out.println("=== Failed to add money to account ===");
        }
    }

    public static void order(CustomerAccount ca) {
        showmenu();
        System.out.print("Select your product: ");
        int drinkmenu = input.nextInt();
        input.nextLine();
        System.out.print("Amount: ");
        int amount = input.nextInt();
        input.nextLine();
        if (boba.order(ca, drinkmenu, amount)) {
            System.out.println("=== Successfully add product to order list ===");
        } else {
            System.out.println("=== Not have this id in the stock ===");
        }
    }

    public static void pay(CustomerAccount ca) {
        try {
            boba.makePayment(ca);
            System.out.println("=== Payment Complete ===");
        } catch (NotEnoughMoneyException | NoProductException | NEIAException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void CheckUsernameForRegistered() {
        String username, name, firstname, lastname, phone, email;
        boolean checkTemp;
        do {
            checkTemp = false;
            System.out.print("Create your username: ");
            username = input.nextLine();
            checkTemp = boba.checkUsername(username);
        } while (checkTemp);

        System.out.print("Create your password: ");
        String passw = input.nextLine();
        System.out.print("Enter your first name: ");
        firstname = input.nextLine();
        System.out.print("Enter your last name: ");
        lastname = input.nextLine();
        System.out.print("Enter your phone numbers: ");
        phone = input.nextLine();
        System.out.print("Enter your email: ");
        email = input.nextLine();
        name = (firstname + " " + lastname);
        System.out.println(" ****** YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED  ****** ");
        boba.addCustomer(new CustomerAccount(username, passw, new Person(name, email, phone)));
    }

    private static boolean checkAdmin(String usn, String psw) {
        return usn.equals("admin") && psw.equals("admin");
    }

    private static boolean checkStaff(String usn, String psw) {
        return boba.checkStaff(usn, psw);
    }

    private static CustomerAccount checkCustomer(String usn, String psw) {
        return boba.checkCustomer(usn, psw);
    }

    public static void showmenu() {
        System.out.println(" ***** PRODUCTS LIST ****** ");
        GeneralList<OrderedProduct> genList = boba.getMenu();
        String format = "%-25s%-20s%n";
        System.out.printf(format, "ID  Name", "Price");
        for (int i = 0; i < genList.getCount(); i++) {
            OrderedProduct temp = genList.getItemAt(i);
            System.out.printf(format, temp.getId() + ": " + temp.getProduct().getName(), temp.getProduct().getPrice());
        }
    }

    public static void showOrderList(CustomerAccount ca) {
        try {
            System.out.println(" ORDER LIST ");
            GeneralList<OrderedProduct> genList = boba.getOrderList(ca);
            String format = "%-25s%-20s%-10s%n";
            System.out.printf(format, "ID  Name", "Price", "Amount");
            for (int i = 0; i < genList.getCount(); i++) {
                OrderedProduct temp = genList.getItemAt(i);
                System.out.printf(format, temp.getId() + ": " + temp.getProduct().getName(), temp.getProduct().getPrice(), temp.getAmount());
            }
        } catch (NoProductException ex) {
            System.out.println(ex);
        }
    }
}
