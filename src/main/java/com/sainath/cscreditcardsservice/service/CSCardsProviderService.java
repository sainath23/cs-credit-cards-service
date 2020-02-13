package com.sainath.cscreditcardsservice.service;

import com.sainath.cscreditcardsservice.model.CSCardsProvider;
import com.sainath.cscreditcardsservice.model.CSCardsProviderSearch;

import java.util.List;

public interface CSCardsProviderService {
    /**
     * Recommends credit cards based on search criteria.
     * @param csCardsProviderSearch Request body to send
     * @return List of CSCardsProvider
     */
    List<CSCardsProvider> findCreditCards(CSCardsProviderSearch csCardsProviderSearch);
}
