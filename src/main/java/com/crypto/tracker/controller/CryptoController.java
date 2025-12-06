package com.crypto.tracker.controller;

import com.crypto.tracker.model.Price;
import com.crypto.tracker.service.CoinMarketCapService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CoinMarketCapService cmcService;

    public CryptoController(CoinMarketCapService cmcService) {
        this.cmcService = cmcService;
    }

    @GetMapping("/fetch/{symbol}")
    public List<Price> fetchPrice(@PathVariable String symbol) {
        return cmcService.fetchAndSavePrice(symbol.toUpperCase());
    }
}
