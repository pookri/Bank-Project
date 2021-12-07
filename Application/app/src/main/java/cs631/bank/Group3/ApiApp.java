package cs631.bank.Group3;

import cs631.bank.Group3.Controller.TransactionTypeController;
import cs631.bank.Group3.models.AccountType;
import cs631.bank.Group3.models.Loan;
import io.javalin.Javalin;
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
        Javalin app = Javalin.create(config -> { 
            config.registerPlugin(getConfiguredOpenApiPlugin());
        })
        .start(7000);
        
        app.stop();
        app.get("/test", ctx -> {
            try(JdbcOracleConnection connection = JdbcOracleConnection.getInstance()){ 
                TransactionTypeController controller = new TransactionTypeController(connection.getDbConnection());
                controller.getTransactionType();
            } catch(Exception e){ 
                System.out.println(e);
            }
            ctx.result("Hello World");
        }
        );

        app.get("/customerInfo", ctx -> { 
            
        });
        app.get("/actNum/{cid}", ctx -> { 
            List<AccountType> accountTypes = new ArrayList<>();
            AccountType at = new AccountType(ctx.pathParam("cid"), "Test");
            accountTypes.add(at);
            ctx.json(accountTypes);
        });

        app.get("/passbook/", ctx -> { 
            
        });

        app.stop();

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