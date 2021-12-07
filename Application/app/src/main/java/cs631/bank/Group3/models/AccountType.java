package cs631.bank.Group3.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class AccountType {
    String account_type;
    String accounttype_name;

    public AccountType(String account_type,String accounttype_name){
        this.account_type=account_type;
        this.accounttype_name=accounttype_name;
    }
}
