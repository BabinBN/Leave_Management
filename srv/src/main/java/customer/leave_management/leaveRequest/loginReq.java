package customer.leave_management.leaveRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class loginReq {


public String CompanyDB;
public String UserName;
public String Password;


public loginReq(String companyDB, String userName, String password) {
    CompanyDB = companyDB;
    UserName = userName;
    Password = password;    
}
    
}
