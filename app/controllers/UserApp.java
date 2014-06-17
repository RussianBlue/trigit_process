package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.signup.signUp;
import views.html.login;
import play.Logger;

public class UserApp extends Controller {

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

    public static Result SignUp() {
        return ok(signUp.render(form(User.class)));
    }

    /**
     * 사용자 가입 처리
     * 입력된 데이터 유효성 검증에 실패하면 bad request 응답
     * 사용자 정보를 저장, 로그인 쿠기 생성 후 메인 페이지로 이동
     *
     * @return
     */
    public static Result newUser(){
        Form<User> newUserForm = form(User.class).bindFromRequest();
        //validate(newUserForm);

        if(newUserForm.hasErrors()){
            return badRequest(signUp.render(newUserForm));
        }else{
            session("user_id", newUserForm.get().user_id);
            return redirect(
                routes.Projects.index()
            );
        }
    }

    /*
     * 사용자 가입 입력 폼 유효성 체크
     */
    private static void validate(Form<User> newUserForm) {
        // loginId가 빈 값이 들어오면 안된다.
        if (newUserForm.field("user_id").value().trim().isEmpty()) {
            flash("success", "로그아웃 되었습니다.");
        }

        if (newUserForm.field("user_id").value().contains(" ")) {
            newUserForm.reject("user_id", "아이디에 빈 칸은 허용되지 않습니다.");
        }

        // password가 빈 값이 들어오면 안된다.
        if (newUserForm.field("password").value().trim().isEmpty()) {
            newUserForm.reject("password", "비밀번호를 입력해 주세요.");
        }

        // password가 빈 값이 들어오면 안된다.
        if (newUserForm.field("name").value().trim().isEmpty()) {
            newUserForm.reject("name", "이름을 입력해 주세요.");
        }
    }

    /*
     * 신규 가입 사용자 생성
     */
    private static User createNewUser(User user) {        
        return user;
    }
}