package ru.yourhockey.sba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;
import ru.yourhockey.sba.config.MatcherOfferConfig;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public abstract class CmdRunner {

    protected final MatcherOfferConfig matcherOfferConfig;

    protected void runProcess(ProcessBuilder processBuilder, String cmdName) {
        Process start = null;
        try {
            log.info("Start processing: {}", cmdName);
            start = processBuilder.start();
            start.waitFor();

            InputStream errorStream = start.getErrorStream();
            String error = new String(errorStream.readAllBytes());
            errorStream.close();
            if (!StringUtils.isEmpty(error)) {
                throw new IOException(error);
            }
            log.info("Successful executing of command: {}", cmdName);
        } catch (IOException | InterruptedException e) {
            log.error("Failing executing of command: {}", cmdName);
            log.error(e.getMessage());
        }
    }

}
