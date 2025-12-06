package com.crypto.tracker.cron;

import com.crypto.tracker.service.CoinMarketCapService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CryptoScheduler {

    private final CoinMarketCapService cmcService;

    public CryptoScheduler(CoinMarketCapService cmcService) {
        this.cmcService = cmcService;
    }

    // Runs every 1 hour
    @Scheduled(cron = "0 0 * * * *")
    public void runCryptoTasks() {
        System.out.println("Running scheduled crypto tasks...");

        try {
            cmcService.fetchAndSavePrice("BTC,ETH,KAS,SOL,XRP,ADA,DOT,DOGE,BNB,LTC");
            // future me yaha aur tasks add kar sakte ho
            // fetchTransactions()
            // updateHoldings()
            // calculatePortfolioValue()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
