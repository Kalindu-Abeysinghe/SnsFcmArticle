package com.example.demo.Controllers;


import com.example.demo.Config.DemoSnsClientConfig;
import com.example.demo.DTO.CreatePlatformApplicationRequestDTO;
import com.example.demo.DTO.CreatePlatformEndpointRequestDTO;
import com.example.demo.DTO.PublishRequestDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.Services.SnsPushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sns")
public class SnsPushNotificationController{

    private SnsPushNotificationService snsPushNotificationService;

    public SnsPushNotificationController(){
        this.snsPushNotificationService=new SnsPushNotificationService(DemoSnsClientConfig.buildSnsClient());
    }

    @PostMapping(path = "/createPlatformApplication")
    public ResponseEntity createPlatformApplication(@RequestBody CreatePlatformApplicationRequestDTO requestDTO){
        try {
            ResponseDTO responseDTO = snsPushNotificationService.createPlatformApplication(requestDTO);
            return  ResponseEntity
                    .status(HttpStatus.OK.value())
                    .body(responseDTO);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body("Exception occurred");
        }
    }

    @PostMapping(path = "createPlatformEndpoint")
    public ResponseEntity createPlatformEndpoint(@RequestBody CreatePlatformEndpointRequestDTO requestDTO){
        try {
            ResponseDTO responseDTO = snsPushNotificationService.createPlatformEndpoint(requestDTO);
            return  ResponseEntity
                    .status(HttpStatus.OK.value())
                    .body(responseDTO);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body("Exception occurred");
        }
    }


    @PostMapping(path = "publishRequest")
    public ResponseEntity publishRequest(@RequestBody PublishRequestDTO requestDTO){
        try {
            ResponseDTO responseDTO = snsPushNotificationService.publishRequest(requestDTO);
            return  ResponseEntity
                    .status(HttpStatus.OK.value())
                    .body(responseDTO);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body("Exception occurred");
        }
    }
}
