package si.sladic.wallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.sladic.wallet.db.model.Balance;
import si.sladic.wallet.db.model.Transaction;
import si.sladic.wallet.db.service.TransactionService;
import si.sladic.wallet.db.service.UserService;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class API {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/balance")
    public HttpEntity<Balance> getBalance(@PathVariable Long playerId) {
        final Balance userBalance = userService.getUserBalance(playerId);
        return new ResponseEntity<>(userBalance, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public HttpEntity<PagedResources<Transaction>> getTransactions(@PathVariable Long playerId, Pageable pageable, PagedResourcesAssembler assembler) {
        final Page<Transaction> userTransactions = transactionService.getUserTransactions(playerId, pageable);
        return new ResponseEntity<>(assembler.toResource(userTransactions), HttpStatus.OK);
    }

    @PutMapping("/transactions/{transactionId}")
    public HttpEntity<Boolean> putTransaction(@PathVariable Long playerId, @PathVariable Long transactionId, @RequestParam BigDecimal amount) {
        final boolean added = transactionService.addUserTransaction(playerId, transactionId, amount);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

}
