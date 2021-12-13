package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs631.bank.Group3.models.Transaction;
import cs631.bank.Group3.models.requests.TransactionReq;
import cs631.bank.Group3.models.responses.AccountSelectResp;

import java.sql.Connection;

public class TransactionController {
    private Connection con=null;
    public TransactionController(Connection connection){
        this.con = connection;
    }
    public Transaction getAllbBranch(){
        try{
            Statement stmt = con.createStatement();
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
            if (request.fromAct != null){ 
                statement.executeQuery("INSERT INTO transaction() VALUES("+ request.fromAct + ",'WD',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval);");
            } else if (request.toAct != null){ 
                statement.executeQuery("INSERT INTO transaction() VALUES("+ request.toAct + ",'DS',"+ request.amount+ ",CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval);");
            }
            return true;
        } catch (SQLException e) {
            
            System.out.println("Could not post transaction \n" );
            e.printStackTrace();
            return false;
        }
    
    }
    
}
