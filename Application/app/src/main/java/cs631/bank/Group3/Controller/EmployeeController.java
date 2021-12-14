package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Employee;
import cs631.bank.Group3.models.responses.EmployeeResponse;
import cs631.bank.Group3.models.responses.SelectResult;

import java.sql.Connection;

public class EmployeeController {
    private Connection con5=null;
    
    public EmployeeController(Connection connection){
        // con5 = JdbcOracleConnection.getInstance().getDbConnection();
        con5 = connection;
    }
    public Employee getAllbBranch(){
        try(Statement stmt = con5.createStatement()){
    
            ResultSet rs=stmt.executeQuery("SELECT * FROM branch");
            while(rs.next()){ 
                System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            return new Employee(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDate(6));
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }

    public List<SelectResult> listOfAssists(String branch){ 
        List<SelectResult> ret = new ArrayList<>();

        try(Statement stmt = con5.createStatement()){ 
            //todo: update this query to give only for branch
            ResultSet rs = stmt.executeQuery("SELECT employee_ssn, e_firstname, e_lastname FROM employee WHERE branch_id='"+branch +"'");
            while(rs.next()){ 
                ret.add(new SelectResult(rs.getString(2) + " " + rs.getString(3),rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return ret;
        }
        return ret;
    }

    public List<EmployeeResponse> employeeResult(){ 
        List<EmployeeResponse> ret = new ArrayList<EmployeeResponse>();
        try( Statement stmt = con5.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT employee_ssn,branch_id,e_firstname,e_lastname,mobile_number,start_date,Employment_period,COUNT(DISTINCT c.cssn) AS Num_AssistedCustomer " + 
            "FROM Employee_view e" +  
            "LEFT OUTER JOIN  assist a ON a.essn=e.employee_ssn " +  
            "LEFT OUTER JOIN customer c ON c.cssn=a.cssn " +  
            "GROUP BY employee_ssn,branch_id,e_firstname,e_lastname,mobile_number,start_date,Employment_period"
            );
            while(rs.next()){ 
               ret.add(new EmployeeResponse(
                   rs.getString(1), 
                   rs.getString(2), 
                   rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return ret;
        }
        return ret;
    }
}
