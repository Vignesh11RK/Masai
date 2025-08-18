package enums;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw");

    private final String displayName;

    TransactionType(String displayName){
        this.displayName=displayName;
    }


    public String getDisplayName(){
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
