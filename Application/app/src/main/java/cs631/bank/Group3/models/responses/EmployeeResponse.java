package cs631.bank.Group3.models.responses;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class EmployeeResponse {
    String employee_ssn;
    String branch_id;
    String fullName;
    String mobile_number;
    Date start_date;
    int employeePeriod;
    int assistedNum;

    public EmployeeResponse(String employee_ssn,
                            String branch_id,
                            String fullName,
                            String mobile_number,
                            Date start_date,
                            int employeePeriod, 
                            int assistedNum
                        ){
        this.employee_ssn=employee_ssn;
        this.branch_id=branch_id;
        this.fullName = fullName;
        this.mobile_number=mobile_number;
        this.start_date=start_date;
        this.employeePeriod = employeePeriod;
        this.assistedNum = assistedNum;
    }
}
