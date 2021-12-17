package cs631.bank.Group3.models.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CustomerTableResp {
    String cssn;
    String cFirstname;
    String cLastname;
    String apartmentNumber;
    String streetName;
    String city;
    String state;
    int zipCode;
    int numOfAccounts;
    int numOfBranches;
    String personalBanker; 

    public CustomerTableResp(
        String cssn,
    String cFirstname,
    String cLastname,
    String apartmentNumber,
    String streetName,
    String city,
    String state,
    int zipCode,
    int numOfAccounts,
    int numOfBranches,
    String personalBanker
    ){ 
        this.cssn = cssn;
        this.cFirstname = cFirstname;
        this.cLastname= cLastname;
        this.apartmentNumber = apartmentNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.numOfAccounts = numOfAccounts;
        this.numOfBranches = numOfBranches;
        this.personalBanker = personalBanker; 
    }


}
