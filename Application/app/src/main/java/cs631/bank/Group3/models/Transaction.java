package cs631.bank.Group3.models;

import java.sql.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Transaction {
    int transactionId;
    String transactionAccountNumber;
    String transactionType;
    double transactionAmount;
    Timestamp transactionTime;
    Date transactionDate;

    public Transaction(String acountNum, String type, double amount) { 
        this.transactionAccountNumber = acountNum;
        this.transactionType = type;
        this.transactionAmount = amount;
        
    }

    public Transaction(int transaction_id,String transaction_account_number,String transaction_type,double transaction_amount,Timestamp transaction_time,Date transaction_date){
        this.transactionId=transaction_id;
        this.transactionAccountNumber=transaction_account_number;
        this .transactionType=transaction_type;
        this.transactionAmount=transaction_amount;
        this.transactionTime=transaction_time;
        this.transactionDate=transaction_date;
    }

   
}
