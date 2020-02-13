package com.sainath.cscreditcardsservice.service;

import com.sainath.cscreditcardsservice.model.CreditCard;
import com.sainath.cscreditcardsservice.model.CreditCardSearch;

import java.util.List;

public interface CreditCardService {
    /**
     * Finds recommended credit cards from providers
     * @param creditCardSearch Search criteria
     * @return List of CreditCard
     */
    List<CreditCard> findRecommendedCreditCards(CreditCardSearch creditCardSearch);
}
