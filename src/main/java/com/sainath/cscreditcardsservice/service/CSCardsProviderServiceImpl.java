package com.sainath.cscreditcardsservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sainath.cscreditcardsservice.model.CSCardsProvider;
import com.sainath.cscreditcardsservice.model.CSCardsProviderSearch;
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
public class CSCardsProviderServiceImpl implements CSCardsProviderService {

    private final Logger logger = LoggerFactory.getLogger(CSCardsProviderServiceImpl.class);

    @Value("${cscards.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate;

    @Autowired
    public CSCardsProviderServiceImpl(RestTemplate restTemplate) {
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
    public List<CSCardsProvider> findCreditCards(CSCardsProviderSearch csCardsProviderSearch) {
        logger.info("Inside findCreditCards...");
        logger.info("csCardsProviderSearch: {}", csCardsProviderSearch.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CSCardsProviderSearch> httpEntity = new HttpEntity<>(csCardsProviderSearch, httpHeaders);
        ResponseEntity<List<CSCardsProvider>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<List<CSCardsProvider>>() {});
        return responseEntity.getBody();
    }

    private List<CSCardsProvider> findFallbackCreditCards(CSCardsProviderSearch csCardsProviderSearch) {
        logger.info("Inside findFallbackCreditCards... CSCards Provider Service is down...");
        return new ArrayList<>();
    }
}
