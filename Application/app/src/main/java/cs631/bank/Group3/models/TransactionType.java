package cs631.bank.Group3.models;

public class TransactionType {
    String transactionType;
    String transactionName;
    Double transactionCharge;
    
    public TransactionType(  String transactionType,
    String transactionName,
    Double transactionCharge){ 
        this.transactionType = transactionType;
        this.transactionName=transactionName;
        this.transactionCharge=transactionCharge;
    }
}
