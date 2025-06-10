public interface PaymentStrategy {
    void pay(double amount);
}

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card (" + cardHolder + ")");
    }
}

public class UPIPayment implements PaymentStrategy {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI (" + upiId + ")");
    }
}

public class WalletPayment implements PaymentStrategy {
    private String walletName;

    public WalletPayment(String walletName) {
        this.walletName = walletName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Wallet: " + walletName);
    }
}

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    // Constructor Injection (Strategy pattern)
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        paymentStrategy.pay(amount);
    }

    // Allows changing strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9012-3456", "Subhani");
        PaymentStrategy upi = new UPIPayment("subhani@upi");
        PaymentStrategy wallet = new WalletPayment("PayBuddy");

        PaymentProcessor processor = new PaymentProcessor(creditCard);
        processor.processPayment(1500);

        processor.setPaymentStrategy(upi);
        processor.processPayment(600);

        processor.setPaymentStrategy(wallet);
        processor.processPayment(300);
    }
}
