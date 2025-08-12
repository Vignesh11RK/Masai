//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LegacyPaymentProcessor legacy = new LegacyPaymentProcessor();
        PaymentGateway gateway = new LegacyPaymentProcessor.LegacyPaymentAdapter(legacy);
        System.out.println("Payment success: " + gateway.pay("A", "B", 100.0));

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}