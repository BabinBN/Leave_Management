package customer.leave_management.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customer.leave_management.Utilis.JWT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Login")
public class logincontroller {
    @Autowired
    public JWT jwt;
    
 
    @PostMapping("path")
    public ResponseEntity<?> login(@RequestBody loginentity loginentity )
    {
              return null;
    }
    
}
