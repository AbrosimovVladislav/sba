package ru.yourhockey.sba.service.dump;

import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.DumpConfig;

@Service
public class TrustInfoDumpService extends DumpService {
    public TrustInfoDumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    @Override
    public void runDump() {
        createTrustInfoDump();
    }

    public void createTrustInfoDump() {
        createDump(
                dumpConfig.getTrustInfoProcessName(),
                dumpConfig.getTrustInfoDb(),
                dumpConfig.getTrustInfoDumpFilePath()
        );
    }

}
