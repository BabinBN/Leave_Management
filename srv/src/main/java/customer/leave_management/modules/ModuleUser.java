
// package customer.leave_management.modules;
// import javax.persistence.ColumnResult;
// import javax.persistence.ConstructorResult;
// import javax.persistence.Id;
// import javax.persistence.SqlResultSetMapping;
// import javax.persistence.Entity;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @JsonIgnoreProperties
// @SqlResultSetMapping(name = "Modules_Mapping", classes = @ConstructorResult(targetClass = ModuleUser.class, columns = {
//         @ColumnResult(name = "id", type = Long.class),
//         @ColumnResult(name = "name", type = String.class),
//         @ColumnResult(name = "key", type = String.class),
//         @ColumnResult(name = "description", type = String.class),
//         @ColumnResult(name = "icon", type = String.class),
//         @ColumnResult(name = "parent_id", type = String.class),
//         @ColumnResult(name = "module_page_id", type = Long.class),
//         @ColumnResult(name = "module_page_name", type = String.class),
//         @ColumnResult(name = "pattern", type = String.class),
//         @ColumnResult(name = "target", type = String.class),
//         @ColumnResult(name = "view_name", type = String.class),
//         @ColumnResult(name = "parent", type = String.class),
//         @ColumnResult(name = "control_id", type = String.class),
//         @ColumnResult(name = "control_aggregations", type = String.class),
//          @ColumnResult(name = "order", type = Integer.class),
//          @ColumnResult(name = "sub_title", type = String.class),
//          @ColumnResult(name = "footer", type = String.class),
//          @ColumnResult(name = "role", type = String.class),
// }))

// public class ModuleUser {
//     @Id
//     private Long id;
//     private String name;
//     private String key;
//     private String description;
//     private String icon;
//     private String parent_id;
//     private Long module_page_id;
//     private String module_page_name;
//     private String pattern;
//     private String target;
//     private String view_name;
//     private String parent;
//     private String control_id;
//     private String control_aggregations;
//      private Integer order;
//      private String sub_title;
//      private String footer;
//      private String role;

//     public ModuleUser(Long id, String name, String key, String description, String icon, String parent_id,
//             Long module_page_id, String module_page_name, String pattern, String target, String view_name,
//             String parent, String control_id, String control_aggregations, Integer order, String sub_title, String footer,String role) {
//         this.id = id;
//         this.name = name;
//         this.key = key;
//         this.description = description;
//         this.icon = icon;
//         this.parent_id = parent_id;
//         this.module_page_id = module_page_id;
//         this.module_page_name = module_page_name;
//         this.pattern = pattern;
//         this.target = target;
//         this.view_name = view_name;
//         this.parent = parent;
//         this.control_id = control_id;
//         this.control_aggregations = control_aggregations;
//         this.order=order;
//         this.sub_title=sub_title;
//         this.footer=footer;
//         this.role=role;
//     }

// }
