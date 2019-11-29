/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;

// line 25 "model.ump"
// line 117 "model.ump"
public class WebUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WebUser Attributes
  private String login_id;
  private String password;
  private UserState state;

  //WebUser Associations
  private Customer customer;
  private ShoppingCart shoppingCart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WebUser(String aLogin_id, String aPassword, UserState aState, Customer aCustomer)
  {
    login_id = aLogin_id;
    password = aPassword;
    state = aState;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create webUser due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLogin_id(String aLogin_id)
  {
    boolean wasSet = false;
    login_id = aLogin_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setState(UserState aState)
  {
    boolean wasSet = false;
    state = aState;
    wasSet = true;
    return wasSet;
  }

  public String getLogin_id()
  {
    return login_id;
  }

  public String getPassword()
  {
    return password;
  }

  public UserState getState()
  {
    return state;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }

  public boolean hasShoppingCart()
  {
    boolean has = shoppingCart != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer == null)
    {
      //Unable to setCustomer to null, as webUser must always be associated to a customer
      return wasSet;
    }
    
    WebUser existingWebUser = aNewCustomer.getWebUser();
    if (existingWebUser != null && !equals(existingWebUser))
    {
      //Unable to setCustomer, the current customer already has a webUser, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Customer anOldCustomer = customer;
    customer = aNewCustomer;
    customer.setWebUser(this);

    if (anOldCustomer != null)
    {
      anOldCustomer.setWebUser(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setShoppingCart(ShoppingCart aNewShoppingCart)
  {
    boolean wasSet = false;
    if (shoppingCart != null && !shoppingCart.equals(aNewShoppingCart) && equals(shoppingCart.getWebUser()))
    {
      //Unable to setShoppingCart, as existing shoppingCart would become an orphan
      return wasSet;
    }

    shoppingCart = aNewShoppingCart;
    WebUser anOldWebUser = aNewShoppingCart != null ? aNewShoppingCart.getWebUser() : null;

    if (!this.equals(anOldWebUser))
    {
      if (anOldWebUser != null)
      {
        anOldWebUser.shoppingCart = null;
      }
      if (shoppingCart != null)
      {
        shoppingCart.setWebUser(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.setWebUser(null);
    }
    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = null;
    if (existingShoppingCart != null)
    {
      existingShoppingCart.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "login_id" + ":" + getLogin_id()+ "," +
            "password" + ":" + getPassword()+ "," +
            "state" + ":" + getState()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shoppingCart = "+(getShoppingCart()!=null?Integer.toHexString(System.identityHashCode(getShoppingCart())):"null");
  }
}