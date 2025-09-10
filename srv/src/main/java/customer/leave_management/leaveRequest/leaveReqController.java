package customer.leave_management.leaveRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import io.micrometer.core.instrument.Meter.Id;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



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
    
    @GetMapping("")
    public JsonNode getLeaveRequest() {
        return leaveReqService.getB1UDO();
    }

    @GetMapping("/{id}")
    public JsonNode getLeaveRequestById(@PathVariable("id") String id) {
        return leaveReqService.getB1UDOById(id);
    }
    
    
}
