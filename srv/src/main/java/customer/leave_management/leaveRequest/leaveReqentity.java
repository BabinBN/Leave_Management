package customer.leave_management.leaveRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class leaveReqentity {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("U_REQ")
    private String uReq;

    @JsonProperty("U_FROM")
    private String uFrom;

    @JsonProperty("U_TO")
    private String uTo;

    @JsonProperty("U_REASON")
    private String uReason;

    @JsonProperty("U_DAYS")
    private String uDays;

    //Ignore Lombok getters for JSON serialization to avoid duplicate lowercase keys
    @JsonIgnore
    public String getCode() { return code; }
    @JsonIgnore
    public String getName() { return name; }
    @JsonIgnore
    public String getUReq() { return uReq; }
    @JsonIgnore
    public String getUFrom() { return uFrom; }
    @JsonIgnore
    public String getUTo() { return uTo; }
    @JsonIgnore
    public String getUReason() { return uReason; }
    @JsonIgnore
    public String getuDays() { return uDays; }

    public leaveReqentity(String code, String name, String uReq, String uFrom, String uTo, String uReason,String uDays) {
        this.code = code;
        this.name = name;
        this.uReq = uReq;
        this.uFrom = uFrom;
        this.uTo = uTo;
        this.uReason = uReason;
        this.uDays = uDays;
    }
}
