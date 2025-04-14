package tr.com.muskar.realTimeChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.muskar.realTimeChat.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
