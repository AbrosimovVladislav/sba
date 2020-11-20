package ru.yourhockey.sba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshService {

    private final MatcherOfferConfig matcherOfferConfig;

    public void refreshMatcherOffers() {
        log.info("Start refreshMatcherOffers()");
        File directory = new File(matcherOfferConfig.getFilePath());
        log.info("Get directory: {}", directory);
        File[] files = directory.listFiles();
        log.info("Get list of files: {}", files);
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        File file = files[0];

        log.info("Start of cmd processing");
        ProcessBuilder processBuilder = new ProcessBuilder(
                "sudo",
                "-u " + matcherOfferConfig.getUsername(),
                "psql",
                "matchingservice",
                "-f " + file.getAbsolutePath()
        );
        processBuilder.environment().put("PGPASSWORD", matcherOfferConfig.getPassword());
        Process start = null;
        try {
            start = processBuilder.start();
            start.waitFor();
            log.info("Finishing of cmd processing");
        } catch (IOException | InterruptedException e) {
            log.error("Failing executing of command");
            log.error(e.getMessage());
        }
        log.info("Successful executing of command");

    }

}
