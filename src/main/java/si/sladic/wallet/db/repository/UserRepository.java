package si.sladic.wallet.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.sladic.wallet.db.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
