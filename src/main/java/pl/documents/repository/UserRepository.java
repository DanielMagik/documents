package pl.documents.repository;

import pl.documents.model.User;
import pl.documents.model.Worker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository
{
    List<User> findAll();
    User save(User entity);
    boolean existsById(UUID id);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
    boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    void deleteAllByCreateDateLessThan(LocalDateTime cutOffDate);

}
