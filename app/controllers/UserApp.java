package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.signup.*;
import views.html.login;
import play.Logger;

public class UserApp extends Controller {
	
    // -- Authentication    
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
            routes.UserApp.login()
        );
    }

    // -- Authentication    
    final static Form<User> signupForm = form(User.class);

    public static Result SignUp(){
        return ok(signUp.render(signupForm));
    }
}