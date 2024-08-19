package org.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

@Data
@JsonPropertyOrder({"customerref","customername","addressline1","addressline2","town","county","country","postcode"})
public class Customer {
    @SerializedName("customerReference")
    private String customerref;

    @SerializedName("customerName")
    private String customername;

    @SerializedName("addressLine1")
    private String addressline1;

    @SerializedName("addressLine2")
    private String addressline2;

    private String town;

    private String county;

    private String country;

    private String postcode;
}
