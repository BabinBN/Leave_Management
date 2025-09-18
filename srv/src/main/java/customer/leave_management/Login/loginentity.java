package customer.leave_management.Login;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor

public class loginentity {

    public String username;
    public String password;

    public loginentity(String UserName, String Password) {
        this.username = UserName;
        this.password = Password;
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
}
