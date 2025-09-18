package customer.leave_management.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginservice {
    
    @Autowired 
    public loginDAO loginDAO;

    public loginentity Addlogin(loginentity loginentity)
    {
        return loginDAO.createLogin(loginentity);
    }

    public loginentity getbyemailpass()
    {
        return null;
    }
}
