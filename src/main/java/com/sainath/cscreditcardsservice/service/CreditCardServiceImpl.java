package com.sainath.cscreditcardsservice.service;

import com.sainath.cscreditcardsservice.constant.CreditCardConstant;
import com.sainath.cscreditcardsservice.model.CSCardsProvider;
import com.sainath.cscreditcardsservice.model.CSCardsProviderSearch;
import com.sainath.cscreditcardsservice.model.CreditCard;
import com.sainath.cscreditcardsservice.model.CreditCardSearch;
import com.sainath.cscreditcardsservice.model.ScoredCardsProvider;
import com.sainath.cscreditcardsservice.model.ScoredCardsProviderSearch;
import com.sainath.cscreditcardsservice.util.CreditCardUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    private CSCardsProviderService csCardsProviderService;
    private ScoredCardsProviderService scoredCardsProviderService;

    @Autowired
    public CreditCardServiceImpl(CSCardsProviderService csCardsProviderService, ScoredCardsProviderService scoredCardsProviderService) {
        this.csCardsProviderService = csCardsProviderService;
        this.scoredCardsProviderService = scoredCardsProviderService;
    }

    @Override
    public List<CreditCard> findRecommendedCreditCards(CreditCardSearch creditCardSearch) {
        logger.info("Inside findRecommendedCreditCards...");
        logger.info("creditCardSearch: {}", creditCardSearch.toString());

        // List to hold credit cards from providers
        List<CreditCard> creditCards = new ArrayList<>();

        // Calling CSCards Provider
        creditCards.addAll(findCreditCardsFromCSCardsProvider(creditCardSearch));

        // Calling ScoredCards Provider
        creditCards.addAll(findCreditCardsFromScoredCardsProvider(creditCardSearch));

        // Sort according to scoreCard desc
        creditCards.sort((o1, o2) -> o2.getCardScore().compareTo(o1.getCardScore()));

        return creditCards;
    }

    // Helper method to find credit cards from CSCards. Return empty ArrayList if nothing found
    private List<CreditCard> findCreditCardsFromCSCardsProvider(CreditCardSearch creditCardSearch) {
        List<CreditCard> creditCards = new ArrayList<>();
        CSCardsProviderSearch csCardsProviderSearch = new CSCardsProviderSearch(creditCardSearch.getName(),
                creditCardSearch.getCreditScore());
        List<CSCardsProvider> csCardsResponse = csCardsProviderService.findCreditCards(csCardsProviderSearch);

        // Iterate over response, calculate score card and add to credit cards list
        if (CollectionUtils.isNotEmpty(csCardsResponse)) {
            csCardsResponse.forEach(response -> {
                BigDecimal eligibility = response.getEligibility();

                // Making eligibility in 100% scale
                if (eligibility != null) {
                    eligibility = eligibility.multiply(new BigDecimal(10));
                } else {
                    eligibility = BigDecimal.ZERO;
                }

                BigDecimal cardScore = CreditCardUtil.getCardScore(eligibility, response.getApr());
                CreditCard creditCard = new CreditCard(CreditCardConstant.PROVIDER_CSCARDS, response.getCardName(), response.getApr(), cardScore);
                creditCards.add(creditCard);
            });
        }
        return creditCards;
    }

    //Helper method to find credit cards from ScoredCards. Return empty ArrayList if nothing found
    private List<CreditCard> findCreditCardsFromScoredCardsProvider(CreditCardSearch creditCardSearch) {
        List<CreditCard> creditCards = new ArrayList<>();
        ScoredCardsProviderSearch scoredCardsProviderSearch = new ScoredCardsProviderSearch(creditCardSearch.getName(),
                creditCardSearch.getCreditScore(),
                creditCardSearch.getSalary());
        List<ScoredCardsProvider> scoredCardsResponse = scoredCardsProviderService.findCreditCards(scoredCardsProviderSearch);

        // Iterate over scoredCardResponse and add to credit cards list
        if (CollectionUtils.isNotEmpty(scoredCardsResponse)) {
            scoredCardsResponse.forEach(response -> {
                BigDecimal eligibility = response.getApprovalRating();

                // Making eligibility in 100% scale
                if (eligibility != null) {
                    eligibility = eligibility.multiply(new BigDecimal(100));
                } else {
                    eligibility = BigDecimal.ZERO;
                }

                BigDecimal cardScore = CreditCardUtil.getCardScore(eligibility, response.getApr());
                CreditCard creditCard = new CreditCard(CreditCardConstant.PROVIDER_SCOREDCARDS, response.getCard(), response.getApr(), cardScore);
                creditCards.add(creditCard);
            });
        }
        return creditCards;
    }
}
