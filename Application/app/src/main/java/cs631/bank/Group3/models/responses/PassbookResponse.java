package cs631.bank.Group3.models.responses;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class PassbookResponse {
    Date date;
    String transactionCode;
    String transactionName;
    double debits;
    double credits;
    double balance;

    public PassbookResponse(
        Date date,
        String transactionCode,
        String transactionName,
        double debits,
        double credits,
        double balance
    ){ 
        this.date = date;
    this.transactionCode = transactionCode;
    this.transactionName = transactionName;
    this.debits = debits;
    this.credits = credits;
    this.balance = balance;
    }

}
