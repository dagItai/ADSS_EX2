import javafx.util.Pair;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static HashSet<WebUser> webUsers = new HashSet<>();
    private static HashSet<Product> products = new HashSet<>();
    private static HashSet<Supplier> suppliers = new HashSet<>();

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

                    } else {
                        System.out.println("login details are not matching, please try again or your account will be banned");
                        printMainMenu();
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
                    String address= keyboard.nextLine();
                    webUser.getCustomer().getAccount().setBilling_address(address);
                    webUser.getCustomer().setAddress(address);
                    return true;

                } else if (answer.equals("2")) {
                    System.out.println("Please enter a new phone number for this user");
                    String number= keyboard.nextLine();
                    webUser.getCustomer().setPhone(number);
                    return true;

                } else if (answer.equals("3")) {
                    System.out.println("Please enter a new email for this user");
                    String email= keyboard.nextLine();
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

}
