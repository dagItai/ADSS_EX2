import java.util.Date;

// line 16 "model.ump"
// line 111 "model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String id;
  private String address;
  private String phone;
  private String email;

  //Customer Associations
  private WebUser webUser;
  private Account account;


  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Customer (String aId, String aAddress, String aPhone, String aEmail){
    id = aId;
    address = aAddress;
    phone = aPhone;
    email = aEmail;
  }

  public Customer(String aId, String aAddress, String aPhone, String aEmail, Account aAccount)
  {
    id = aId;
    address = aAddress;
    phone = aPhone;
    email = aEmail;
    if (aAccount == null || aAccount.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = aAccount;
  }

  public Customer(String aId, String aAddress, String aPhone, String aEmail, String aIdForAccount, String aBilling_addressForAccount, boolean aIs_closedForAccount, Date aOpenForAccount, Date aClosedForAccount, int aBalanceForAccount, ShoppingCart aShoppingCartForAccount)
  {
    id = aId;
    address = aAddress;
    phone = aPhone;
    email = aEmail;
    account = new Account(aIdForAccount, aBilling_addressForAccount, aIs_closedForAccount, aOpenForAccount, aClosedForAccount, aBalanceForAccount, this, aShoppingCartForAccount);
  }

  //------------------------
  // INTERFACE
  //------------------------
  public void setAccount(Account account) {
    this.account = account;
  }

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(String aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public WebUser getWebUser()
  {
    return webUser;
  }

  public boolean hasWebUser()
  {
    boolean has = webUser != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setWebUser(WebUser aNewWebUser)
  {
    boolean wasSet = false;
    if (webUser != null && !webUser.equals(aNewWebUser) && equals(webUser.getCustomer()))
    {
      //Unable to setWebUser, as existing webUser would become an orphan
      return wasSet;
    }

    webUser = aNewWebUser;
    Customer anOldCustomer = aNewWebUser != null ? aNewWebUser.getCustomer() : null;

    if (!this.equals(anOldCustomer))
    {
      if (anOldCustomer != null)
      {
        anOldCustomer.webUser = null;
      }
      if (webUser != null)
      {
        webUser.setCustomer(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    WebUser existingWebUser = webUser;
    webUser = null;
    if (existingWebUser != null)
    {
      existingWebUser.delete();
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phone" + ":" + getPhone()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "webUser = "+(getWebUser()!=null?Integer.toHexString(System.identityHashCode(getWebUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }
}