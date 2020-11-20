package ru.yourhockey.sba.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.sba.service.DumpService;

@RequiredArgsConstructor
@RequestMapping("/dump")
@RestController
public class DumpController {

    private final DumpService dumpService;

    @GetMapping("/matcherOffer")
    public void dumpMatcherOffers(){
        dumpService.dumpMatcherOffers();
    }

}
