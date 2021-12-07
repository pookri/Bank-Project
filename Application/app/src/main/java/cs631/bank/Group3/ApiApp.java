package cs631.bank.Group3;

import cs631.bank.Group3.Controller.TransactionTypeController;
import io.javalin.Javalin;

public class ApiApp{ 
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            try(JdbcOracleConnection connection = JdbcOracleConnection.getInstance()){ 
                TransactionTypeController controller = new TransactionTypeController(connection.getDbConnection());
                controller.getTransactionType();
            } catch(Exception e){ 
                System.out.println(e);
            }
            ctx.result("Hello World");
        }
        );

    }
}