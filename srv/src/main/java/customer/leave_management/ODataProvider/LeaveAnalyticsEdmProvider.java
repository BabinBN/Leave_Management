package customer.leave_management.ODataProvider;

import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.springframework.stereotype.Component;

@Component
public class LeaveAnalyticsEdmProvider extends EdmProvider {

    public static final String NAMESPACE = "leave.management";
    public static final String ENTITY_TYPE = "LeaveAnalytics";
    public static final String ENTITY_SET = "LeaveAnalyticsSet";
    public static final String CONTAINER = "LeaveManagementContainer";

    @Override
    public EntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {
        if (entityTypeName.getNamespace().equals(NAMESPACE) &&
                entityTypeName.getName().equals(ENTITY_TYPE)) {

            // Properties
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("Code").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("U_REQ").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("U_FROM").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("U_TO").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("U_REASON").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("U_DAYS").setType(EdmSimpleTypeKind.Int32));

            // Key
            List<PropertyRef> keyProps = new ArrayList<>();
            keyProps.add(new PropertyRef().setName("Code"));
            Key key = new Key().setKeys(keyProps);

            // Entity Type
            return new EntityType()
                    .setName(ENTITY_TYPE)
                    .setProperties(properties)
                    .setKey(key);
        }
        return null;
    }

    @Override
    public EntitySet getEntitySet(String entityContainerName, String entitySetName) throws ODataException {
        if (entityContainerName.equals(CONTAINER) && entitySetName.equals(ENTITY_SET)) {
            return new EntitySet()
                    .setName(ENTITY_SET)
                    .setEntityType(new FullQualifiedName(NAMESPACE, ENTITY_TYPE));
        }
        return null;
    }

    @Override
    public List<Schema> getSchemas() throws ODataException {
        List<Schema> schemas = new ArrayList<>();
        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        // Entity Types
        List<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(new FullQualifiedName(NAMESPACE, ENTITY_TYPE)));
        schema.setEntityTypes(entityTypes);

        // Container
        EntityContainer container = new EntityContainer();
        container.setName(CONTAINER);
        container.setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(CONTAINER, ENTITY_SET)); // <-- FIXED HERE
        container.setEntitySets(entitySets);

        List<EntityContainer> containers = new ArrayList<>();
        containers.add(container);
        schema.setEntityContainers(containers);

        schemas.add(schema);
        return schemas;
    }

}
