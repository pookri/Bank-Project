package cs631.bank.Group3;

import cs631.bank.Group3.Controller.AccountController;
import cs631.bank.Group3.Controller.BranchController;
import cs631.bank.Group3.Controller.CustomerController;
import cs631.bank.Group3.Controller.EmployeeController;
import cs631.bank.Group3.Controller.PassbookController;
import cs631.bank.Group3.Controller.TransactionController;
import cs631.bank.Group3.Controller.TransactionTypeController;
import cs631.bank.Group3.models.AccountType;
import cs631.bank.Group3.models.Customer;
import cs631.bank.Group3.models.Employee;
import cs631.bank.Group3.models.Loan;
import cs631.bank.Group3.models.requests.CustomerReq;
import cs631.bank.Group3.models.requests.TransactionReq;
import cs631.bank.Group3.models.responses.PassbookResponse;
import io.javalin.Javalin;
import io.javalin.core.event.EventHandler;
import io.javalin.core.event.EventListener;
import io.javalin.core.event.EventManager;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ApiApp{ 
    public static void main(String[] args) {
        String userName = args[0];
        String pwd = args[1];
        String url = args[2];
        JdbcOracleConnection connection = JdbcOracleConnection.firstInstance(userName, pwd, url);
        // JdbcOracleConnection connection = JdbcOracleConnection.getInstance();

        TransactionController transactionController= new TransactionController(connection.getDbConnection());
        BranchController branchController = new BranchController(connection.getDbConnection());
        PassbookController passbookController = new PassbookController(connection.getDbConnection());
        CustomerController customerController = new CustomerController(connection.getDbConnection());

        Javalin app = Javalin.create(config -> { 
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.enableCorsForAllOrigins();
        }).events(
            event -> { 
                event.serverStopping(() -> {
                    connection.close();
                });
            }
        )
        .start(7000);

        app.get("/branches", ctx -> {
            ctx.json(branchController.getAllbBranch());
        } );

        // 
        app.get("/employees", ctx -> {
             EmployeeController controller = new EmployeeController(connection.getDbConnection());
            ctx.json(controller.employeeResult());
        });

        app.get("/employee/assists/{branchId}", ctx -> { 
            EmployeeController controller = new EmployeeController(connection.getDbConnection());  
            ctx.json(controller.listOfAssists(ctx.pathParam("branchId")));
        });

        app.post("/employee", ctx -> {

        });

        app.post("/branch", ctx -> {
            
        });

        app.delete("/customer/{custId}", ctx -> { 
            customerController.deleteCustomer(ctx.pathParam("custId"));
        });

        app.put("/customer", ctx -> { 
            Customer cust = ctx.bodyAsClass(Customer.class);
            ctx.json(customerController.editCustomer(cust) );

        });

        app.post("/customer", ctx -> {
            CustomerReq req = ctx.bodyAsClass(CustomerReq.class);
            boolean b = customerController.addCustomer(req);
            ctx.json(b);
        });

        app.get("/customers", ctx -> { 
            ctx.json(customerController.getAllCustomers());
        });

        app.get("/branchIds/", ctx -> { 
            ctx.json(branchController.getBranchIds());
        });

        app.get("/customerInfo", ctx -> { 
            ctx.json(passbookController.customerInfo());
        });
        
        app.get("/branchInfo/{custId}", ctx -> { 
            
        });

        app.get("/getChkSvActs/", ctx -> { 
            ctx.json(transactionController.chkSvAccounts());
        });

        app.get("/passbook/listOfOwners/{actId}", ctx -> { 
            String actId = ctx.pathParam("actId");
            ctx.json(passbookController.getListOfOwners(actId));
        });

        app.get("/passbook/{actNum}/{time}", ctx -> { 
            
            // Instant date = Instant.ofEpochMilli(Long.parseLong(ctx.pathParam("time")));
            
            List<PassbookResponse> result = passbookController.getPassbookResult(ctx.pathParam("actNum"), new java.sql.Date(Long.parseLong(ctx.pathParam("time"))));
            ctx.json(result);
        });

        app.get("/accountsInfo/{custId}/{branchId}", ctx -> {
            // todo: Get all accounts of a customer write query
            transactionController.getAllAccountsOfCustomer(ctx.pathParam("custId"), ctx.pathParam("branchId"));
        });

        app.get("/accountsInfo/{custId}/", ctx -> {
            ctx.json(passbookController.allAccountsOfCust(ctx.pathParam("custId")));
        });

        // get values to show in accounts table
        app.get("/accounts/", ctx -> { 
            AccountController actCon = new AccountController(connection.getDbConnection());
            ctx.json( actCon.getAllAccount() );
        });

        app.post("/transaction", ctx -> { 
            TransactionReq req = ctx.bodyAsClass(TransactionReq.class);
            Boolean b = transactionController.postTransaction(req);
            ctx.json(b);
        });

        app.get("/transactions", ctx -> { 
            ctx.json(transactionController.getAllTransactions());
        });

    }

    private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version("1.0").description("User API");
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor("io.javalin.example.java")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .reDoc(new ReDocOptions("/redoc")) // endpoint for redoc
                .defaultDocumentation(doc -> {
                    // doc.json("500");
                    // doc.json("503");
                });
        return new OpenApiPlugin(options);
    }
}