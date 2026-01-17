package com.revy.api_server.web.api.trade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/trade")
@RequiredArgsConstructor
public class StockTradeApi {

    @PostMapping("/buy")
    public void buyStock() {
        log.debug("buyStock called");
    }


}
