package si.sladic.wallet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import si.sladic.wallet.db.model.User;
import si.sladic.wallet.db.service.TransactionService;
import si.sladic.wallet.db.service.UserService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class PopulateDB {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        final User user = userService.createUser();

        transactionService.addUserTransaction(user.getId(), 1L, BigDecimal.valueOf(10));
        transactionService.addUserTransaction(user.getId(), 2L, BigDecimal.valueOf(-1.22));
        transactionService.addUserTransaction(user.getId(), 3L, BigDecimal.valueOf(-8.78));
    }
}
