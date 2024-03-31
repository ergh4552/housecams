package com.kpoy.housecams.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "video")
@Getter
@Setter
public class VideoConfigProperties {
    private String directoriespath;
    private String extension;
}
