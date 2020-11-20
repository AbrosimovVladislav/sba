package ru.yourhockey.sba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

@Slf4j
@Service
public class RefreshService extends CmdRunner {

    private static final String MATCHER_OFFER_REFRESH = "MATCHER_OFFER_REFRESH";

    public RefreshService(MatcherOfferConfig matcherOfferConfig) {
        super(matcherOfferConfig);
    }

    public void refreshMatcherOffers() {
        log.info("Start refreshMatcherOffers()");
        File directory = new File(matcherOfferConfig.getFilePath());
        log.info("Get directory: {}", directory);
        File[] files = directory.listFiles();
        log.info("Get list of files: {}", files);
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        File file = files[0];

        log.info("Start of cmd processing");
//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "sudo",
//                "-u " + matcherOfferConfig.getUsername(),
//                "psql",
//                "matchingservice",
//                "-f " + file.getAbsolutePath()
//        );

        ProcessBuilder processBuilder = new ProcessBuilder(
                "psql",
                "-U " + matcherOfferConfig.getUsername(),
                "matchingservice",
                "-f " + file.getAbsolutePath()
        );
        processBuilder.environment().put("PGPASSWORD", matcherOfferConfig.getPassword());
        runProcess(processBuilder, MATCHER_OFFER_REFRESH);
    }

}
