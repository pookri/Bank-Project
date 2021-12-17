package cs631.bank.Group3.models.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class BranchResponse {
    String id;
    String name;
    String city;
    double assets;
    int numOfEmployees;

    public BranchResponse(
        String id,
        String name,
        String city,
        double assets,
        int numOfEmployees
    ){ 
        this.id = id;
        this.name = name;
        this.city = city;
        this.assets = assets;
        this.numOfEmployees = numOfEmployees;
    }
}
