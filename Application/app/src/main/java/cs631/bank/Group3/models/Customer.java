package cs631.bank.Group3.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Customer {
    public int cssn;
    public String cFirstname,cLastname;
    public int apartmentNumber,streetNumber;
    public String streetName;
    public String city,state;
    public int zipCode;

    public Customer(){ 

    }

    public Customer( int cssn,String c_firstname,String c_lastname,int apartment_number,int street_number,String city,String state,int zip_code){
        this.cssn=cssn;
        this.cFirstname=c_firstname;
        this.cLastname=c_lastname;
        this.apartmentNumber=apartment_number;
        this.streetNumber=street_number;
        this.city=city;
        this.state=state;
        this.zipCode=zip_code;
    }
}
