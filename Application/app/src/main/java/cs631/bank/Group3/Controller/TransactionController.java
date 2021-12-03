package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Transaction;

import java.sql.Connection;

public class TransactionController {
    private Connection con3=null;
    TransactionController(){
        con3 = JdbcOracleConnection.getInstance().getDbConnection();
    }
    public Transaction getAllbBranch(){
        try{
            Statement stmt = con3.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM transaction");
            while(rs.next()){ 
                System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            return new Transaction(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getTimestamp(5),rs.getDate(6));
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }
    
}
