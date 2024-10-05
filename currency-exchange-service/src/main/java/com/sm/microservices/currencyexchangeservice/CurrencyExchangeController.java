package com.sm.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	/// currency-exchange/from/USD/to/INR
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeDetails(@PathVariable String from, @PathVariable String to) {
		/*
		 * CurrencyExchange currencyExchange = new
		 * CurrencyExchange(1000l,from,to,BigDecimal.valueOf(50)); String port =
		 * environment.getProperty("local.server.port");
		 * currencyExchange.setEnvironment(port);
		 * //currencyExchange.setEnvironment("8000"); return currencyExchange;
		 */

		logger.info("retrieveExchangeDetails called with {} and to {}", from, to);
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to" + to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		// currencyExchange.setEnvironment("8000");
		return currencyExchange;
	}
}
