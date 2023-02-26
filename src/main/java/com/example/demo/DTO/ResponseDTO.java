package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String applicationArn;

    private Integer statusCode;

    private String statusText;

    private Boolean isSuccessful;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endpointArn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

}