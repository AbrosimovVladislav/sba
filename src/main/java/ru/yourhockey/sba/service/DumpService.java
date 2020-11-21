package ru.yourhockey.sba.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.time.Instant;

@Slf4j
@Service
public class DumpService extends CmdRunner {

    private static final String MATCHER_OFFER_DUMP = "MATCHER_OFFER_DUMP";

    public DumpService(MatcherOfferConfig matcherOfferConfig) {
        super(matcherOfferConfig);
    }

    public void dumpMatcherOffers() {
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
        runProcess(processBuilder, MATCHER_OFFER_DUMP);
    }

}
