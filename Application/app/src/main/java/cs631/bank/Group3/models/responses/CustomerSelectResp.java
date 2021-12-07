package cs631.bank.Group3.models.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CustomerSelectResp {
    String fullName;
    String id;

    public CustomerSelectResp(String fullName, String id){ 
        this.fullName = fullName;
        this.id = id;
    }
}
