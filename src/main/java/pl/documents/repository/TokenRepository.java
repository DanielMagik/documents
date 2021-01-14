package pl.documents.repository;

import pl.documents.model.Token;
import pl.documents.model.enums.TokenType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository
{
    Token save(Token entity);
    boolean existsById(UUID id);
    Optional<Token> findById(UUID id);
    void deleteById(UUID id);
    List<Token> findAll();
    boolean existsByIdAndTokenTypeIs(UUID id, TokenType tokenType);
}
