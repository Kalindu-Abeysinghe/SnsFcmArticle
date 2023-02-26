package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CreatePlatformApplicationRequestDTO {


    private String firebaseServerKey;
    private String applicationName;

}
