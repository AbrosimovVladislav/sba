package ru.yourhockey.sba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class DumpService {

    private final MatcherOfferConfig matcherOfferConfig;

    public void dumpMatcherOffers() {
        log.info("Start dump of MatcherOffers");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "pg_dump",
                    "--dbname=" + matcherOfferConfig.getDb(),
                    "--schema=public",
                    "--table=public.\"matcher_offer\"",
                    "--file=" + matcherOfferConfig.getFilePath() + "matcher-offers-dump-" + Instant.now() + ".sql",
                    "--username=" + matcherOfferConfig.getUsername(),
                    "--host=localhost",
                    "--port=5432"
            );
            processBuilder.environment().put("PGPASSWORD", matcherOfferConfig.getPassword());
            Process start = processBuilder.start();
            start.waitFor();

            InputStream errorStream = start.getErrorStream();
            String error = new String(errorStream.readAllBytes());
            if (StringUtils.isEmpty(error)) {
                log.info("");
            } else {
                log.error(error);
            }
            errorStream.close();

        } catch (IOException | InterruptedException e) {
            log.error("Command  can't be processed");
            e.printStackTrace();
        }
    }

}
