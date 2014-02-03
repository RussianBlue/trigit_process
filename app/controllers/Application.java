package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import play.db.jpa.*;

public class Application extends Controller {
	
    // -- Authentication
    @Transactional
    public static class Login {

        public String user_id;
        public String password;
        
        public String validate() {
            if (User.authenticate(user_id, password) == null) {
              return "아이디와 비밀번호를 확인해주세요.";
            }
            return null;
		}
    }

    /**
     * Login page.
     */    
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    /**
     * Handle login form submission.
     */
    @Transactional
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {            
            session("user_id", loginForm.get().user_id);
            return redirect(
                routes.Projects.index()
            );
        }
    }

    /**
     * Logout and clean the session.
     */
    
    public static Result logout() {
        session().clear();
        flash("success", "로그아웃 되었습니다.");
        return redirect(
            routes.Application.login()
        );
    }
  
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
