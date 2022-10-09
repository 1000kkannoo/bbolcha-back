package project.BBolCha.domain.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.BBolCha.domain.user.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}