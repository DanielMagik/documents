package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.FamilyMember;
import pl.documents.repository.FamilyMemberRepository;

import java.util.UUID;

@Repository
interface SqlFamilyMemberRepository extends FamilyMemberRepository, JpaRepository<FamilyMember, UUID>
{

}
