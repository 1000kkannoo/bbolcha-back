package project.BBolCha.domain.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.BBolCha.domain.user.Entity.UserToken;

@Repository
public interface TokenRepository extends JpaRepository<UserToken,String> {
    boolean existsById(String token);
}
