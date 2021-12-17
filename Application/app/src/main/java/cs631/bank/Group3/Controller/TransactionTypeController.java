package cs631.bank.Group3.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.TransactionType;

import java.sql.Connection;

public class TransactionTypeController {
    private Connection con1=null;
    

    public TransactionTypeController(){
       this.con1 = JdbcOracleConnection.getInstance().getDbConnection();
    }

    public TransactionTypeController(Connection con){ 
        this.con1 = con;
       
    }

    // public String insertAccount(String sctName, int someOther){ 
    //     final String INSERT_ACCOUNT ="INSERT into account (account_number,branch_id,acc_type,balance,interest_rate,create_date) VALUES(?,?,?,?,?,?)";
    //     return INSERT_ACCOUNT;

    // }
    
   public TransactionType getTransactionType(){ 
       try{
        Statement stmt = con1.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM transaction_type");
        while(rs.next()){ 
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getDouble(3) );  
        }
        return new TransactionType(rs.getString(1), rs.getString(2),rs.getDouble(3));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
   }

   public TransactionType updaTransactionType(String transaction_type,String transaction_name,double transaction_charge){
    try{
        Statement stmt = con1.createStatement();
        ResultSet rs=stmt.executeQuery("UPDATE transaction_type SET transaction_type=?,transaction_name=?,transaction_charge=?");
        while(rs.next()){ 
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getDouble(3) );  
        }
        return new TransactionType(rs.getString(1), rs.getString(2),rs.getDouble(3));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
   }
    
}
