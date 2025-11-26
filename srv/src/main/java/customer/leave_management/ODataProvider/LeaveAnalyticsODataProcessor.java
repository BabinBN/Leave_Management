package customer.leave_management.ODataProvider;

import java.util.*;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import customer.leave_management.leaveRequest.leaveReqService;
import customer.leave_management.leaveRequest.leaveReqentity;

@Component
public class LeaveAnalyticsODataProcessor extends ODataSingleProcessor {

    @Autowired
    private leaveReqService leaveService;

    @Override
    public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {
        try {

            JsonNode json = leaveService.getB1UDO();

            ObjectMapper mapper = new ObjectMapper();

            List<leaveReqentity> list = mapper.convertValue(
                    json,
                    new TypeReference<List<leaveReqentity>>() {
                    });

            List<Map<String, Object>> result = new ArrayList<>();

            for (leaveReqentity item : list) {

                Map<String, Object> row = new HashMap<>();
                row.put("Code", item.getCode());
                row.put("Name", item.getName());
                row.put("U_REQ", item.getUReq());
                row.put("U_FROM", item.getUFrom());
                row.put("U_TO", item.getUTo());
                row.put("U_REASON", item.getUReason());
                row.put("U_DAYS", Integer.valueOf(item.getuDays()));

                result.add(row);
            }

            EdmEntitySet entitySet = uriInfo.getStartEntitySet();

            return EntityProvider.writeFeed(
                    contentType,
                    entitySet,
                    result,
                    EntityProviderWriteProperties
                            .serviceRoot(getContext().getPathInfo().getServiceRoot())
                            .build());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ODataException("Error while generating OData feed: " + ex.getMessage(), ex);
        }
    }
}
