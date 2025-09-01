package customer.leave_management.leaveRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/leaveReq")
public class leaveReqController {

    @Autowired
    private leaveReqService leaveReqService;

    @PostMapping("/login")
    public JsonNode postLogin(@RequestBody loginReq loginentity) {
        return leaveReqService.postB1Login(loginentity);
    }

     @PostMapping("/add")
    public JsonNode postLeaveRequest(@RequestBody  leaveReqentity lentity) {
        return leaveReqService.postB1UDO(lentity);
    }
    
}
