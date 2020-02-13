package com.sainath.cscreditcardsservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sainath.cscreditcardsservice.model.ScoredCardsProvider;
import com.sainath.cscreditcardsservice.model.ScoredCardsProviderSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoredCardsProviderServiceImpl implements ScoredCardsProviderService {
    private final Logger logger = LoggerFactory.getLogger(ScoredCardsProviderServiceImpl.class);

    @Value("${scoredcards.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate;

    @Autowired
    public ScoredCardsProviderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "findFallbackCreditCards",
                        commandProperties = {
                                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
                        })
    public List<ScoredCardsProvider> findCreditCards(ScoredCardsProviderSearch scoredCardsProviderSearch) {
        logger.info("Inside findCreditCards...");
        logger.info("scoredCardsProviderSearch: {}", scoredCardsProviderSearch.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ScoredCardsProviderSearch> httpEntity = new HttpEntity<>(scoredCardsProviderSearch, httpHeaders);
        ResponseEntity<List<ScoredCardsProvider>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity,
                                                new ParameterizedTypeReference<List<ScoredCardsProvider>>() {});
        return responseEntity.getBody();
    }

    private List<ScoredCardsProvider> findFallbackCreditCards(ScoredCardsProviderSearch scoredCardsProviderSearch) {
        logger.info("Inside findFallbackCreditCards... ScoredCards Provider Service is down...");
        return new ArrayList<>();
    }
}
