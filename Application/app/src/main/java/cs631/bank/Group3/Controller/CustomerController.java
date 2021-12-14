package cs631.bank.Group3.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Customer;

import java.sql.Connection;

public class CustomerController {
    private Connection con4=null;
    CustomerController(){
        con4 = JdbcOracleConnection.getInstance().getDbConnection();
    }
    public Customer getAllCustomers(){
        try{
            Statement stmt = con4.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM customer");
            while(rs.next()){ 
                System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            return new Customer(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8));
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }
}
