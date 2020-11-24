package ru.yourhockey.sba.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.yourhockey.sba.service.dump.DumpService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DumpScheduler {

    private final List<DumpService> dumpServices;

    @Scheduled(cron = "0 0 */12 * * *")
    public void dump() {
        dumpServices.forEach(DumpService::runDump);
    }

}
