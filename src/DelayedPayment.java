/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4753.5a97eca04 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 85 "model.ump"
// line 164 "model.ump"
public class DelayedPayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DelayedPayment Attributes
  private Date payment_date;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DelayedPayment(String aId, float aTotal, String aDetails, Order aOrder, Date aPayment_date, Account... allAccounts)
  {
    super(aId, aTotal, aDetails, aOrder, allAccounts);
    payment_date = aPayment_date;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPayment_date(Date aPayment_date)
  {
    boolean wasSet = false;
    payment_date = aPayment_date;
    wasSet = true;
    return wasSet;
  }

  public Date getPayment_date()
  {
    return payment_date;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "payment_date" + "=" + (getPayment_date() != null ? !getPayment_date().equals(this)  ? getPayment_date().toString().replaceAll("  ","    ") : "this" : "null");
  }
}