package com.sainath.cscreditcardsservice.service;

import com.sainath.cscreditcardsservice.model.ScoredCardsProvider;
import com.sainath.cscreditcardsservice.model.ScoredCardsProviderSearch;

import java.util.List;

public interface ScoredCardsProviderService {
    /**
     * Recommends Credit Cards based on search criteria
     * @param scoredCardsProviderSearch request body to send
     * @return List of ScoredCardsProvider
     */
    List<ScoredCardsProvider> findCreditCards(ScoredCardsProviderSearch scoredCardsProviderSearch);
}
