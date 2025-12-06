package com.crypto.tracker.controller;

import com.crypto.tracker.model.Wallet;
import com.crypto.tracker.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.saveWallet(wallet);
    }

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }
    
 // Add wallet
    @PostMapping("/add")
    public ResponseEntity<Wallet> addWallet(
            @RequestParam Long userId,
            @RequestParam String walletAddress) {

        Wallet wallet = walletService.addWallet(userId, walletAddress);
        return ResponseEntity.ok(wallet);
    }
    
 // Get all wallets of user
    @GetMapping("/list")
    public ResponseEntity<List<Wallet>> getWallets(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getUserWallets(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        return walletService.getWalletById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
    }
}
