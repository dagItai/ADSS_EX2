import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Account {
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;

    Customer customer;
    ShoppingCart shoppingCart;
    Set<Order> orderSet;
    Map<Payment, PartInPayment> paymentPartInPayment;



}
