import javafx.util.Pair;

import java.util.Date;
import java.util.*;

public class Main {
    private static HashSet<WebUser> webUsers = new HashSet<>();
    private static HashSet<Product> products = new HashSet<>();
    private static HashSet<Supplier> suppliers = new HashSet<>();
    private static HashMap<WebUser, Order> usersOrders;
    private static int numOfOrdered = 0;
    private static int numOfPayments = 0;

    public void mainmenu() throws Exception {
        Scanner input = new Scanner(System.in);
        while (true) {
            int choice = printMainMenu();
            switch (choice) {
                case 0:
                    printLoginMenu();
                    continue;
                case 1:
                    Pair<String, String> login_details = printLoginMenu();
                    WebUser webUser = login(login_details.getKey(), login_details.getValue());
                    if (webUser != null) {
                        /// yaara here functions for logged user
                        functionsForLoggedUser(webUser);
                    } else {
                        System.out.println("login details are not matching, please try again or your account will be banned");
                        //printMainMenu();
                    }
                    continue;
                case 2:
                    addProducts();
                    continue;
                case 3:
                    editProduct();
                    continue;
                case 4:
                    removeProduct();
                    continue;
                case 5:
                    addUser();
                    continue;
                case 6:
                    editUser();
                    continue;
                case 7:
                    removeUser();
                    continue;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private WebUser login(String userName, String password) {
        for (WebUser webUser : webUsers) {
            if (webUser.getLogin_id().equals(userName) && webUser.getPassword().equals(password))
                return webUser;

        }
        return null;
    }

    private int printMainMenu() {
        int optionn = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Login");
        System.out.println("2.Add products");
        System.out.println("3.Edit products ");
        System.out.println("4.Remove products ");
        System.out.println("5.Add users");
        System.out.println("6.Edit users ");
        System.out.println("7.Remove users ");
        System.out.println("--------------");
        System.out.println("Enter your choice:");
        optionn = keyboard.nextInt();
        return optionn;
    }

    private Pair<String, String> printLoginMenu() {
        String username;
        String password;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome! Here is the Login:");
        System.out.println("--------------");
        System.out.println("Enter user name:");
        username = keyboard.nextLine();
        System.out.println("Enter password:");
        password = keyboard.nextLine();
        return new Pair<>(username, password);
    }

    public static void main(String[] args) throws Exception {

        String id = "Dani";
        String password = "Dani123";
        UserState state = UserState.Active;
        String address = "Klausner";
        String phone = "0505973311";
        String email = "dani'sEmail@walla.co.il";
        boolean isClosed = false;
        Date openDate = new Date();
        Date closedDate = null;
        int balance = 10000;
        Date createdDate = new Date();

        WebUser daniUser = setWebUser(id, password, state, address, phone, email, isClosed, openDate, closedDate, balance, createdDate, false);
        WebUser danaUser = setWebUser("Dana", "Dana123", state, "Rager", "0525853870", "momo@cow", isClosed, openDate, closedDate, balance, createdDate, true);
        webUsers.add(daniUser);
        webUsers.add(danaUser);

        Supplier supplier = new Supplier("Moshe", "Moshe");
        Product bamba = new Product("Bamba", "Bamba", supplier);
        supplier.addProduct(bamba);
        suppliers.add(supplier);
        products.add(bamba);
        ((PremiumAccount) danaUser.getCustomer().getAccount()).addProduct(bamba);
        bamba.setPremiumAccount((PremiumAccount) danaUser.getCustomer().getAccount());

        Main m = new Main();
        m.mainmenu();
    }

    private static WebUser setWebUser(String id, String password, UserState state, String address, String phone, String email, boolean isClosed, Date openDate, Date closedDate, int balance, Date createdDate, boolean premium) throws Exception {
        Customer customer = new Customer(id, address, phone, email);
        WebUser webUser = new WebUser(id, password, state, customer); //no costumer
        Account account;
        if (!premium) {
            account = new Account(id, address, isClosed, openDate, closedDate, balance, openDate, webUser);
        } else {
            account = new PremiumAccount(id, address, isClosed, openDate, closedDate, balance, openDate, webUser);
        }
        return webUser;
    }

    //------------------------
    // PRODUCTS FUNCTION
    //------------------------
    private boolean editProduct() {
        String id = "";
        String answer = "";
        boolean hasAdd = false;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Please enter the product ID you want to edit");
        id = keyboard.nextLine();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                System.out.println("Choose the following:");
                System.out.println("--------------");
                System.out.println("1.add product to exist supplier");
                System.out.println("2.add product to new supplier");
                answer = keyboard.nextLine();
                if (answer.equals("1")) {
                    String supplierID;
                    System.out.println("Please enter the supplier's ID that you want to set to this product");
                    supplierID = keyboard.nextLine();
                    for (Supplier supplier : suppliers) {
                        if (supplier.getId().equals(supplierID)) {
                            product.setSupplier(supplier);
                            return true;
                        }
                    }
                    System.out.println("There isn't these suppplier's ID! back to main menu...");

                } else if (answer.equals("2")) {
                    Supplier supplier = newSupplier();
                    product.setSupplier(supplier);
                }
            }
        }
        System.out.println("No such product ID! back to main menu...");
        return false;
    }

    private boolean addProductToSupplier(Supplier supplier) {
        String id = "";
        String name = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the following:");
        System.out.println("--------------");
        System.out.println("Enter product id:");
        id = keyboard.nextLine();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                System.out.println("This product id is already exists.. back to main menu!");
                return false;
            }
        }
        System.out.println("Enter product name:");
        name = keyboard.nextLine();
        Product product = new Product(id, name, supplier);
        products.add(product);
        return true;
    }

    private boolean addProducts() {
        String answer = "";
        String id = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hello, please choose from the following");
        System.out.println("--------------");
        System.out.println("1.add product to exist supplier");
        System.out.println("2.add product to new supplier");
        answer = keyboard.nextLine();
        if (answer.equals("1")) {
            System.out.println("--------------");
            System.out.println("please enter supplier id");
            id = keyboard.nextLine();
            for (Supplier supplier : suppliers) {
                if (supplier.getId().equals(id)) {
                    addProductToSupplier(supplier);
                    return true;
                }
            }
            System.out.println("There is no supplier with this id! back to main menu... ");
            return false;
        } else if (answer.equals("2")) {
            Supplier supplier = newSupplier();
            if (supplier == null)
                return false;
            addProductToSupplier(supplier);
            return true;
        }
        return true;
    }

    private boolean removeProduct() {
        String id = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the product id that you want to remove:");
        id = keyboard.nextLine();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.delete();
                products.remove(product);
                System.out.println("successfully removed!");
                return true;
            }
        }
        System.out.println("There is no product with this id! back to main menu... ");
        return false;

    }

    private Supplier newSupplier() {
        String id = "";
        String name = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the following:");
        System.out.println("--------------");
        System.out.println("Enter supplier id:");
        id = keyboard.nextLine();
        for (Supplier supplier : suppliers) {
            if (supplier.getId().equals(id)) {
                System.out.println("This supplier id is already exists.. back to main menu!");
                return null;
            }
        }
        System.out.println("Enter supplier name:");
        name = keyboard.nextLine();
        Supplier supplier = new Supplier(id, name);
        suppliers.add(supplier);
        return supplier;
    }

    //------------------------
    // USER FUNCTION
    //------------------------
    private boolean removeUser() {
        String id = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the user id that you want to remove:");
        id = keyboard.nextLine();
        for (WebUser webUser : webUsers) {
            if (webUser.getLogin_id().equals(id)) {
                webUser.delete();
                webUsers.remove(webUser);
                System.out.println("successfully removed!");
                return true;
            }
        }
        System.out.println("There is no user with this id! back to main menu... ");
        return false;
    }

    private boolean editUser() {
        String answer = "";
        String id = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter user ID that you would like to edit");
        id = keyboard.nextLine();
        for (WebUser webUser : webUsers) {
            if (webUser.getLogin_id().equals(id)) {
                System.out.println("Please choose from the following:");
                System.out.println("--------------");
                System.out.println("1. Change user address");
                System.out.println("2. Change user phone number");
                System.out.println("3. Change user email");
                answer = keyboard.nextLine();
                if (answer.equals("1")) {
                    System.out.println("Please enter a new address for this user");
                    String address = keyboard.nextLine();
                    webUser.getCustomer().getAccount().setBilling_address(address);
                    webUser.getCustomer().setAddress(address);
                    return true;

                } else if (answer.equals("2")) {
                    System.out.println("Please enter a new phone number for this user");
                    String number = keyboard.nextLine();
                    webUser.getCustomer().setPhone(number);
                    return true;

                } else if (answer.equals("3")) {
                    System.out.println("Please enter a new email for this user");
                    String email = keyboard.nextLine();
                    webUser.getCustomer().setPhone(email);
                    return true;
                }
            }
        }
        System.out.println("There is no user with this id! back to main menu... ");
        return false;
    }

    private boolean addUser() throws Exception {
        String id;
        String password;
        String address;
        String phone;
        String email;
        String balance;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Hi, please Enter user id");
        id = keyboard.nextLine();
        for (WebUser webUser : webUsers) {
            if (webUser.getLogin_id().equals(id)) {
                System.out.println("There is already user with this ID! back to main menu..");
                return true;
            }
        }
        System.out.println("Hi, please Enter user password");
        password = keyboard.nextLine();
        System.out.println("Hi, please Enter user address");
        address = keyboard.nextLine();
        System.out.println("Hi, please Enter user phone");
        phone = keyboard.nextLine();
        System.out.println("Hi, please Enter user email");
        email = keyboard.nextLine();
        System.out.println("Hi, please Enter user balance");
        balance = keyboard.nextLine();
        UserState state = UserState.New;
        boolean isClosed = false;
        Date openDate = new Date();
        Date closedDate = null;
        Date createdDate = new Date();

        WebUser newWebUser = setWebUser(id, password, state, address, phone, email, isClosed, openDate, closedDate, Integer.valueOf(balance), createdDate, false);
        webUsers.add(newWebUser);
        return true;
    }

    // logged user
    private boolean functionsForLoggedUser(WebUser wu) {
        int option = 0;
        Scanner keyboard3 = new Scanner(System.in);
        System.out.println("Successfully Logged-in!");

        while (true) {
            System.out.println("--------------");
            System.out.println("1.View my last orders");
            System.out.println("2.Add product to your shopping cart");

            System.out.println("3.Log out and return to main menu");
            System.out.println("--------------");
            System.out.println("Enter your choice:");
            option = keyboard3.nextInt();
            switch (option) {
                case 1:
                    List<Order> myOrders = wu.getCustomer().getAccount().getOrders();
                    if (myOrders.size() == 0) {// empty list
                        System.out.println("Sorry, your list is empty");


                    } else {
                        System.out.println(myOrders.get(myOrders.size() - 1).toString());
                    }
                    continue;
                case 2:
                    String productName;
                    Scanner keyboard1 = new Scanner(System.in);
                    System.out.println("Please insert the product you would like to buy");
                    productName = keyboard1.nextLine();
                    for (Product p2 : products) {
                        if (p2.getName().compareTo(productName) == 0) {

                            Scanner choosebuy = new Scanner(System.in);
                            System.out.println("--------------");
                            System.out.println("Choose your way of Buying:");
                            System.out.println("1.from Premium Account");
                            System.out.println("2.Regular");
                            System.out.println("--------------");
                            System.out.println("Enter your choice:");
                            int mychoose = choosebuy.nextInt();
                            if (mychoose == 1) {
                                String premiumAccountName;
                                Scanner keyboard2 = new Scanner(System.in);
                                System.out.println("Please insert the Premium account ID you would like to buy from ");
                                premiumAccountName = keyboard2.nextLine();
                                for (Product p : products) { //check all the  products
                                    if (p.getName().compareTo(productName) == 0) {  // check if its bamba
                                        if (p.getPremiumAccount() == null) {
                                            System.out.println("There is no premium account which is selling this item, try again later :-)");
                                            break;
                                        }
                                        if (premiumAccountName.compareTo(((Account) p.getPremiumAccount()).getId()) == 0) { // checks if this is the specific user who has bamba
                                            PremiumAccount danaFor = p.getPremiumAccount();
                                            numOfOrdered++;
                                            Order order = new Order(Integer.toString(numOfOrdered), new Date(), new Date(), wu.getCustomer().getAddress(), "waitForDelivery", 10, wu.getCustomer().getAccount());

                                            LineItem li = new LineItem(1, 10, p, wu.getShoppingCart(), order);
                                            p.addLineItem(li);
                                            order.addLineItem(li);
                                            wu.getShoppingCart().addLineItem(li);
                                            p.getPremiumAccount().removeProduct(p);
                                            Scanner keyboard4 = new Scanner(System.in);
                                            System.out.println("the product " + productName + " successfully added to your cart, now you need to pay!");
                                            System.out.println("--------------");
                                            System.out.println("Choose your way of payment:");
                                            System.out.println("1.Immediate Payment");
                                            System.out.println("2.Delayed Payment");
                                            System.out.println("--------------");
                                            System.out.println("Enter your choice:");
                                            int option3 = keyboard4.nextInt();
                                            if (option3 == 1) {
                                                numOfPayments++;
                                                ImmediatePayment imp = new ImmediatePayment(Integer.toString(numOfPayments), (float) order.getTotal(), order.toString(), order, true, wu.getCustomer().getAccount());
                                                wu.getShoppingCart().getAccount().addPayment(imp);
                                                order.addPayment(imp);
                                                System.out.println("Congratulations! you successfully purchase: " + productName + " from " + premiumAccountName + " Now you can check your history of orders!");
                                                break;
                                            } else {
                                                if (option3 == 2) {
                                                    numOfPayments++;

                                                    DelayedPayment delp = new DelayedPayment(Integer.toString(numOfPayments), (float) order.getTotal(), order.toString(), order, null, wu.getCustomer().getAccount());
                                                    wu.getShoppingCart().getAccount().addPayment(delp);
                                                    order.addPayment(delp);
                                                    System.out.println("Congratulations! you successfully purchase: " + productName + " from " + premiumAccountName + " Now you can check your history of orders!");
                                                    break;
                                                } else {
                                                    order.delete();
                                                    danaFor.addProduct(p);
                                                    break;

                                                }
                                            }
                                        } else {
                                            System.out.println("this premium account doesn't sell your item, try different account");
                                            break;
                                        }

                                    }
                                }
                            }
                            else if (mychoose==2){
                                numOfOrdered++;
                                Order order = new Order(Integer.toString(numOfOrdered), new Date(), new Date(), wu.getCustomer().getAddress(), "waitForDelivery", 10, wu.getCustomer().getAccount());

                                LineItem li = new LineItem(1, 10, p2, wu.getShoppingCart(), order);
                                p2.addLineItem(li);
                                order.addLineItem(li);
                                wu.getShoppingCart().addLineItem(li);

                                Scanner keyboard4 = new Scanner(System.in);
                                System.out.println("the product " + productName + " successfully added to your cart, now you need to pay!");
                                System.out.println("--------------");
                                System.out.println("Choose your way of payment:");
                                System.out.println("1.Immediate Payment");
                                System.out.println("2.Delayed Payment");
                                System.out.println("--------------");
                                System.out.println("Enter your choice:");
                                int option3 = keyboard4.nextInt();

                                if (option3 == 1) {
                                    numOfPayments++;
                                    ImmediatePayment imp = new ImmediatePayment(Integer.toString(numOfPayments), (float) order.getTotal(), order.toString(), order, true, wu.getCustomer().getAccount());
                                    wu.getShoppingCart().getAccount().addPayment(imp);
                                    order.addPayment(imp);
                                    System.out.println("Congratulations! you successfully purchase: " + productName +  " Now you can check your history of orders!");
                                    p2.getSupplier().removeProduct(p2);
                                    if ( wu.getCustomer().getAccount() instanceof PremiumAccount){
                                        ((PremiumAccount) wu.getCustomer().getAccount()).addProduct(p2);
                                    }
                                    break;
                                } else {
                                    if (option3 == 2) {
                                        numOfPayments++;

                                        DelayedPayment delp = new DelayedPayment(Integer.toString(numOfPayments), (float) order.getTotal(), order.toString(), order, null, wu.getCustomer().getAccount());
                                        wu.getShoppingCart().getAccount().addPayment(delp);
                                        order.addPayment(delp);
                                        System.out.println("Congratulations! you successfully purchase: " + productName +  " Now you can check your history of orders!");
                                        p2.getSupplier().removeProduct(p2);
                                        if ( wu.getCustomer().getAccount() instanceof PremiumAccount){
                                            ((PremiumAccount) wu.getCustomer().getAccount()).addProduct(p2);
                                        }
                                        break;
                                    } else {
                                        order.delete();

                                        break;

                                    }
                                }





                            }
                        }
                    }
                    continue;

                case 3:
                    try{
                        mainmenu();
                    }catch(Exception exception){
                        System.out.println();
                    }
                    break;  //Todo check if necessary
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

}
