package cs631.bank.Group3.models;

public enum TransactionTypeEnum {

    WD(true, "WD"),
    DS(false, "DS"),
    SC(true, "SC"),
    IN(false, "IN"),
    LN(true, "LN"),
    OD(true, "OD"),
    ID(false, "ID");
    
    public String valString;
    public boolean isDebit;

    TransactionTypeEnum(boolean isDebit, String valString){ 
        this.valString = valString;
        this.isDebit = isDebit;
    }
}
