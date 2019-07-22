package si.sladic.wallet.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.sladic.wallet.db.model.Balance;
import si.sladic.wallet.db.model.Transaction;
import si.sladic.wallet.db.model.User;
import si.sladic.wallet.db.repository.BalanceRepository;
import si.sladic.wallet.db.repository.TransactionRepository;
import si.sladic.wallet.db.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    public Page<Transaction> getUserTransactions(Long id, Pageable pageable) {
        return transactionRepository.findByUserId(id, pageable);
    }

    @Transactional
    public boolean addUserTransaction(Long id, Long transactionId, BigDecimal amount) {
        final Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            // user does not exist, unable to add transaction
            return false;
        }

        final Optional<Transaction> preexistingTransaction = transactionRepository.findById(transactionId);

        if (preexistingTransaction.isPresent()) {
            // transaction with given id already exists, can't proceed
            return false;
        }

        final Balance balance = user.get().getBalance();

        if (balance.getAmount().add(amount).signum() < 0) {
            // transaction amount would make balance negative, can't proceed
            return false;
        }

        final Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(amount);
        transaction.setUser(user.get());

        transaction.setPreviousBalance(balance.getAmount());
        transactionRepository.save(transaction);

        balance.setAmount(balance.getAmount().add(amount));
        balanceRepository.save(balance);

        return true;
    }
}
