package cs631.bank.Group3.models.requests;

import java.util.List;

import cs631.bank.Group3.models.Customer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CustomerReq {
    public String type; // single/joint
    public List<Customer> customers; // ssn, name, ... 
    public String accountType; // Checking, Savings
    public String employeeHelping; // id
    public int initialDeposit; // 897
    public int loanAmount; // 987
    public String branch; // id

    public CustomerReq(){ 

    }

    public CustomerReq(String type, List<Customer> customers, String accountType, String employeeHelping, String branch, int loanAmount, int initialDeposit){
        this.type = type;
        this.customers = customers;
        this.accountType = accountType;
        this.employeeHelping = employeeHelping;
        this.branch = branch;
        this.loanAmount = loanAmount;
        this.initialDeposit = initialDeposit;
    }
}
