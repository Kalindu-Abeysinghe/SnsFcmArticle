package com.example.demo.Services;

import com.example.demo.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.util.HashMap;
import java.util.Map;


@Service
public class SnsPushNotificationService {

    private SnsClient snsClient;

    public SnsPushNotificationService(SnsClient snsClient){
        this.snsClient=snsClient;
    }

    public ResponseDTO createPlatformApplication(CreatePlatformApplicationRequestDTO requestDTO){

        Map<String , String> platformAttributes=new HashMap<>();
        platformAttributes.put("PlatformCredential", requestDTO.getFirebaseServerKey());

        CreatePlatformApplicationRequest request = CreatePlatformApplicationRequest.builder()
                .name(requestDTO.getApplicationName())
                .platform("GCM")
                .attributes(platformAttributes)
                .build();

        CreatePlatformApplicationResponse response = snsClient.createPlatformApplication(request);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setApplicationArn(response.platformApplicationArn());
        responseDTO.setStatusText(response.sdkHttpResponse().statusText().orElse("Error"));
        responseDTO.setStatusCode(response.sdkHttpResponse().statusCode());
        responseDTO.setIsSuccessful(response.sdkHttpResponse().isSuccessful());

        return responseDTO;

    }

    public ResponseDTO createPlatformEndpoint(CreatePlatformEndpointRequestDTO requestDTO){

        Map<String,String> endpointAttributes=new HashMap<>();
        endpointAttributes.put("UserId", requestDTO.getUserId());
        endpointAttributes.put("ChannelId", requestDTO.getChannelId());

        CreatePlatformEndpointRequest request = CreatePlatformEndpointRequest.builder()
                .platformApplicationArn(requestDTO.getApplicationArn())
                .token(requestDTO.getDeviceToken())
                .customUserData(requestDTO.getUserData())
                .attributes(endpointAttributes)
                .build();

        CreatePlatformEndpointResponse response = snsClient.createPlatformEndpoint(request);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setEndpointArn(response.endpointArn());
        responseDTO.setStatusText(response.sdkHttpResponse().statusText().orElse("Error"));
        responseDTO.setStatusCode(response.sdkHttpResponse().statusCode());
        responseDTO.setIsSuccessful(response.sdkHttpResponse().isSuccessful());

        return responseDTO;
    }

    public ResponseDTO publishRequest(PublishRequestDTO publishRequestDTO) throws Exception{

        GcmDTO gcmDTO = new GcmDTO(publishRequestDTO.getNotificationTitle(), publishRequestDTO.getMessage());
        String gcmDTOasString = getGcmDTOasString(gcmDTO);

        PublishRequest publishRequest = PublishRequest.builder()
                .targetArn(publishRequestDTO.getTargetArn())
                .message(gcmDTOasString)
                .messageStructure("json")
                .build();

        PublishResponse response = snsClient.publish(publishRequest);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessageId(response.messageId());
        responseDTO.setStatusText(response.sdkHttpResponse().statusText().orElse("Error"));
        responseDTO.setStatusCode(response.sdkHttpResponse().statusCode());
        responseDTO.setIsSuccessful(response.sdkHttpResponse().isSuccessful());

        return responseDTO;    }

    private String getGcmDTOasString(GcmDTO gcmDTO) throws Exception{

           ObjectMapper objectMapper = new ObjectMapper();

           Map<String, String> data = new HashMap<>();
           data.put("default","This is default value");
           data.put("GCM",objectMapper.writeValueAsString(gcmDTO));

           return objectMapper.writeValueAsString(data);

    }
}
