package customer.leave_management.Login;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Transactional
@Repository
public class loginDAO {
    @PersistenceContext
    public EntityManager entityManager;

    public loginentity createLogin(loginentity loginentity) {
        try {
          //  String reqpayload = new Gson().toJson(loginentity);
             String reqPayload = new Gson().toJson(new loginentity[] { loginentity });
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ADD_EDIT_USER");
            //  StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
            //         "\"BEE572FBAFE34972B0047FFB3C5AB915\".\"Add_Edit_Login\"",
            //         loginentity.class // or the result mapping name
            // );
            query.registerStoredProcedureParameter("IN_PARAM", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EX_MESSAGE", String.class, ParameterMode.OUT);
            query.setParameter("IN_PARAM", reqPayload);
            query.execute();

            loginentity resultlist = (loginentity) query.getSingleResult();
            return resultlist;
        } catch (Exception ex) {
            return null;
        }
    }

}
