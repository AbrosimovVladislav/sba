package ru.yourhockey.sba.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "dump")
public class DumpConfig {

    String matcherProcessName;
    String aggregatorProcessName;
    String scrapperProcessName;
    String ttProcessName;
    String trustInfoProcessName;

    String matcherDb;
    String aggregatorDb;
    String scrapperDb;
    String ttDb;
    String trustInfoDb;

    String matcherDumpFilePath;
    String aggregatorDumpFilePath;
    String scrapperDumpFilePath;
    String ttDumpFilePath;
    String trustInfoDumpFilePath;

    String host;
    String port;
    String dbUserName;
    String dbPassword;
}
