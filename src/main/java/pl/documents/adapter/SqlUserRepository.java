package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.User;
import pl.documents.repository.UserRepository;

import java.util.UUID;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, UUID>
{

}
