package ru.yourhockey.sba.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final MatcherOfferConfig matcherOfferConfig;

    public void refreshMatcherOffers() {
        File directory = new File(matcherOfferConfig.getFilePath());
        File[] files = directory.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        File file = files[0];

        ProcessBuilder processBuilder = new ProcessBuilder(
                "psql",
                "-U " + matcherOfferConfig.getUsername(),
                "matchingservice",
                "-f " + file.getAbsolutePath()
        );
        processBuilder.environment().put("PGPASSWORD", matcherOfferConfig.getPassword());
        Process start = null;
        try {
            start = processBuilder.start();
            start.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
