package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Token;
import pl.documents.model.enums.TokenType;
import pl.documents.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Repository
public interface SqlTokenRepository extends TokenRepository, JpaRepository<Token, UUID>
{

}
