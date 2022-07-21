package hrms.hrms.core.dataAccess.abstracts;

import hrms.hrms.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
