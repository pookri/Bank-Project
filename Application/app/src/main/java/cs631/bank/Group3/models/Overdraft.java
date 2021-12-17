package cs631.bank.Group3.models;

import java.sql.Date;

public class Overdraft {
    int overdraft_number;
    int checking_account_number;
    Date overdraft_date;
    float overdraft_fee;
    
    public Overdraft(int overdraft_number,int checking_account_number,Date overdraft_date,float overdraft_fee){
        this.overdraft_number=overdraft_number;
        this.checking_account_number=checking_account_number;
        this.overdraft_date=overdraft_date;
        this.overdraft_fee=overdraft_fee;
    }
}
