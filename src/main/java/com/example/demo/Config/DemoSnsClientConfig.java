package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class DemoSnsClientConfig {

    @Bean
    public static SnsClient buildSnsClient(){
        return SnsClient.builder()
                .credentialsProvider(() -> getAwsCredentials())
                .region(getAwsRegion())
                .build();
    }

    public static AwsCredentials getAwsCredentials(){
        return new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return System.getenv("awsAccessKey");
            }

            @Override
            public String secretAccessKey() {
                return System.getenv("awsSecretAccessKey");
            }
        };
    }

    public static Region getAwsRegion(){
        return Region.of(System.getenv("awsRegion"));
    }
}
