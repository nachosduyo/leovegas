package si.sladic.wallet.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.sladic.wallet.db.model.Balance;
import si.sladic.wallet.db.model.User;
import si.sladic.wallet.db.repository.BalanceRepository;
import si.sladic.wallet.db.repository.UserRepository;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BalanceRepository balanceRepository;

    @Transactional
    public User createUser() {
        User user = new User();

        Balance balance = new Balance();
        balance.setAmount(BigDecimal.ZERO);

        user.setBalance(balance);

        balanceRepository.save(balance);
        return userRepo.save(user);
    }

    public Balance getUserBalance(Long id) {
        return userRepo.findById(id)
                .map(User::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Unable to fetch balance for user!"));
    }

}
