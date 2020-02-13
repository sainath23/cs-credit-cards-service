package com.sainath.cscreditcardsservice.api;

import com.sainath.cscreditcardsservice.model.CreditCard;
import com.sainath.cscreditcardsservice.model.CreditCardSearch;
import com.sainath.cscreditcardsservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreditCardRestController {
    private CreditCardService creditCardService;

    @Autowired
    public CreditCardRestController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/creditcards")
    public ResponseEntity<List<CreditCard>> getRecommendedCreditCards(@Valid @RequestBody CreditCardSearch creditCardSearch) {
        return new ResponseEntity<>(creditCardService.findRecommendedCreditCards(creditCardSearch), HttpStatus.OK);
    }
}
