package com.crypto.tracker.service;

import com.crypto.tracker.model.Price;
import com.crypto.tracker.repository.PriceRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoinMarketCapService {
	
	@Value("${cmc.api.key}")
    private String apiKey;

	private final PriceRepository priceRepository;

	public CoinMarketCapService(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	public List<Price> fetchAndSavePrice(String symbols) {
		List<Price> savedPrices = new ArrayList<>();

		String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + symbols;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-CMC_PRO_API_KEY", apiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		System.out.println("Hello, Mukesh! 😎 " + response.getBody());
		JSONObject json = new JSONObject(response.getBody());
		JSONObject data = json.getJSONObject("data");

		for (String symbol : symbols.split(",")) {
			JSONObject coin = data.getJSONObject(symbol);
			double price = coin.getJSONObject("quote").getJSONObject("USD").getDouble("price");

			Price p = priceRepository.findBySymbol(symbol)
	                .orElse(new Price());
			p.setSymbol(symbol);
			p.setPrice(new BigDecimal(String.valueOf(price)));
			p.setTimestamp(LocalDateTime.now());

			priceRepository.save(p);
			savedPrices.add(p);

			System.out.println(symbol + " : " + price);
		}

		System.out.println("All prices saved successfully!");

		return savedPrices;
	}
}
