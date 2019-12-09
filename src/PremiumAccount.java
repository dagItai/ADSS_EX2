/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// line 38 "model.ump"
// line 128 "model.ump"
public class PremiumAccount extends Account {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //PremiumAccount Associations
    private List<Product> products;

    //------------------------
    // CONSTRUCTOR
    //------------------------
    public PremiumAccount(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Date aCreatedForShoppingCart, WebUser aWebUserForShoppingCart) {
        super(aId, aBilling_address, aIs_closed, aOpen, aClosed, aBalance, aCreatedForShoppingCart, aWebUserForShoppingCart);
        products = new ArrayList<Product>();
    }

    public PremiumAccount(String aId, String aBilling_address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Customer aCustomer, ShoppingCart aShoppingCart) {
        super(aId, aBilling_address, aIs_closed, aOpen, aClosed, aBalance, aCustomer, aShoppingCart);
        products = new ArrayList<Product>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    public Product getProduct(int index) {
        Product aProduct = products.get(index);
        return aProduct;
    }

    public List<Product> getProducts() {
        List<Product> newProducts = Collections.unmodifiableList(products);
        return newProducts;
    }

    public int numberOfProducts() {
        int number = products.size();
        return number;
    }

    public boolean hasProducts() {
        boolean has = products.size() > 0;
        return has;
    }

    public int indexOfProduct(Product aProduct) {
        int index = products.indexOf(aProduct);
        return index;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfProducts() {
        return 0;
    }

    /* Code from template association_AddManyToOptionalOne */
    public boolean addProduct(Product aProduct) {
        boolean wasAdded = false;
        if (products.contains(aProduct)) {
            return false;
        }
        PremiumAccount existingPremiumAccount = aProduct.getPremiumAccount();
        if (existingPremiumAccount == null) {
            aProduct.setPremiumAccount(this);
        } else if (!this.equals(existingPremiumAccount)) {
            existingPremiumAccount.removeProduct(aProduct);
            addProduct(aProduct);
        } else {
            products.add(aProduct);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeProduct(Product aProduct) {
        boolean wasRemoved = false;
        if (products.contains(aProduct)) {
            products.remove(aProduct);
            aProduct.setPremiumAccount(null);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    public void delete() {
        while (!products.isEmpty()) {
            products.get(0).setPremiumAccount(null);
        }
        super.delete();
    }

}