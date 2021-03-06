package cs631.bank.Group3.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.common.cache.Cache;

import cs631.bank.Group3.models.Branch;
import cs631.bank.Group3.models.responses.BranchResponse;
import cs631.bank.Group3.models.responses.SelectResult;

import java.sql.Connection;

public class BranchController {

    private Connection con3=null;
    public BranchController(Connection connection){
        con3 = connection;
    }
    public List<BranchResponse> getAllbBranch(){
        try(Statement stmt = con3.createStatement()) {
            // ResultSet rs=stmt.executeQuery("SELECT * FROM branch");
            ResultSet rs = stmt.executeQuery("SELECT br.branch_id,  br.branch_name, br.city, br.assets,COUNT(e.employee_ssn) AS Num_of_Employees FROM branch br,employee e where br.branch_id=e.branch_id GROUP BY br.branch_id, br.branch_name, br.city, br.assets");
            List<BranchResponse> branches = new ArrayList<>();
            while(rs.next()){ 
                branches.add( new BranchResponse(rs.getString(1), rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5)) );
            }
            return branches;
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
    }

    /**
     * This is used to show branches in customer creatation 
     * @return
     */
    public List<SelectResult> getBranchIds(){ 
        List<SelectResult> ret = new ArrayList<>();

        try(Statement stmt = con3.createStatement()){ 
            ResultSet rs = stmt.executeQuery("SELECT b.branch_id ,b.branch_name FROM branch b");
            while(rs.next()){ 
                ret.add(new SelectResult(rs.getString(2),rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return ret;
        }
        return ret;
    }
    
}
