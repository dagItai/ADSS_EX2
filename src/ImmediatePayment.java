/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.util.*;

// line 91 "model.ump"
// line 169 "model.ump"
public class ImmediatePayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ImmediatePayment Attributes
  private boolean phoneConfirmation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ImmediatePayment(String aId, float aTotal, String aDetails, Order aOrder, boolean aPhoneConfirmation, Account...allAccounts)
  {
    super(aId, aTotal, aDetails, aOrder, allAccounts);
    phoneConfirmation = aPhoneConfirmation;
  }
    public ImmediatePayment(String aId, float aTotal, String aDetails, Order aOrder, boolean aPhoneConfirmation, Account account)
    {
        super(aId, aTotal, aDetails, aOrder, account);
        phoneConfirmation = aPhoneConfirmation;
    }


    //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneConfirmation(boolean aPhoneConfirmation)
  {
    boolean wasSet = false;
    phoneConfirmation = aPhoneConfirmation;
    wasSet = true;
    return wasSet;
  }

  public boolean getPhoneConfirmation()
  {
    return phoneConfirmation;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneConfirmation" + ":" + getPhoneConfirmation()+ "]";
  }
}