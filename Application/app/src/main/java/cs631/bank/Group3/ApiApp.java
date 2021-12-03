package cs631.bank.Group3;

import io.javalin.Javalin;

public class ApiApp{ 
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            ctx.result("Hello World");
        }
            );

    }
}