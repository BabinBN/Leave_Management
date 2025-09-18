package customer.leave_management.Login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Data;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
@Data
@Entity
 @NoArgsConstructor
// @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

@SqlResultSetMapping(name = "login", classes = @ConstructorResult(targetClass = loginentity.class, columns = {
        @ColumnResult(name = "userid", type = Long.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "email", type = String.class),
        @ColumnResult(name = "password", type = String.class),
        @ColumnResult(name = "created_at", type = String.class),
        @ColumnResult(name = "updated_at", type = String.class)

}

))
public class loginentity {
@Id
    public Long userid;
    public String username;
    public String email;
    public String password;
    public String created_at;
    public String updated_at;

    public loginentity(Long userid, String UserName, String email, String password, String Created_at,
            String Updated_at) {
                this.userid=userid;
        this.username = UserName;
        this.password = password;
        this.email = email;
        this.created_at = Created_at;
        this.updated_at = Updated_at;
    }

     public Long getId() {
        return userid;
    }

    public void setId(Long userId) {
        this.userid = userId;
    }

    public String getuser() {
        return username;
    }

    public void setuser(String userName) {
        this.username = userName;
    }

    public String getpass() {
        return password;
    }

    public void setpass(String Password) {
        this.password = Password;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
