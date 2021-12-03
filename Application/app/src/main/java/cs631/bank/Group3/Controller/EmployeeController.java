package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Employee;

public class EmployeeController {
    private Connection con5=null;
    EmployeeController(){
        con5 = JdbcOracleConnection.getInstance().getDbConnection();
    }
    public Employee getAllbBranch(){
        try{
            Statement stmt = con5.createStatement();
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
}
