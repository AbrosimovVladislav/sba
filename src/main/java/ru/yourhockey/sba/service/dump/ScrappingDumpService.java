package ru.yourhockey.sba.service.dump;

import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.DumpConfig;

@Service
public class ScrappingDumpService extends DumpService {
    public ScrappingDumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    @Override
    public void runDump() {
        createScrapperDump();
    }

    public void createScrapperDump() {
        createDump(
                dumpConfig.getScrapperProcessName(),
                dumpConfig.getScrapperDb(),
                dumpConfig.getScrapperDumpFilePath()
        );
    }
}
