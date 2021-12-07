package cs631.bank.Group3.models.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class AccountSelectResp {

    int account_number;
    String branch_id;
    String acc_type;

    public AccountSelectResp(int account_number,
    String branch_id,
    String acc_type){ 
        this.account_number = account_number;
        this.branch_id = branch_id;
        this.acc_type = acc_type;
    }
    
}
