// package customer.leave_management.modules;

// import java.util.Collection;

// import javax.persistence.EntityManager;
// import javax.persistence.ParameterMode;
// import javax.persistence.PersistenceContext;
// import javax.persistence.StoredProcedureQuery;

// import org.springframework.stereotype.Component;

// @Component
// public class ModulesUserDAO {
//      @PersistenceContext
//     private EntityManager entityManager;

//     public Collection<ModuleUser> getModulesByLogin(String body) {
//         try {

//             StoredProcedureQuery spPayrollProcessMappingDetails = entityManager
//                     .createStoredProcedureQuery("GET_MODULES_BY_USER", "Modules_Mapping");
//             spPayrollProcessMappingDetails.registerStoredProcedureParameter("BODY", String.class,
//                     ParameterMode.IN);
//             spPayrollProcessMappingDetails.setParameter("BODY", body);
//             spPayrollProcessMappingDetails.execute();
//             @SuppressWarnings("unchecked")
//             Collection<ModuleUser> objectList = spPayrollProcessMappingDetails.getResultList();
//             return objectList;

//         } catch (Exception exception) {
//             throw exception;
//         }
//     }
// }
