package customer.leave_management.ODataProvider;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaveODataServiceFactory extends ODataServiceFactory {

    @Autowired
    private LeaveAnalyticsEdmProvider edmProvider;

    @Autowired
    private LeaveAnalyticsODataProcessor processor;

    @Override
    public ODataService createService(ODataContext ctx) {
        return createODataSingleProcessorService(edmProvider, processor);
    }
}

