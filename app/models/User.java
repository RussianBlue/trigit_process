package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.*;
import play.db.jpa.*;
import play.Logger;

/**
 * User entity managed by Ebean
 */
@Entity 
@Table(name="users")
public class User extends Model{
    @Id    
    public Long id;

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    public String user_id;
    
    @Column(name = "password")
    public String password;
    
    @Constraints.Required
    public String name;

    public String email;
    public String project_id;
    public String phone_number;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date created_at;
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date last_login_at;

    public Long sign_in_count;

    public User() { 

    } 
    
    
    public User(String user_id, String password, String name, String email, String project_id, String phone_number, Long sign_in_count){
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.project_id = project_id;
        this.phone_number = phone_number;
        this.created_at = new Date();
        this.last_login_at = new Date();
        this.sign_in_count = sign_in_count;
    }

    /**
     * Retrieve a User from id.
     */
    public static User findById(Long id) {
        return JPA.em().find(User.class, id);
    }

    public static User findByUserId(String user_id) {
        return JPA.em().find(User.class, user_id);
    }
    
    /**
     * Authenticate a User.
     */    
    public static User authenticate(String user_id, String password) {
        Logger.info("user = " + user_id + ", password = " + password);        
        return JPA.em().find(User.class, user_id);
    }
    
    public String toString() {
        return "User(" + email + ")";
    }

}

