package ru.yourhockey.sba.service.dump;

import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.DumpConfig;

@Service
public class TTDumpService extends DumpService {
    public TTDumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    @Override
    public void runDump() {
        createTTDump();
    }

    public void createTTDump() {
        createDump(
                dumpConfig.getTtProcessName(),
                dumpConfig.getTtDb(),
                dumpConfig.getTtDumpFilePath()
        );
    }
}
