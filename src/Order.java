import java.util.Date;
import java.util.Set;

public class Order {
    String number;
    Date ordered;
    Date shipped;
    Address ship_to;
    OrderStatus status;
    float total;

    Set <LineItem> lineItems;
    Set <Payment> payments;
    Account account;
}
