package customer.leave_management.B1PurchaseOrder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/PO")
public class POcontroller {

    @Autowired
    public POservice pOservice;

    @PostMapping("/add")
    public JsonNode PostPO(@RequestBody POentity poentity) {
        return pOservice.AddPO(poentity);
    }

    @GetMapping("/getpo")
    public JsonNode getPO(@PathVariable String Id) {
        return null;
    }

}
