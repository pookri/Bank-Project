package cs631.bank.Group3.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        listOfRows = new ArrayList<PassbookResponse>();

        try (PreparedStatement ps2 = this.jdbcConnection.prepareStatement("select changed_balance from passbook_view where TRANSACTION_ACCOUNT_NUMBER= " + accountNum + " AND transaction_time < ? AND ROWNUM =1 ORDER BY transaction_time DESC")){ 
            ps2.setDate(1,sinceDate);
            ResultSet rs = ps2.executeQuery();
            // ResultSet rs = stmt.executeQuery("Select b.changed_balance from transaction t, balance_logs b where t.TRANSACTION_ACCOUNT_NUMBER= " + 163952696 + " AND transaction_time < ? AND t.transaction_id=b.transaction_id AND ROWNUM =1" );
            if (rs.next()){ 
                listOfRows.add(new PassbookResponse(398479873498L, null, "Balance Forward", sinceDate, 0, 0, rs.getDouble(1)));
            }
        } catch(Exception e){ 
            System.out.println("Didn't get passbook forward");
        }

        try(PreparedStatement ps = this.jdbcConnection.prepareStatement("select * from passbook_view where TRANSACTION_ACCOUNT_NUMBER=" + accountNum + " AND transaction_time >= ? ORDER BY transaction_time")) { 
            // Statement stmt = this.jdbcConnection.createStatement();
            // PreparedStatement ps = this.jdbcConnection.prepareStatement("Select * from Transaction_balance where TRANSACTION_ACCOUNT_NUMBER=" + accountNum + " AND transaction_time > ?");
            // ResultSet rs1 = stmt.executeQuery("Select balance from passbook_view where TRANSACTION_ACCOUNT_NUMBER=" + accountNum );

            // stmt.close();
            ps.setDate(1, sinceDate);

            ResultSet rs = ps.executeQuery();

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
                //  System.out.println("passbook " + rs.getLong(1) + rs.getString(3) + rs.getString(4) + rs.getDate(5) + 0.0 + rs.getDouble(6) + rs.getDouble(7));
                // new PassbookResponse(rs.getString(1), transactionCode, transactionName, debits, credits, balance)
                // return new Transaction(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getTimestamp(5),rs.getDate(6));
            }
        } catch(SQLException e){ 
            System.out.println(e);
        }
        return listOfRows;
    }

    public List<String> getListOfOwners(String actId){ 
      
    List<String> ret = new ArrayList<>();
    try (Statement stmt = this.jdbcConnection.createStatement()) {
        ResultSet rs = stmt.executeQuery("SELECT c.c_firstname,c.c_lastname " + 
        "FROM  customer_account ca, customer c WHERE c.cssn = ca.cssn AND ca.account_number =" + actId);
        while(rs.next()){ 
            String fullname = rs.getString(1) + " " + rs.getString(2);
            ret.add(fullname);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return ret;
    }
    return ret;
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
