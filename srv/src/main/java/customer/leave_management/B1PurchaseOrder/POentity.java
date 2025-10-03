// package customer.leave_management.B1PurchaseOrder;

// import java.sql.Date;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonProperty;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @Data
// @NoArgsConstructor
// @AllArgsConstructor

// public class POentity {
//    @JsonProperty("CardCode")
//     public String CardCode;
//     @JsonProperty("CardName")
//     public String CardName;
//     @JsonProperty("DocNum")
//     public String DocNum;
//     @JsonProperty("NumAtCard")
//     public String NumAtCard;
//     @JsonProperty("DocType")
//     public String DocType;
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
//     public Date DocDate;
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
//     public Date DocDueDate;
//     @JsonProperty("Address")
//     public String Address;
//     @JsonProperty("BPLID")
//     public String BPLID;
//     @JsonProperty("Department")
//     public String Department;
//     @JsonProperty("Email")
//     public String Email;
//     @JsonProperty("Branch")
//     public String Branch;
//     @JsonProperty("BPLName")
//     public String BPLName;
//     @JsonProperty("DocCurrency")
//     public String DocCurrency;
//     @JsonProperty("DocumentStatus")
//     public String DocumentStatus;
    
// }


package customer.leave_management.B1PurchaseOrder;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POentity {

    // Header fields
    @JsonProperty("CardCode")
    private String CardCode;

    @JsonProperty("CardName")
    private String CardName;

    @JsonProperty("DocNum")
    private String DocNum;

    @JsonProperty("NumAtCard")
    private String NumAtCard;

    @JsonProperty("DocType")
    private String DocType;

    @JsonProperty("DocDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date DocDate;

    @JsonProperty("DocDueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date DocDueDate;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("VATRegNum")
    private String VATRegNum;

    @JsonProperty("Department")
    private String Department;

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("Branch")
    private String Branch;

    @JsonProperty("BPL_IDAssignedToInvoice")
    private String BPL_IDAssignedToInvoice;

    @JsonProperty("DocCurrency")
    private String DocCurrency;

    @JsonProperty("DocumentStatus")
    private String DocumentStatus;

    @JsonProperty("DocTotal")
    private Double DocTotal;

    // Line-level items
    @JsonProperty("DocumentLines")
    private List<POLine> DocumentLines;

    // Serialized getters (ignored for JSON to avoid duplicate lowercase keys)
    @JsonIgnore
    public String getCardCode() { return CardCode; }
    @JsonIgnore
    public String getCardName() { return CardName; }
    @JsonIgnore
    public String getDocNum() { return DocNum; }
    @JsonIgnore
    public String getNumAtCard() { return NumAtCard; }
    @JsonIgnore
    public String getDocType() { return DocType; }
    @JsonIgnore
    public Date getDocDate() { return DocDate; }
    @JsonIgnore
    public Date getDocDueDate() { return DocDueDate; }
    @JsonIgnore
    public String getAddress() { return Address; }
    @JsonIgnore
    public String getVATRegNum() { return VATRegNum; }
    @JsonIgnore
    public String getDepartment() { return Department; }
    @JsonIgnore
    public String getEmail() { return Email; }
    @JsonIgnore
    public String getBranch() { return Branch; }
    @JsonIgnore
    public String getBPLName() { return BPL_IDAssignedToInvoice; }
    @JsonIgnore
    public String getDocCurrency() { return DocCurrency; }
    @JsonIgnore
    public String getDocumentStatus() { return DocumentStatus; }
    @JsonIgnore
    public Double getDocTotal() { return DocTotal; }
    @JsonIgnore
    public List<POLine> getDocumentLines() { return DocumentLines; }

    // Inner class for document lines
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class POLine {
        @JsonProperty("ItemCode")
        private String ItemCode;

        @JsonProperty("ItemDescription")
        private String ItemDescription;

        @JsonProperty("Quantity")
        private Double Quantity;

        @JsonProperty("VatGroup")
        private String VatGroup;

        @JsonProperty("DiscountPercent")
        private Double DiscountPercent;

        @JsonProperty("Price")
        private Double Price;

        @JsonProperty("GrossPrice")
        private Double GrossPrice;

        @JsonProperty("UoMCode")
        private String UoMCode;

        @JsonProperty("Currency")
        private String Currency;

        @JsonProperty("Rate")
        private Double Rate;

        // Ignore getters for JSON serialization to avoid duplicates
        @JsonIgnore
        public String getItemCode() { return ItemCode; }
        @JsonIgnore
        public String getItemDescription() { return ItemDescription; }
        @JsonIgnore
        public Double getQuantity() { return Quantity; }
        @JsonIgnore
        public String getVatGroup() { return VatGroup; }
        @JsonIgnore
        public Double getDiscountPercent() { return DiscountPercent; }
        @JsonIgnore
        public Double getPrice() { return Price; }
        @JsonIgnore
        public Double getGrossPrice() { return GrossPrice; }
        @JsonIgnore
        public String getUoMCode() { return UoMCode; }
        @JsonIgnore
        public String getCurrency() { return Currency; }
        @JsonIgnore
        public Double getRate() { return Rate; }
        
    }
}
