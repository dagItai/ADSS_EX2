/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// line 32 "model.ump"
// line 122 "model.ump"
public class ShoppingCart {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //ShoppingCart Attributes
    private Date created;

    //ShoppingCart Associations
    private WebUser webUser;
    private Account account;
    private List<LineItem> lineItems;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public ShoppingCart(Date aCreated, WebUser aWebUser, Account aAccount) {
        created = aCreated;
        boolean didAddWebUser = setWebUser(aWebUser);
        if (!didAddWebUser) {
            throw new RuntimeException("Unable to create shoppingCart due to webUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        if (aAccount == null || aAccount.getShoppingCart() != null) {
            throw new RuntimeException("Unable to create ShoppingCart due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        account = aAccount;
        lineItems = new ArrayList<LineItem>();
    }

    public ShoppingCart(Date aCreated, WebUser aWebUser, String aIdForAccount, String aBilling_addressForAccount, boolean aIs_closedForAccount, Date aOpenForAccount, Date aClosedForAccount, int aBalanceForAccount, Customer aCustomerForAccount) {
        created = aCreated;
        boolean didAddWebUser = setWebUser(aWebUser);
        if (!didAddWebUser) {
            throw new RuntimeException("Unable to create shoppingCart due to webUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        account = new Account(aIdForAccount, aBilling_addressForAccount, aIs_closedForAccount, aOpenForAccount, aClosedForAccount, aBalanceForAccount, aCustomerForAccount, this);
        lineItems = new ArrayList<LineItem>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setCreated(Date aCreated) {
        boolean wasSet = false;
        created = aCreated;
        wasSet = true;
        return wasSet;
    }

    public Date getCreated() {
        return created;
    }

    /* Code from template association_GetOne */
    public WebUser getWebUser() {
        return webUser;
    }

    /* Code from template association_GetOne */
    public Account getAccount() {
        return account;
    }

    /* Code from template association_GetMany */
    public LineItem getLineItem(int index) {
        LineItem aLineItem = lineItems.get(index);
        return aLineItem;
    }

    public List<LineItem> getLineItems() {
        List<LineItem> newLineItems = Collections.unmodifiableList(lineItems);
        return newLineItems;
    }

    public int numberOfLineItems() {
        int number = lineItems.size();
        return number;
    }

    public boolean hasLineItems() {
        boolean has = lineItems.size() > 0;
        return has;
    }

    public int indexOfLineItem(LineItem aLineItem) {
        int index = lineItems.indexOf(aLineItem);
        return index;
    }

    /* Code from template association_SetOneToOptionalOne */
    public boolean setWebUser(WebUser aNewWebUser) {
        boolean wasSet = false;
        if (aNewWebUser == null) {
            //Unable to setWebUser to null, as shoppingCart must always be associated to a webUser
            return wasSet;
        }

        ShoppingCart existingShoppingCart = aNewWebUser.getShoppingCart();
        if (existingShoppingCart != null && !equals(existingShoppingCart)) {
            //Unable to setWebUser, the current webUser already has a shoppingCart, which would be orphaned if it were re-assigned
            return wasSet;
        }

        WebUser anOldWebUser = webUser;
        webUser = aNewWebUser;
        webUser.setShoppingCart(this);

        if (anOldWebUser != null) {
            anOldWebUser.setShoppingCart(null);
        }
        wasSet = true;
        return wasSet;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfLineItems() {
        return 0;
    }

    /* Code from template association_AddManyToOne */
    public LineItem addLineItem(int aQuantity, int aPrice, Product aProduct, Order aOrder) {
        return new LineItem(aQuantity, aPrice, aProduct, this, aOrder);
    }

    public boolean addLineItem(LineItem aLineItem) {
        boolean wasAdded = false;
        if (lineItems.contains(aLineItem)) {
            return false;
        }
        ShoppingCart existingShoppingCart = aLineItem.getShoppingCart();
        boolean isNewShoppingCart = existingShoppingCart != null && !this.equals(existingShoppingCart);
        if (isNewShoppingCart) {
            aLineItem.setShoppingCart(this);
        } else {
            lineItems.add(aLineItem);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeLineItem(LineItem aLineItem) {
        boolean wasRemoved = false;
        //Unable to remove aLineItem, as it must always have a shoppingCart
        if (!this.equals(aLineItem.getShoppingCart())) {
            lineItems.remove(aLineItem);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    public void delete() {
        WebUser existingWebUser = webUser;
        webUser = null;
        if (existingWebUser != null) {
            existingWebUser.setShoppingCart(null);
        }
        Account existingAccount = account;
        account = null;
        if (existingAccount != null) {
            existingAccount.delete();
        }
        for (int i = lineItems.size(); i > 0; i--) {
            LineItem aLineItem = lineItems.get(i - 1);
            aLineItem.delete();
        }
    }


    public String toString() {
        return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "created" + "=" + (getCreated() != null ? !getCreated().equals(this) ? getCreated().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "webUser = " + (getWebUser() != null ? Integer.toHexString(System.identityHashCode(getWebUser())) : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "account = " + (getAccount() != null ? Integer.toHexString(System.identityHashCode(getAccount())) : "null");
    }
}