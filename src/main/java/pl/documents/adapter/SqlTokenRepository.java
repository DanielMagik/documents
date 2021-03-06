package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Token;
import pl.documents.repository.TokenRepository;

import java.util.UUID;

@Repository
interface SqlTokenRepository extends TokenRepository, JpaRepository<Token, UUID>
{

}
