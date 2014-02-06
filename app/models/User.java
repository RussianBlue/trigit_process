package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.Logger;

/**
 * User entity managed by Ebean
 */
@Entity
@Table(name="users")
public class User extends Model{

    private static final long serialVersionUID = 1L;
    @Id
    public Long id;
    
    @Constraints.Required
    @Formats.NonEmpty
    public String user_id;
    
    @Constraints.Required
    public String password;
    
    @Constraints.Required        
    public String name;
    
    @Constraints.Pattern("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$")
    public String email;
   
    @Constraints.Required
    public String project_id;
   
    @Constraints.Required
    public String phone_number;

    @Formats.DateTime(pattern="yyyy-MM-dd")    
    public Date created_at;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date last_login_at;

    @Constraints.Required
    public Long sign_in_count;

    public User() {}
    
    public User(String user_id, String password, String email, String project_id, String phone_number,Date created_at, Date last_login_at, Long sign_in_count ) {
        this.user_id = user_id;
        this.password = password;
        this.email = email;
        this.project_id = project_id;
        this.phone_number = phone_number;
        this.created_at = created_at;
        this.last_login_at = last_login_at;
        this.sign_in_count = sign_in_count;
    }

    // -- Queries
    
    public static Model.Finder<Long,User> find = new Model.Finder<Long,User>(Long.class, User.class);
    
    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static User findById(Long id){
        return find.byId(id);
    }

    public static User findByUserId(String user_id){
        return find.where().eq("user_id", user_id).findUnique();
    }

    public static User findBySignUpCount(Long id){
        return find.byId(id);
    }
    
    /**
     * Authenticate a User.
     */
    public static User authenticate(String user_id, String password) {
        return find.where()
            .eq("user_id", user_id)
            .eq("password", password)
            .findUnique();
    }
    
    // --
    
    public String toString() {
        return "User(" + user_id + ")";
    }
}

