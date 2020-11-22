package ru.yourhockey.sba.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.sba.service.dump.DumpService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class DumpController {

    private final List<DumpService> dumpServices;

    @GetMapping("/testDump")
    public void testDump() {
        dumpServices.forEach(DumpService::runDump);
    }

}
