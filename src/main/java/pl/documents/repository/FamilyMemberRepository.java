package pl.documents.repository;

import pl.documents.model.FamilyMember;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FamilyMemberRepository
{
    List<FamilyMember> findAllByWorker(Worker worker);
    FamilyMember save(FamilyMember entity);
    boolean existsById(UUID id);
    Optional<FamilyMember> findById(UUID id);
    void deleteById(UUID id);
}
