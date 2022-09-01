package com.geekbrains.cartservice.proretries;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.auth-service")
@Data
@Component
public class UserServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
