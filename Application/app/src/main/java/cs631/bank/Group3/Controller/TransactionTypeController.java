package cs631.bank.Group3.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.TransactionType;

public class TransactionTypeController {
    private Connection con1=null;

    TransactionTypeController(){
        con1 = JdbcOracleConnection.getInstance().getDbConnection();
    }

    // public String insertAccount(String sctName, int someOther){ 
    //     final String INSERT_ACCOUNT ="INSERT into account (account_number,branch_id,acc_type,balance,interest_rate,create_date) VALUES(?,?,?,?,?,?)";
    //     return INSERT_ACCOUNT;

    // }
    
    

   public TransactionType getTransactionType(){ 
       try{
        Statement stmt = con1.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM transaction_type");
        System.out.println("did I get results " + rs.next());
        while(rs.next()){ 
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getDouble(3) );  
        }
        return new TransactionType(rs.getString(1), rs.getString(2),rs.getDouble(3));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
   }




    public static void main(String[] args) {

        

        // //Statement stmt=con.createStatement();  

        // ResultSet rs=stmt.executeQuery("SELECT * FROM transaction_type");

        // System.out.println("did I get results " + rs.next());
        // while(rs.next()){ 
        //     System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) );  
        // }
        
    }

    
}
