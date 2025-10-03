// package customer.leave_management.modules;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;



// @RestController
// @RequestMapping("/modules")
// public class ModulesController {
//      @Autowired
//     ModulesService modulesService;

//     @PostMapping(value = "/user")
//     public ResponseEntity<?> getAllSystems(@RequestBody String body) {
//         try {
//             return new ResponseEntity<>(modulesService.getModulesByLogin(body), HttpStatus.OK);
//         } catch (Exception ex) {
    
//         }         return null;

//     }
// }
