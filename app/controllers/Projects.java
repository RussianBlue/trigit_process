package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import java.util.*;

import models.*;

import views.html.*;
import views.html.projects.*;

/**
 * Manage projects related operations.
 */
@Security.Authenticated(Secured.class)
public class Projects extends Controller {
  
    /**
     * Display the dashboard.
     */
    public static Result index() {
        return ok(
            
        );
    }

    // -- Projects

    /**
     * Add a project.
     */
    public static Result add() {
        /*
        Project newProject = Project.create(
            "New project", 
            form().bindFromRequest().get("group"),
            request().username()
        );
        */
        return ok();
    }
}

