package cs631.bank.Group3.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs631.bank.Group3.models.TransactionTypeEnum;
import cs631.bank.Group3.models.responses.PassbookResponse;

public class PassbookController {

    private Connection jdbcConnection;

    public PassbookController(Connection connection){ 
        this.jdbcConnection = connection;
    }


    public List<PassbookResponse> getPassbookResult(Date sinceDate, String accountNum){ 
        List<PassbookResponse> listOfRows = null;
        try { 
            Statement stmt = this.jdbcConnection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Transaction_balance where TRANSACTION_ACCOUNT_NUMBER=" + accountNum);
            listOfRows = new ArrayList<PassbookResponse>();
            while(rs.next()){
                 String t_type = rs.getString(3);
                 if (TransactionTypeEnum.valueOf(t_type).isDebit){ 
                    listOfRows.add(
                        new PassbookResponse(rs.getLong(1), rs.getString(3), rs.getString(4), rs.getDate(5) , rs.getDouble(6), 0.0, rs.getDouble(7))
                    );
                 } else { 
                    listOfRows.add(
                        new PassbookResponse(rs.getLong(1), rs.getString(3), rs.getString(4), rs.getDate(5) , 0.0, rs.getDouble(6), rs.getDouble(7))
                    );
                 }
                // new PassbookResponse(rs.getString(1), transactionCode, transactionName, debits, credits, balance)
                // return new Transaction(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getTimestamp(5),rs.getDate(6));
            }
        } catch(SQLException e){ 
            System.out.println(e);
        } 
        return listOfRows;
    }
    
}
