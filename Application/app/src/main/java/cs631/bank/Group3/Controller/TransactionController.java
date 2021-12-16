package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs631.bank.Group3.models.Transaction;
import cs631.bank.Group3.models.requests.TransactionReq;
import cs631.bank.Group3.models.responses.AccountSelectResp;
import cs631.bank.Group3.models.responses.SelectResult;

import java.sql.Connection;

public class TransactionController {
    private Connection con=null;
    public TransactionController(Connection connection){
        this.con = connection;
    }
    public List<Transaction> getAllTransactions(){
        List<Transaction> ret = new ArrayList<>();
        try( Statement stmt = con.createStatement()){
           
            ResultSet rs=stmt.executeQuery("SELECT * FROM transaction ORDER BY transaction_time DESC");
            while(rs.next()){ 
                ret.add(new Transaction(rs.getInt(6),rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getTimestamp(4),rs.getDate(5)));
                // System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            // return new Transaction(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getTimestamp(5),rs.getDate(6));
            } catch (SQLException e) {
                System.out.println(e);
                return ret;
            }
            return ret;
    }

    /**
     * Get all the check and savings Account 
     * @return
     */
    public List<SelectResult> chkSvAccounts(){ 
        List<SelectResult> ret = new ArrayList<>();
        try (Statement stmt = this.con.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT ac.account_number,at.account_type,c.c_firstname,c.c_lastname " +
            "FROM account ac, account_type at, customer_account ca, customer c " +
            "where c.cssn = ca.cssn " +
            "AND ca.account_number = ac.account_number " +
            "AND ac.acc_type=at.account_type " +
            "AND at.account_type ='SVNGS' " +
            "UNION " +
            "SELECT ac.account_number,at.account_type,c.c_firstname,c.c_lastname " +
            "FROM account ac, account_type at, customer_account ca, customer c " +
            "where c.cssn = ca.cssn " +
            "AND ca.account_number = ac.account_number " +
            "AND ac.acc_type=at.account_type " +
            "AND at.account_type ='CKNGS'");
            while(rs.next()){ 

                String fullname = rs.getString(3) + " " + rs.getString(4);
                String account = rs.getString(1) + " - " + rs.getString(2);
                String label = account + ", " + fullname;
                ret.add( new SelectResult(label, rs.getString(1)) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ret;
        }
        return ret;   
    }

    public List<AccountSelectResp> getAllAccountsOfCustomer(String customerId, String branchId){ 
       try { 
        
        List<AccountSelectResp> toRet = new ArrayList<AccountSelectResp>();
        Statement statement = this.con.createStatement();
        ResultSet result = statement.executeQuery("select * from ");
        while(result.next()){
            toRet.add(new AccountSelectResp(result.getInt(1), result.getString(2), result.getString(3) ));
        }
        return toRet;
       } catch (SQLException e) { 
        System.out.println("Could not post transaction \n" );
        e.printStackTrace();
        return new ArrayList<>();
       }
    }

    public boolean postTransaction(TransactionReq request) { 
        
        try {
            Statement statement = this.con.createStatement();
            // INSERT INTO transaction() VALUES(12453986,'WD',20.0,CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval);      
            // if (request.fromAct != null){ 
            if (request.transactionType.equals("withdraw")){ 
                statement.executeUpdate("INSERT INTO transaction VALUES("+ request.fromAct + ",'WD',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval)");
            }
            // else if (request.toAct != null){ 
            else if (request.transactionType.equals("deposit")){ 
                statement.executeUpdate("INSERT INTO transaction VALUES("+ request.toAct + ",'DS',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval)");
            } else if ( request.transactionType.equals("transfer") ){ 
                statement.executeUpdate("INSERT INTO transaction VALUES("+ request.fromAct + ",'WD',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval)");
                statement.executeUpdate("INSERT INTO transaction VALUES("+ request.toAct + ",'DS',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval)");
            }
            return true;
        } catch (SQLException e) {
            
            System.out.println("Could not post transaction \n" );
            e.printStackTrace();
            return false;
        }
    
    }
    
}
