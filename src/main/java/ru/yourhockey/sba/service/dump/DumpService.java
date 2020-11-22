package ru.yourhockey.sba.service.dump;

import lombok.extern.slf4j.Slf4j;
import ru.yourhockey.sba.config.DumpConfig;
import ru.yourhockey.sba.service.CmdRunner;

import java.time.Instant;

@Slf4j
public abstract class DumpService extends CmdRunner {

    public DumpService(DumpConfig dumpConfig) {
        super(dumpConfig);
    }

    public abstract void runDump();

    protected void createDump(String processName, String db, String filePath) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "pg_dump",
                "--dbname=" + db,
                "--schema=public",
                "--file=" + filePath + processName + Instant.now() + ".sql",
                "--username=" + dumpConfig.getDbUserName(),
                "--host=" + dumpConfig.getHost(),
                "--port=" + dumpConfig.getPort()
        );
        processBuilder.environment().put("PGPASSWORD", dumpConfig.getDbPassword());
        runProcess(processBuilder, processName);
    }

}
