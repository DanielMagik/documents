package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.documents.model.FamilyMember;
import pl.documents.repository.FamilyMemberRepository;

import java.util.UUID;

public interface SqlFamilyMemberRepository extends FamilyMemberRepository, JpaRepository<FamilyMember, UUID>
{
}
