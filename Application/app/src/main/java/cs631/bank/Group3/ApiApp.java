package cs631.bank.Group3;

import cs631.bank.Group3.Controller.PassbookController;
import cs631.bank.Group3.Controller.TransactionController;
import cs631.bank.Group3.Controller.TransactionTypeController;
import cs631.bank.Group3.models.AccountType;
import cs631.bank.Group3.models.Loan;
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

import java.util.ArrayList;
import java.util.List;


public class ApiApp{ 
    public static void main(String[] args) {

        JdbcOracleConnection connection = JdbcOracleConnection.getInstance();
        TransactionController transactionController= new TransactionController(connection.getDbConnection());

        Javalin app = Javalin.create(config -> { 
            config.registerPlugin(getConfiguredOpenApiPlugin());
        }).events(
            event -> { 
                event.serverStopping(() -> {
                    connection.close();
                });
            }
        )
        .start(7000);

        app.get("/customerInfo", ctx -> { 

        });
        app.get("/accountNum/{cid}", ctx -> { 
            List<AccountType> accountTypes = new ArrayList<>();
            AccountType at = new AccountType(ctx.pathParam("cid"), "Test");
            accountTypes.add(at);
            ctx.json(accountTypes);
        });

        app.get("/passbook/{actNum}", ctx -> { 
            PassbookController controller = new PassbookController(connection.getDbConnection());
            List<PassbookResponse> result = controller.getPassbookResult(null, ctx.pathParam("actNum"));
            ctx.json(result);
        });

        app.get("/accounts/{custId}", ctx -> {
            // todo: Get all accounts of a customer write query
            transactionController.getAllAccountsOfCustomer(ctx.pathParam("custId"));
        });

        app.post("/transaction", ctx -> { 
            TransactionReq req = ctx.bodyAsClass(TransactionReq.class);
            transactionController.postTransaction(req);
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