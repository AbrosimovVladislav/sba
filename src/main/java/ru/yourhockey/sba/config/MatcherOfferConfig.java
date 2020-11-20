package ru.yourhockey.sba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "dump.matcher-offers")
public class MatcherOfferConfig {
    String db;
    String username;
    String password;
}
