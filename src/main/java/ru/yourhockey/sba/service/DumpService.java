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
        String cmd = new StringJoiner(" ")
                .add("pg_dump")
                .add("--dbname=" + matcherOfferConfig.getDb())
                .add("--schema=public")
                .add("--table=public.\\\"matcher_offer\\\"")
                .add("--file=" +
                        (System.getProperty("os.name").contains("Linux") ? matcherOfferConfig.getLinuxFilePath() : matcherOfferConfig.getWindowsFilePath())
                        + "matcher-offers-dump-" + Instant.now() + ".sql")
                .add("--username=" + matcherOfferConfig.getUsername())
                .add("--host=localhost")
                .add("--port=5432")
                .toString();
        log.info("Command for MatcherOffers dump: {}", cmd);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.environment().put("PGPASSWORD", matcherOfferConfig.getPassword());
            Process start = processBuilder.command(cmd).start();
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
            log.error("Command {} can't be processed", cmd);
            e.printStackTrace();
        }
    }

}
