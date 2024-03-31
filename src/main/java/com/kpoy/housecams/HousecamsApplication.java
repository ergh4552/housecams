package com.kpoy.housecams;

import com.kpoy.housecams.configuration.VideoConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VideoConfigProperties.class)
public class HousecamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HousecamsApplication.class, args);
    }

}
