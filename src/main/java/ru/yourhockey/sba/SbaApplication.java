package ru.yourhockey.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.yourhockey.sba.config.DumpConfig;
import ru.yourhockey.sba.config.MatcherOfferConfig;


@EnableAdminServer
@SpringBootApplication
@EnableConfigurationProperties({DumpConfig.class})
public class SbaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SbaApplication.class, args);
	}
}
