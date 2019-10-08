package ai.wealth.boot.initiator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.wealth.boot.initiator.configuration.security.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	public Optional<Users> getByUsername(String username);
}
