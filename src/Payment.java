/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// line 78 "model.ump"
// line 159 "model.ump"
public class Payment {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Payment Attributes
    private String id;
    private float total;
    private String details;

    //Payment Associations
    private List<Account> accounts;
    private Order order;
    private HashMap<Account, Integer> partInPayment;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Payment(String aId, float aTotal, String aDetails, Order aOrder, Account account, int percent) {
        id = aId;
        total = aTotal;
        details = aDetails;
        accounts = new ArrayList<Account>();

        boolean didAddAccounts = setAccounts(account);
        if (!didAddAccounts) {
            throw new RuntimeException("Unable to create Payment, must have at least 1 accounts. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }

        partInPayment = new HashMap<>();
        partInPayment.put(account, percent);

        boolean didAddOrder = setOrder(aOrder);
        if (!didAddOrder) {
            throw new RuntimeException("Unable to create payment due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    public Payment(String aId, float aTotal, String aDetails, Order aOrder, Account account) {
        this(aId, aTotal, aDetails, aOrder, account, 100);
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

    public boolean setTotal(float aTotal) {
        boolean wasSet = false;
        total = aTotal;
        wasSet = true;
        return wasSet;
    }

    public boolean setDetails(String aDetails) {
        boolean wasSet = false;
        details = aDetails;
        wasSet = true;
        return wasSet;
    }

    public String getId() {
        return id;
    }

    public float getTotal() {
        return total;
    }

    public String getDetails() {
        return details;
    }

    /* Code from template association_GetMany */
    public Account getAccount(int index) {
        Account aAccount = accounts.get(index);
        return aAccount;
    }

    public List<Account> getAccounts() {
        List<Account> newAccounts = Collections.unmodifiableList(accounts);
        return newAccounts;
    }

    public int numberOfAccounts() {
        int number = accounts.size();
        return number;
    }

    public boolean hasAccounts() {
        boolean has = accounts.size() > 0;
        return has;
    }

    public int indexOfAccount(Account aAccount) {
        int index = accounts.indexOf(aAccount);
        return index;
    }

    /* Code from template association_GetOne */
    public Order getOrder() {
        return order;
    }

    /* Code from template association_IsNumberOfValidMethod */
    public boolean isNumberOfAccountsValid() {
        boolean isValid = numberOfAccounts() >= minimumNumberOfAccounts();
        return isValid;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAccounts() {
        return 1;
    }

    /* Code from template association_AddManyToManyMethod */
    public boolean addAccount(Account aAccount, int percent) {
        boolean wasAdded = false;
        if (accounts.contains(aAccount)) {
            return false;
        }
        accounts.add(aAccount);
        if (aAccount.indexOfPayment(this) != -1) {
            wasAdded = true;
            partInPayment.put(aAccount, percent);
        } else {
            wasAdded = aAccount.addPayment(this);
            if (!wasAdded) {
                accounts.remove(aAccount);
            }
        }
        return wasAdded;
    }

    public boolean addAccount(Account aAccount) {
        return addAccount(aAccount, 100);
    }

    /* Code from template association_AddMStarToMany */
    public boolean removeAccount(Account aAccount) {
        boolean wasRemoved = false;
        if (!accounts.contains(aAccount)) {
            return wasRemoved;
        }

        if (numberOfAccounts() <= minimumNumberOfAccounts()) {
            return wasRemoved;
        }

        int oldIndex = accounts.indexOf(aAccount);
        accounts.remove(oldIndex);
        if (aAccount.indexOfPayment(this) == -1) {
            wasRemoved = true;
            partInPayment.remove(aAccount);
        } else {
            wasRemoved = aAccount.removePayment(this);
            if (!wasRemoved) {
                accounts.add(oldIndex, aAccount);
            }
        }
        return wasRemoved;
    }

    /* Code from template association_SetMStarToMany */
    public boolean setAccounts(Account... newAccounts) {
        boolean wasSet = false;
        ArrayList<Account> verifiedAccounts = new ArrayList<Account>();
        for (Account aAccount : newAccounts) {
            if (verifiedAccounts.contains(aAccount)) {
                continue;
            }
            verifiedAccounts.add(aAccount);
        }

        if (verifiedAccounts.size() != newAccounts.length || verifiedAccounts.size() < minimumNumberOfAccounts()) {
            return wasSet;
        }

        ArrayList<Account> oldAccounts = new ArrayList<Account>(accounts);
        accounts.clear();
        for (Account aNewAccount : verifiedAccounts) {
            accounts.add(aNewAccount);
            if (oldAccounts.contains(aNewAccount)) {
                oldAccounts.remove(aNewAccount);
            } else {
                aNewAccount.addPayment(this);
            }
        }

        for (Account anOldAccount : oldAccounts) {
            anOldAccount.removePayment(this);
        }
        wasSet = true;
        return wasSet;
    }

    /* Code from template association_SetOneToMany */
    public boolean setOrder(Order aOrder) {
        boolean wasSet = false;
        if (aOrder == null) {
            return wasSet;
        }

        Order existingOrder = order;
        order = aOrder;
        if (existingOrder != null && !existingOrder.equals(aOrder)) {
            existingOrder.removePayment(this);
        }
        order.addPayment(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        ArrayList<Account> copyOfAccounts = new ArrayList<Account>(accounts);
        accounts.clear();
        for (Account aAccount : copyOfAccounts) {
            aAccount.removePayment(this);
        }
        Order placeholderOrder = order;
        this.order = null;
        if (placeholderOrder != null) {
            placeholderOrder.removePayment(this);
        }
    }


    public String toString() {
        return super.toString() + "[" +
                "id" + ":" + getId() + "," +
                "total" + ":" + getTotal() + "," +
                "details" + ":" + getDetails() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "order = " + (getOrder() != null ? Integer.toHexString(System.identityHashCode(getOrder())) : "null");
    }
}