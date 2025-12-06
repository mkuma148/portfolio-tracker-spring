package com.crypto.tracker.service;

import com.crypto.tracker.model.Direction;
import com.crypto.tracker.model.Holding;
import com.crypto.tracker.model.Transaction;
import com.crypto.tracker.model.Wallet;
import com.crypto.tracker.repository.HoldingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HoldingService {

    private final HoldingRepository holdingRepository;

    public HoldingService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    public Holding saveHolding(Holding holding) {
        return holdingRepository.save(holding);
    }

    public List<Holding> getAllHoldings() {
        return holdingRepository.findAll();
    }

    public Optional<Holding> getHoldingById(Long id) {
        return holdingRepository.findById(id);
    }

    public void deleteHolding(Long id) {
        holdingRepository.deleteById(id);
    }
    
 // New method: update holding based on transaction
//    public void applyTransaction(Transaction tx) {
//        Wallet wallet = tx.getWallet();
//        String coin = tx.getDirection(); // or coin type from transaction
//        BigDecimal amount = tx.getAmount();
//
//        // Find if holding exists
//        Optional<Holding> optionalHolding = holdingRepository.findByWalletAndCoin(wallet, coin);
//        Holding holding;
//        if (optionalHolding.isPresent()) {
//            holding = optionalHolding.get();
//            if (tx.getDirection() == Direction.IN) {
//                holding.setQuantity(holding.getQuantity().add(amount));
//            } else {
//                holding.setQuantity(holding.getQuantity().subtract(amount));
//            }
//        } else {
//            holding = new Holding();
//            holding.setWallet(wallet);
//            holding.setCoin(coin);
//            holding.setQuantity(amount);
//        }
//
//        holding.setUpdatedAt(java.time.LocalDateTime.now());
//        holdingRepository.save(holding);
//    }
//    
//    public BigDecimal calculatePortfolioValue(Wallet wallet) {
//        List<Holding> holdings = holdingRepository.findByWallet(wallet);
//        BigDecimal totalValue = BigDecimal.ZERO;
//
//        for (Holding h : holdings) {
//            BigDecimal lastPrice = h.getLastPrice() != null ? h.getLastPrice() : BigDecimal.ZERO;
//            totalValue = totalValue.add(h.getQuantity().multiply(lastPrice));
//        }
//        return totalValue;
//    }
}
