package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import play.Logger;

public class Application extends Controller {
    // -- Javascript routing
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
            	controllers.routes.javascript.Projects.add()
            )
        );
    }
}
