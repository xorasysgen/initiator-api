package ai.wealth.boot.initiator.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.wealth.boot.initiator.configuration.security.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	public Users getByUsername(String username);
}
