package ru.yourhockey.sba.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.sba.service.refresh.RefreshService;

@RequiredArgsConstructor
@RequestMapping("/refresh")
@RestController
public class RefreshController {

    private final RefreshService refreshService;

    @GetMapping("/matcherOffer")
    public void refreshMatcherOffers() {
        refreshService.refreshMatcherOffers();
    }
}
