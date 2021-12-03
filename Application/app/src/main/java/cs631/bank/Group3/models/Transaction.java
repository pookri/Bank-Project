package cs631.bank.Group3.models;

import java.sql.Date;
import java.sql.Timestamp;



public class Transaction {
    int transaction_id;
    int transaction_account_number;
    String transaction_type;
    double transaction_amount;
    Timestamp transaction_time;
    Date transaction_date;

    public Transaction(int transaction_id,int transaction_account_number,String transaction_type,double transaction_amount,Timestamp transaction_time,Date transaction_date){
        this.transaction_id=transaction_id;
        this.transaction_account_number=transaction_account_number;
        this .transaction_type=transaction_type;
        this.transaction_amount=transaction_amount;
        this.transaction_time=transaction_time;
        this.transaction_date=transaction_date;
    }

   
}
