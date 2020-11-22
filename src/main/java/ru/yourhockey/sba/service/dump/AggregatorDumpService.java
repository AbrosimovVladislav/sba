package ru.yourhockey.sba.service.dump;

import org.springframework.stereotype.Service;
import ru.yourhockey.sba.config.DumpConfig;

@Service
public class AggregatorDumpService extends DumpService {
    public AggregatorDumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    @Override
    public void runDump() {
        createAggregatorDump();
    }

    public void createAggregatorDump() {
        createDump(
                dumpConfig.getAggregatorProcessName(),
                dumpConfig.getAggregatorDb(),
                dumpConfig.getAggregatorDumpFilePath()
        );
    }

}
