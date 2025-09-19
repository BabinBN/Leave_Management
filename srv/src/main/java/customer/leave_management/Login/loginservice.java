package customer.leave_management.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer.leave_management.Utilis.JWT;

@Service
public class loginservice {
    
    @Autowired 
    public loginDAO loginDAO;
@Autowired
private JWT jwtUtil;

    public loginentity Addlogin(loginentity loginentity)
    {
        return loginDAO.createLogin(loginentity);
    }

    public loginentity getbyemailpass(String email,String password)
    {
         loginentity valid=loginDAO.getuserPWD(email,password);
         if (valid.email !=null) {
            String token= jwtUtil.generateToken(email);
            valid.token=token;
             return valid;
        }
        else 
        {
            throw new RuntimeException("Invaild Credentials");
        }
    }
}
