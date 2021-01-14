package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.User;
import pl.documents.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User, UUID>
{

}
