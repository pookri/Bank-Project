package cs631.bank.Group3.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Account {
    
    String account_number;
    String branch_id;
    String acc_type;
    double balance;
    double interest_rate;
    Date create_date;
    Date recentAccessDate;
    String fullName;

    public Account(String account_number,String branch_id,String acc_type,double balance,Date create_date, Date recentAccessDate, String fullName){
        this.account_number=account_number;
        this.branch_id=branch_id;
        this.acc_type=acc_type;
        this.balance=balance;
        // this.interest_rate=interest_rate;
        this.create_date=create_date;
        this.recentAccessDate = recentAccessDate;
        this.fullName = fullName;
    }
}
