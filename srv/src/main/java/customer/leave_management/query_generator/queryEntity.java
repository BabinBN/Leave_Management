package customer.leave_management.query_generator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class queryEntity {

    @JsonProperty("SqlCode")
    private String sqlCode;

    @JsonProperty("SqlName")
    private String sqlName;

    @JsonProperty("SqlText")
    private String sqlText;
    
}
