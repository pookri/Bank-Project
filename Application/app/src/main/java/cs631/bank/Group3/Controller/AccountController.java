package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Account;

import java.sql.Connection;

public class AccountController {
    private Connection con2=null;
    public AccountController(Connection connection){
        con2 = connection;
    }
    public List<Account> getAllAccount(){
        List<Account> toRet = new ArrayList<>();
        try( Statement stmt = con2.createStatement()){

            ResultSet rs=stmt.executeQuery("SELECT a.account_number, a.branch_id, a.acc_type, a.balance, a.interest_rate, a.create_date, ca.recent_access_date FROM account a, customer_account ca where ca.account_number=a.account_number");
            while(rs.next()){ 
                toRet.add( new Account(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDouble(4), rs.getDate(5), rs.getDate(6) ) );
                // System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            // return 
            } catch (SQLException e) {
                System.out.println(e);
                return toRet;
            }
            return toRet;
    }

}
