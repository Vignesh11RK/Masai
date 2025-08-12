//Target
interface PaymentGateway{
    boolean pay(String fromAccount, String toAccount, double amount);
}

//Legacy class
// Adaptee (legacy)

class LegacyPaymentProcessor{
    // older signature--- its an older class that was is being used here as a legacy code
    public String makePayment(String srcAcct, String destAcct, double amt) {
        // returns transaction id or null if fail
        System.out.println("Legacy code is running");
        return "Vignesh_Legacy";
    }

    // Adapter
    static class LegacyPaymentAdapter implements PaymentGateway{
        private final LegacyPaymentProcessor legacy;

        public LegacyPaymentAdapter(LegacyPaymentProcessor legacy) {
            this.legacy = legacy;
        }

        @Override
        public boolean pay(String fromAccount, String toAccount, double amount) {
            String txn = legacy.makePayment(fromAccount, toAccount, amount);
            return txn != null;
        }

    }





}
