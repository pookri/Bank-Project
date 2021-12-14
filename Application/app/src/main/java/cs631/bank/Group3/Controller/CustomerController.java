package cs631.bank.Group3.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.Instant;

import cs631.bank.Group3.JdbcOracleConnection;
import cs631.bank.Group3.models.Customer;
import cs631.bank.Group3.models.requests.CustomerReq;
import cs631.bank.Group3.models.responses.CustomerTableResp;

import java.sql.Connection;
import java.sql.Date;

public class CustomerController {
    private Connection con4=null;
    private final int overdraftLimit = 600;
    private final int loan_period = 3;
    
    public CustomerController(Connection connection){
        // con4 = JdbcOracleConnection.getInstance().getDbConnection();
        con4 = connection;
    }

    /**
     * Use this method to show data in customer table
     * @return
     */
    public List<CustomerTableResp> getAllCustomers(){
        List<CustomerTableResp> toRet = new ArrayList<>();
        try(Statement stmt = con4.createStatement()){
            
            ResultSet rs=stmt.executeQuery(   "SELECT c.cssn,c.c_firstname,c.c_lastname,c.apartment_number,c.street_name,c.city,c.state,c.zip_code, "+ 
            "COUNT(DISTINCT ac.account_number) AS num_of_accounts,COUNT(DISTINCT b.branch_id) AS Num_of_branches , "+ 
            "e.e_firstname ||' ' || e.e_lastname AS Personal_Banker "+ 
        "FROM customer c "+ 
        "LEFT OUTER JOIN customer_account ca ON c.cssn = ca.cssn "+ 
        "LEFT OUTER JOIN account ac ON ac.account_number = ca.account_number "+ 
        "LEFT OUTER JOIN branch b ON ac.branch_id = b.branch_id "+ 
        "LEFT OUTER JOIN assist ass ON ass.cssn=c.cssn "+ 
        "LEFT OUTER JOIN employee e ON e.employee_ssn=ass.essn "+ 
        "GROUP BY c.cssn,c.c_firstname,c.c_lastname,c.apartment_number,c.street_name,c.city,c.state,c.zip_code,e.e_firstname,e.e_lastname");
            while(rs.next()){ 
                toRet.add(new CustomerTableResp(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
                // System.out.println(rs.getInt(1) +" "+ rs.getString(2)+" "+ rs.getString(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDate(6) );  
            }
            // return new Customer(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8));
            } catch (SQLException e) {
                System.out.println(e);
                return toRet;
            }
            return toRet;
    }

    public boolean deleteCustomer(String custId){ 
        try(Statement stmt = con4.createStatement()){ 
            stmt.executeUpdate("DELETE from customer where cssn = "+ custId);
        }catch(SQLException e){ 
            return false;
        }
        return true;
    }

    public boolean editCustomer(Customer customer){ 
        
        try(Statement stmt = con4.createStatement()){ 
        
            stmt.executeUpdate("UPDATE customer " + 
            "SET c_firstname = " + "'" + customer.cFirstname + "'" + "," +
            "c_lastname = " + "'" + customer.cLastname + "'" + "," +  
            "apartment_number= "  + customer.apartmentNumber + "," +  
            "street_name = " + "'" + customer.streetName + "'" + "," +  
            "city = " + "'" + customer.city + "'" + "," +  
            "state = " + "'" + customer.state + "'" + "," +  
            "zip_code = " + "'" + customer.zipCode + "'"  +  
             " where cssn = " + "'" + customer.cssn + "'");
        }catch(SQLException e){ 
            return false;
        }
        return true;
    }

    public boolean addCustomer(CustomerReq req){ 

        try(Statement stmt = con4.createStatement()){ 
            if (req.type.equals("Single")) { 
                Customer cust = req.customers.get(0);
            //     stmt.executeUpdate("INSERT INTO customer (cssn,c_firstname,c_lastname,apartment_number,street_name,city,state,zip_code) "+ 
            // "VALUES ( "+ cust.cssn +" , "+ cust.cFirstname +" , "+ cust.cLastname +" , "+ cust.apartmentNumber +" , "+ 
            // cust.streetName +" , "+ cust.city +" , "+ cust.state +" , "+ cust.zipCode + " )");
            stmt.executeUpdate("INSERT INTO customer "+ 
            "VALUES ( "+ "'" +  cust.cssn + "'"  +","+ "'" + cust.cFirstname +"'"  +","+ "'" +cust.cLastname + "'" 
            +","+ cust.apartmentNumber +","+ 
            "'" + cust.streetName+ "'" +","+ "'" + cust.city + "'" +","+ "'" + cust.state + "'" +","+ cust.zipCode + ")");
                // customer entry 
                long randomDigit =  new java.util.Date().getTime();
                
                try { 
                    Thread.sleep(10);
                } catch (Exception e ){ 
                    System.out.println("Thread sleep error" );
                }
                
                long loanId = new java.util.Date().getTime();
                String randActNum = String.valueOf(randomDigit).substring(0, 9);
                 
                double interestRate = 0.0;
                if (req.accountType.equals("SVNGS")){ 
                    interestRate = 1.5;
                } 

                if (req.accountType.equals("LN")){ 
                    int loan_amount= req.initialDeposit;
                    double monthlyPay = loan_amount / loan_period;

                    ResultSet rs = stmt.executeQuery("SELECT br.assets " + " FROM branch br " + " WHERE br.branch_id = '" + req.branch +"'");
                    rs.next();
                    int assets = rs.getInt(1) - loan_amount ;

                    stmt.executeUpdate("UPDATE branch " + 
                        "SET assets = " + assets +
                        " where branch_id = " + "'" + req.branch + "'");

                    stmt.executeUpdate("INSERT into account(account_number,branch_id,acc_type,balance,interest_rate,create_date,overdraft_limit)"+
                                        " VALUES("+ randActNum +","+ "'" + req.branch + "'" +","+ "'"+req.accountType+"'"
                                        +","+ req.initialDeposit +","+ interestRate +
                                        ", CURRENT_TIMESTAMP ,"+ overdraftLimit +")");
                    stmt.executeUpdate("INSERT into customer_account (cssn,account_number,recent_access_date)"+ " VALUES("+
                                        "'" +cust.cssn +"'" +"," + randActNum +"," + " CURRENT_TIMESTAMP " + ")");
                                                        
                stmt.executeUpdate("INSERT into loan(loan_number,loan_original_amount,monthly_pay,account_number,remaining_balance,loan_period)"+
                " VALUES("+ loanId +"," + loan_amount +","+ monthlyPay + ","+ randActNum +","+ loan_amount  + "," + loan_period +")" );
                stmt.executeUpdate("INSERT INTO assist VALUES(" + "'" + req.employeeHelping + "'" + "," + "'" + cust.cssn + "'" +" )");
                }
            else{ 
                stmt.executeUpdate("INSERT into account(account_number,branch_id,acc_type,balance,interest_rate,create_date,overdraft_limit)"+
                 " VALUES("+ randActNum +","+ "'" + req.branch + "'" +","+ "'"+req.accountType+"'"
                  +","+ req.initialDeposit +","+ interestRate +
                  ", CURRENT_TIMESTAMP ,"+ overdraftLimit +")");

                // account entry -- Generate ActNUm (random) 
                stmt.executeUpdate("INSERT into customer_account (cssn,account_number,recent_access_date)"+ " VALUES("+
                "'" +cust.cssn +"'" +"," + randActNum +"," + " CURRENT_TIMESTAMP " + ")");

                stmt.executeUpdate("INSERT INTO assist VALUES(" + "'" + req.employeeHelping + "'" + "," + "'" + cust.cssn + "'" +" )");
            }
                // customer_account entry 
            } else if (req.type.equals("Join")){ 
                Customer cust = req.customers.get(0);
                Customer cust1 = req.customers.get(1);
                
                stmt.executeUpdate("INSERT INTO customer "+ 
            "VALUES ( "+ "'" +  cust.cssn + "'"  +","+ "'" + cust.cFirstname +"'"  +","+ "'" +cust.cLastname + "'" 
            +","+ cust.apartmentNumber +","+ 
            "'" + cust.streetName+ "'" +","+ "'" + cust.city + "'" +","+ "'" + cust.state + "'" +","+ cust.zipCode + ")");

            stmt.executeUpdate("INSERT INTO customer "+ 
            "VALUES ( "+ "'" +  cust1.cssn + "'"  +","+ "'" + cust1.cFirstname +"'"  +","+ "'" +cust1.cLastname + "'" 
            +","+ cust1.apartmentNumber +","+ 
            "'" + cust1.streetName+ "'" +","+ "'" + cust1.city + "'" +","+ "'" + cust1.state + "'" +","+ cust1.zipCode + ")");

                long randomDigit = new java.util.Date().getTime() + new Random().nextLong();
                String randActNum = String.valueOf(randomDigit).substring(0, 9);
                
                double interestRate = 0.0;
                if (req.accountType.equals("SVNGS")){ 
                    interestRate = 1.5;
                }

                stmt.executeUpdate("INSERT into account(account_number,branch_id,acc_type,balance,interest_rate,create_date,overdraft_limit)"+
                " VALUES("+ randActNum +","+ "'" + req.branch + "'" +","+ "'"+req.accountType+"'"
                 +","+ req.initialDeposit +","+ interestRate +
                 ", CURRENT_TIMESTAMP ,"+ overdraftLimit +")");
                                
                 stmt.executeUpdate("INSERT into customer_account (cssn,account_number,recent_access_date)"+ " VALUES("+
                "'" +cust.cssn +"'" +"," + randActNum +"," + " CURRENT_TIMESTAMP " + ")");

                stmt.executeUpdate("INSERT into customer_account (cssn,account_number,recent_access_date)"+ " VALUES("+
                "'" +cust1.cssn +"'" +"," + randActNum +"," + " CURRENT_TIMESTAMP " + ")");

                stmt.executeUpdate("INSERT INTO assist VALUES(" + "'" + req.employeeHelping + "'" + "," + "'" + cust.cssn + "'" +" )");

                stmt.executeUpdate("INSERT INTO assist VALUES(" + "'" + req.employeeHelping + "'" + "," + "'" + cust1.cssn + "'" +" )");

                // customer two entry
                // account - one entry 
                // customer_account two entry 
            }
           
        }  catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        //insert in to customer 
        // if loan 
        // calculate monthly pay .. amount / 12 
        // update balance (table) SET assets = assets - loan amount 
        // insert in to loan account 
        // 
        // insert into account 
        //
        return true;
    }
}
