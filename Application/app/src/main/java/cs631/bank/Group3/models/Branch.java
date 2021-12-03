package cs631.bank.Group3.models;

public class Branch {
    String branch_id;
    String branch_name;
    int manager_ssn;
    int assistant_ssn;
    double assets;
    String address;
    String city;
    String state;
    int zip_code;
    public Branch(String branch_id,String branch_name,int manager_ssn,int assistant_ssn,double assets,String address,String city,String state,int zip_code){
        this.branch_id=branch_id;
        this.branch_name=branch_name;
        this.manager_ssn=manager_ssn;
        this.assistant_ssn=assistant_ssn;
        this.assets=assets;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip_code=zip_code;
    }
}
