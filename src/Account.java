/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 102 "model.ump"
public class Account {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Account Attributes
    private String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;

    //Account Associations
    private Customer customer;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    private List<Payment> payments;
    private HashMap<Payment, Integer> partInPayment;
    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Account(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Customer aCustomer, ShoppingCart aShoppingCart) {
        id = aId;
        billing_address = aBilling_address;
        is_closed = aIs_closed;
        open = aOpen;
        closed = aClosed;
        balance = aBalance;
        if (aCustomer == null || aCustomer.getAccount() != null) {
            throw new RuntimeException("Unable to create Account due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        customer = aCustomer;
        if (aShoppingCart == null || aShoppingCart.getAccount() != null) {
            throw new RuntimeException("Unable to create Account due to aShoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        shoppingCart = aShoppingCart;
        orders = new ArrayList<>();
        payments = new ArrayList<>();
        partInPayment = new HashMap<>();
    }

    public Account(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Date aCreatedForShoppingCart, WebUser aWebUserForShoppingCart) {
        id = aId;
        billing_address = aBilling_address;
        is_closed = aIs_closed;
        open = aOpen;
        closed = aClosed;
        balance = aBalance;
        customer = aWebUserForShoppingCart.getCustomer();
        customer.setAccount(this);
        shoppingCart = new ShoppingCart(aCreatedForShoppingCart, aWebUserForShoppingCart, this);
        aWebUserForShoppingCart.setShoppingCart(shoppingCart);
        orders = new ArrayList<>();
        payments = new ArrayList<>();
        partInPayment = new HashMap<>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setId(String aId) {
        boolean wasSet = false;
        id = aId;
        wasSet = true;
        return wasSet;
    }

    public boolean setBilling_address(String aBilling_address) {
        boolean wasSet = false;
        billing_address = aBilling_address;
        wasSet = true;
        return wasSet;
    }

    public boolean setIs_closed(boolean aIs_closed) {
        boolean wasSet = false;
        is_closed = aIs_closed;
        wasSet = true;
        return wasSet;
    }

    public boolean setOpen(Date aOpen) {
        boolean wasSet = false;
        open = aOpen;
        wasSet = true;
        return wasSet;
    }

    public boolean setClosed(Date aClosed) {
        boolean wasSet = false;
        closed = aClosed;
        wasSet = true;
        return wasSet;
    }

    public boolean setBalance(int aBalance) {
        boolean wasSet = false;
        balance = aBalance;
        wasSet = true;
        return wasSet;
    }

    public String getId() {
        return id;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public boolean getIs_closed() {
        return is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public Date getClosed() {
        return closed;
    }

    public int getBalance() {
        return balance;
    }

    /* Code from template association_GetOne */
    public Customer getCustomer() {
        return customer;
    }

    /* Code from template association_GetOne */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /* Code from template association_GetMany */
    public Order getOrder(int index) {
        Order aOrder = orders.get(index);
        return aOrder;
    }

    public List<Order> getOrders() {
        List<Order> newOrders = Collections.unmodifiableList(orders);
        return newOrders;
    }

    public int numberOfOrders() {
        int number = orders.size();
        return number;
    }

    public boolean hasOrders() {
        boolean has = orders.size() > 0;
        return has;
    }

    public int indexOfOrder(Order aOrder) {
        int index = orders.indexOf(aOrder);
        return index;
    }

    /* Code from template association_GetMany */
    public Payment getPayment(int index) {
        Payment aPayment = payments.get(index);
        return aPayment;
    }

    public List<Payment> getPayments() {
        List<Payment> newPayments = Collections.unmodifiableList(payments);
        return newPayments;
    }

    public int numberOfPayments() {
        int number = payments.size();
        return number;
    }

    public boolean hasPayments() {
        boolean has = payments.size() > 0;
        return has;
    }

    public int indexOfPayment(Payment aPayment) {
        int index = payments.indexOf(aPayment);
        return index;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfOrders() {
        return 0;
    }

    /* Code from template association_AddManyToOne */
    public Order addOrder(String aNumber, Date aOrdered, Date aShipped, String aShip, String aShip_to, String aStatus, float aTotal) {
        return new Order(aNumber, aOrdered, aShipped, aShip_to, aStatus, aTotal, this);
    }

    public boolean addOrder(Order aOrder) {
        boolean wasAdded = false;
        if (orders.contains(aOrder)) {
            return false;
        }
        Account existingAccount = aOrder.getAccount();
        boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
        if (isNewAccount) {
            aOrder.setAccount(this);
        } else {
            orders.add(aOrder);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeOrder(Order aOrder) {
        boolean wasRemoved = false;
        //Unable to remove aOrder, as it must always have a account
        if (!this.equals(aOrder.getAccount())) {
            orders.remove(aOrder);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfPayments() {
        return 0;
    }

    /* Code from template association_AddManyToManyMethod */
    public boolean addPayment(Payment aPayment) {
        return addPayment(aPayment, 100);
    }

    public boolean addPayment(Payment aPayment, int percent) {
        boolean wasAdded = false;
        if (payments.contains(aPayment)) {
            return false;
        }
        payments.add(aPayment);
        if (aPayment.indexOfAccount(this) != -1) {
            wasAdded = true;
            partInPayment.put(aPayment, percent);
        } else {
            wasAdded = aPayment.addAccount(this);
            if (!wasAdded) {
                payments.remove(aPayment);
            }
        }
        return wasAdded;
    }

    /* Code from template association_RemoveMany */
    public boolean removePayment(Payment aPayment) {
        boolean wasRemoved = false;
        if (!payments.contains(aPayment)) {
            return wasRemoved;
        }

        int oldIndex = payments.indexOf(aPayment);
        payments.remove(oldIndex);
        if (aPayment.indexOfAccount(this) == -1) {
            partInPayment.remove(aPayment);
            wasRemoved = true;
        } else {
            wasRemoved = aPayment.removeAccount(this);
            if (!wasRemoved) {
                payments.add(oldIndex, aPayment);
            }
        }
        return wasRemoved;
    }

    public void delete() {
        Customer existingCustomer = customer;
        customer = null;
        if (existingCustomer != null) {
            existingCustomer.delete();
        }
        ShoppingCart existingShoppingCart = shoppingCart;
        shoppingCart = null;
        if (existingShoppingCart != null) {
            existingShoppingCart.delete();
        }
        for (int i = orders.size(); i > 0; i--) {
            Order aOrder = orders.get(i - 1);
            aOrder.delete();
        }
        ArrayList<Payment> copyOfPayments = new ArrayList<Payment>(payments);
        payments.clear();
        for (Payment aPayment : copyOfPayments) {
            if (aPayment.numberOfAccounts() <= Payment.minimumNumberOfAccounts()) {
                aPayment.delete();
            } else {
                aPayment.removeAccount(this);
            }
        }
    }


    public String toString() {
        return super.toString() + "[" +
                "id" + ":" + getId() + "," +
                "billing_address" + ":" + getBilling_address() + "," +
                "is_closed" + ":" + getIs_closed() + "," +
                "balance" + ":" + getBalance() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "open" + "=" + (getOpen() != null ? !getOpen().equals(this) ? getOpen().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "closed" + "=" + (getClosed() != null ? !getClosed().equals(this) ? getClosed().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "customer = " + (getCustomer() != null ? Integer.toHexString(System.identityHashCode(getCustomer())) : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "shoppingCart = " + (getShoppingCart() != null ? Integer.toHexString(System.identityHashCode(getShoppingCart())) : "null");
    }
}