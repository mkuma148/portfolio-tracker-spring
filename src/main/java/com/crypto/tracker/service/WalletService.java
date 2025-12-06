package com.crypto.tracker.service;

import com.crypto.tracker.model.User;
import com.crypto.tracker.model.Wallet;
import com.crypto.tracker.repository.UserRepository;
import com.crypto.tracker.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    
    @Autowired
    private UserRepository userRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
    
   

    public Wallet addWallet(Long userId, String walletAddress) {

		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = new Wallet();
        wallet.setWalletAddress(walletAddress);
        wallet.setLabel(walletAddress.substring(0, 6) + "...");
        wallet.setUser(user);

        return walletRepository.save(wallet);
    }
    
    public List<Wallet> getUserWallets(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
