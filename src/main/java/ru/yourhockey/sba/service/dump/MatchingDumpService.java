package ru.yourhockey.sba.service.dump;

import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.DumpConfig;

@Service
public class MatchingDumpService extends DumpService {
    public MatchingDumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    @Override
    public void runDump() {
        createMatcherDump();
    }

    public void createMatcherDump() {
        createDump(
                dumpConfig.getMatcherProcessName(),
                dumpConfig.getMatcherDb(),
                dumpConfig.getMatcherDumpFilePath()
        );
    }
}
