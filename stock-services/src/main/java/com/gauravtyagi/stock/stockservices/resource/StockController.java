package com.gauravtyagi.stock.stockservices.resource;

import com.gauravtyagi.stock.stockservices.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/stock")
public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    private static Stock apply(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Stock("");
    }

    @GetMapping("/{username}")
    public List<Quote> getStocks(@PathVariable("username") final String userName)
    {
      ResponseEntity<List<String>>  stockList = restTemplate.exchange("http://DATABASE-SERVICE/rest/db/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
        });

       List<String> quotes=stockList.getBody();
     return quotes.stream()
             .map(quote -> {
                 Stock stock= apply(quote);
               return  new Quote(quote,stock.getQuote().getPrice());
             })
             .collect(Collectors.toList());
    }
}
