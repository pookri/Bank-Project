package cs631.bank.Group3.models;

import java.sql.Date;

public class Account {
    
    int account_number;
    String branch_id;
    String acc_type;
    double balance;
    double interest_rate;
    Date create_date;

    public Account(int account_number,String branch_id,String acc_type,double balance,double interest_rate,Date create_date){
        this.account_number=account_number;
        this.branch_id=branch_id;
        this.acc_type=acc_type;
        this.balance=balance;
        this.interest_rate=interest_rate;
        this.create_date=create_date;

    }
}
