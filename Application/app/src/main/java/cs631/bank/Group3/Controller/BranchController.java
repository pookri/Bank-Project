package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Branch;

import java.sql.Connection;

public class BranchController {

    private Connection con3=null;
    BranchController(){
        con3 = JdbcOracleConnection.getInstance().getDbConnection();
    }
    public Branch getAllbBranch(){
        try{
            Statement stmt = con3.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM branch");
            while(rs.next()){ 
                System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            return new Branch(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getInt(9));
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }
    
}
