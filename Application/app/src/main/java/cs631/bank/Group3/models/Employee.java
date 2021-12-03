package cs631.bank.Group3.models;

import java.sql.Date;

public class Employee {
    int employee_ssn;
    String branch_id;
    String e_firstname;
    String e_lastname;
    int mobile_number;
    Date start_date;

    public Employee(int employee_ssn,String branch_id,String e_firstname,String e_lastname,int mobile_number,Date start_date){
        this.employee_ssn=employee_ssn;
        this.branch_id=branch_id;
        this.e_firstname=e_firstname;
        this.e_lastname=e_lastname;
        this.mobile_number=mobile_number;
        this.start_date=start_date;
    }
}
