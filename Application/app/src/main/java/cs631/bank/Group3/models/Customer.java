package cs631.bank.Group3.models;

public class Customer {
    int cssn;
    String c_firstname,c_lastname;
    int apartment_number,street_number;
    String city,state;
    int zip_code;

    public Customer( int cssn,String c_firstname,String c_lastname,int apartment_number,int street_number,String city,String state,int zip_code){
        this.cssn=cssn;
        this.c_firstname=c_firstname;
        this.c_lastname=c_lastname;
        this.apartment_number=apartment_number;
        this.street_number=street_number;
        this.city=city;
        this.state=state;
        this.zip_code=zip_code;
    }
}
