package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GcmDTO {


    @JsonProperty("notification")
    private GcmNotification notification;

    @JsonProperty("data")
    private GcmData gcmData;

    public GcmDTO(String notificationTitle, String notificationMessage){

        this.notification = new GcmNotification(notificationTitle, notificationMessage);

        /*This GCM data could be given any object as arguments, but I just used a string for demo purposes*/
        this.gcmData  = new GcmData(notificationMessage);
    }

    private class GcmNotification implements Serializable {

        @JsonProperty("title")
        private String title;

        @JsonProperty("message")
        private String message;

        public GcmNotification(String title, String message){
            this.title=title;
            this.message=message;
        }
    }

    private class GcmData <T> implements Serializable{

        @JsonProperty("message")
        private T messageData;

        public GcmData (T messageData){
            this.messageData=messageData;
        }
    }
}
