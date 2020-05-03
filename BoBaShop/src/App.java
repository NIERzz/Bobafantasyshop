
import Exception.ExceedMaxCapacityException;
import java.util.Scanner;
import model.Beverage;
import model.BoBaShop;
import model.CustomerAccount;
import model.Dessert;
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
            + "           1. • Topup •            \n"
            + "           2. • Order •            \n"
            + "           3. •  Pay  •            \n"
            + " ==================================  ";
    static String menustaff = " =====   Select Your Menu:   ===== \n"
            + "           1. •  Addproduct •            \n"
            + "           2. • Removeproduct •            \n"
            + "           3. •   Restock  •            \n"
            + "           4. •  Blacklist  •            \n"
            + " ==================================  ";
    
    
    public static void main(String[] args) {
        homepage();
    }
    public static void homepage(){
        do {
            selecthome = menu();
            switch (selecthome) {
                case 1:
                    login();
                    break;
                case 2:
                    if(boba==null){
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
        selecthome = input.nextInt(); input.nextLine();
        return selecthome;
    }
    public static int menustaff(){
         System.out.println("*******************************************");
         System.out.println("*********     LOGGIN AS STAFF     *********");
         System.out.println("*******************************************");        
         System.out.println(menustaff);
         System.out.print(" • Enter your menu: ");
        selectstaff = input.nextInt(); input.nextLine();
        return selectstaff;
    }
 public static int menucustomer(){
         System.out.println("******************************************");
         System.out.println("*********   LOGGIN AS CUSTOMER   *********");
         System.out.println("******************************************");
         System.out.println(menucustomer);
         System.out.print(" • Enter your menu: ");
        selectcust = input.nextInt(); input.nextLine();
        return selectcust;
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
                staff(staff);
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
        boba = new BoBaShop(shopname, staff ,mcp);
        
       
    
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
                    // can add more but not yet
            }
            } while (selectstaff != 3); // can add more but not yet
                 System.out.println("********************************************************");
                 System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
                 System.out.println("********************************************************");
}
    public static void addproduct(StaffAccount st){
        System.out.println(" ***** SELECT TYPE OF PRODUCTS ****** ");
        System.out.println(" • 1. Beverage • ");
        System.out.println(" • 2. Dessert  • ");
        System.out.print(" === Enter type: ");
        int typeselect = input.nextInt(); input.nextLine();
        switch(typeselect){
            case 1:
                System.out.print("Enter the product: ");
                String Bname = input.nextLine();
                System.out.print("Enter price of product: ");
                int Bprice = input.nextInt(); input.nextLine();
                boba.addNewProduct(new Beverage(Bprice, Bname));
                break;
                case 2:
                System.out.print("Enter the product: ");
                String Dname = input.next(); input.nextLine();
                System.out.print("Enter price of product: ");
                int Dprice = input.nextInt(); input.nextLine();
                boba.addNewProduct(new Dessert(Dprice, Dname));
                break;
        }
       
       
    }
    public static void removeproduct(StaffAccount st){
        System.out.print("Enter ID to remove the products: ");
        int rmproductid = input.nextInt(); input.nextLine();
        boba.removeProductFromStock(rmproductid);
    }
    public static void restock(StaffAccount st) {
      try {  System.out.print("Enter Id to add the products: ");
        int addproductid = input.nextInt(); input.nextLine();
        System.out.print("Enter Amount of the products: ");
        int amountproduct = input.nextInt(); input.nextLine();
        boba.restock(addproductid, amountproduct);
      }
            catch(ExceedMaxCapacityException m){
                System.out.println(m.getMessage());
        }
    }
    public static void blacklist(StaffAccount st){
         System.out.print("Enter username: ");
        String blacklist = input.nextLine();
        boba.blacklistCustomer(boba.getCustomerByUsername(blacklist));
    }
   /*                                                                           CUSTOMER ZONE !!!!!!!                                                                                 */ 
    public static void customer(CustomerAccount ca) {
        CustomerAccount customer = ca;
         do{   
             selectcust = menucustomer();
             switch (selectcust) {
                case 1:
                    topup(customer);
                    break;
                case 2:
                    order(customer);
                    break;
                case 3:
                    pay(customer);
                    break;
                    // can add more but not yet
            }
        } while (selectcust != 3); // can add more but not yet
                 System.out.println("********************************************************");
                 System.out.println("***** THANK YOU FOR VISITING TO BOBASHOP, GOODLUCK *****");
                 System.out.println("********************************************************");
        

    }
    public static void topup(CustomerAccount ca){
        System.out.print("Enter your money: ");
        int money = input.nextInt(); input.nextLine();
        boba.topUp(ca, money);
    }
    
    public static void order(CustomerAccount ca){
        System.out.print("Select your drink: ");
        int drinkmenu = input.nextInt(); input.nextLine();
        System.out.println("Amount: ");
        int amount = input.nextInt(); input.nextLine();
        boba.order(ca, drinkmenu, amount);
    }

    public static void pay(CustomerAccount ca){ //*********** not success ***********
        boba.makePayment(ca);
    }
    public static void CheckUsernameForRegistered() {
        String username, name, firstname, lastname, phone, email;
        boolean checkTemp;
        do {
            checkTemp = false;
            System.out.print("Create your username: ");
            username = input.nextLine();
            checkTemp = boba.checkUsername(username);
        } while (!checkTemp);

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
}
