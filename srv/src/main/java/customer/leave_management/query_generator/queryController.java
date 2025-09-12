package customer.leave_management.query_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/query")
public class queryController {
    @Autowired
    public queryService queryservice;

    @PostMapping("/post")
    public JsonNode PostQuery(@RequestBody queryEntity queryPost) {
        return queryservice.PostQueryService(queryPost);
    }

    // @GetMapping("/test")
    // public ResponseEntity<String> testQuery() {
    // return ResponseEntity.ok("Query API working!");
    // }
    @PostMapping("/Postquery/{id}")
    public JsonNode postSqlQuery(@PathVariable("id") String id) {

        return queryservice.getqueryService(id);
    }

}
