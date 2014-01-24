package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import play.db.jpa.*;

/**
 * User entity managed by Ebean
 */
@Entity 
@Table(name="users")
public class User extends Model {

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public String user_id;
    
    @Constraints.Required
    public String password;
    
    @Constraints.Required
    public String name;

    public String email;
    public String project_id;
    public String phone_number;
    public Number sign_in_count;
    
    public User(){

    }

    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return JPA.em().find(User.Class, email);
    }
    
    /**
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
    // --
    
    public String toString() {
        return "User(" + email + ")";
    }

}

