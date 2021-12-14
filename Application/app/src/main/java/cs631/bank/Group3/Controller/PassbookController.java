package cs631.bank.Group3.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.net.SMTPAppender;
import cs631.bank.Group3.models.TransactionTypeEnum;
import cs631.bank.Group3.models.responses.PassbookResponse;
import cs631.bank.Group3.models.responses.SelectResult;

public class PassbookController {

    private Connection jdbcConnection;

    public PassbookController(Connection connection){ 
        this.jdbcConnection = connection;
    }


    public List<PassbookResponse> getPassbookResult(String accountNum, Date sinceDate){ 
        List<PassbookResponse> listOfRows = null;
       
        try(PreparedStatement ps = this.jdbcConnection.prepareStatement("Select * from Transaction_balance where TRANSACTION_ACCOUNT_NUMBER=" + accountNum + " AND transaction_time > ?")) { 
            // Statement stmt = this.jdbcConnection.createStatement();
            // PreparedStatement ps = this.jdbcConnection.prepareStatement("Select * from Transaction_balance where TRANSACTION_ACCOUNT_NUMBER=" + accountNum + " AND transaction_time > ?");
            // ResultSet rs = stmt.executeQuery("Select * from Transaction_balance where TRANSACTION_ACCOUNT_NUMBER=?" + accountNum + 
            // );
            ps.setDate(1, sinceDate);

            ResultSet rs = ps.executeQuery();

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
    
    // Name with Id 
    public List<SelectResult> customerInfo(){ 
        List<SelectResult> ret = new ArrayList<>();
        try (Statement stmt = this.jdbcConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT c.cssn,c.c_firstname,c.c_lastname FROM customer c");
            while(rs.next()){ 
                String fullname = rs.getString(2) + " " + rs.getString(3);
                ret.add(new SelectResult(fullname,rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ret;
        }
        return ret;
    }

    public List<SelectResult> allAccountsOfCust(String custId){ 
        List<SelectResult> ret = new ArrayList<>();
        try(Statement stmt = this.jdbcConnection.createStatement()){ 
            ResultSet rs = stmt.executeQuery("SELECT ac.account_number,ac.acc_type FROM account ac, customer_account ca WHERE ac.account_number=ca.account_number AND ca.cssn=" + custId);
            while(rs.next()){ 
                String label = rs.getString(2) + " - " + rs.getString(1);
                ret.add(new SelectResult(label, rs.getString(1)));
            }
        } catch(SQLException e){ 
            e.printStackTrace();
            return ret;
        }
        return ret;
    }
}
