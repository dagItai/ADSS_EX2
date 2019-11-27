import java.util.Map;
import java.util.Set;

public abstract class Payment {

    private String id;
    private float total;
    private String details;
    Order order;
    Map<Account,PartInPayment> accountPartInPayment;

    public Payment(String id, float total, String details) {
        this.id = id;
        this.total = total;
        this.details = details;
    }
}

