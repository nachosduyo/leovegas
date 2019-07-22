package si.sladic.wallet.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import si.sladic.wallet.db.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
