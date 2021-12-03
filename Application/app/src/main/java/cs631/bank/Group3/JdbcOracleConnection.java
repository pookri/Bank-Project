package cs631.bank.Group3;

import java.sql.*;

public class JdbcOracleConnection{  
   
    private static String userName = "ps245";
    private static String password = "Krupal25081998?";
    private static String dataBaseURL = "jdbc:oracle:thin:@prophet.njit.edu:1521:course";
    private static JdbcOracleConnection jdbcConnectionInstance;
    private Connection dbConnection;

    private JdbcOracleConnection(){ 
        this.dbConnection = this.createConnection();
    }  
    
    public static JdbcOracleConnection getInstance(){ 
    
        if (jdbcConnectionInstance == null){ 
            JdbcOracleConnection.jdbcConnectionInstance = new JdbcOracleConnection();
        }
        return JdbcOracleConnection.jdbcConnectionInstance;
    }
    
    private Connection createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(dataBaseURL, userName, password);
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null; 
        }
    }

    public Connection getDbConnection(){ 
        return this.dbConnection;
    }
}  


 
    // con=DriverManager.getConnection(  
    // "jdbc:oracle:thin:@prophet.njit.edu:1521:course","ps245","Krupal25081998?");  
    // System.out.println("Is connection open " + con.isValid(100) );
  

// Statement stmt=con.createStatement();  

// ResultSet rs=stmt.executeQuery("SELECT * FROM transaction_type");

// System.out.println("did I get results " + rs.next());
// while(rs.next()){ 
//     System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) );  
// }
  
// }catch(Exception e){ 
//     System.out.println(e);
//     con.close();
// } finally{ 
//     con.close();
// }
// return con;  
// }  