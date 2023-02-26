package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePlatformEndpointRequestDTO {

    private String applicationArn;
    private String deviceToken;
    private String userData;
    private String userId;
    private String channelId;
}
