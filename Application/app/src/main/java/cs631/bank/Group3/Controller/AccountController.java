package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Account;

public class AccountController {
    private Connection con2=null;
    AccountController(){
        con2 = JdbcOracleConnection.getInstance().getDbConnection();
    }
    public Account getAllAccount(){
        try{
            Statement stmt = con2.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM account");
            while(rs.next()){ 
                System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDouble(4), rs.getDouble(5), rs.getDate(6));
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }

}
