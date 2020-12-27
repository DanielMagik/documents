package pl.documents.service;

import org.junit.jupiter.api.Test;
import pl.documents.exception.BadAddressException;
import pl.documents.exception.BadFamilyMemberException;
import pl.documents.exception.BadIdException;
import pl.documents.model.FamilyMember;
import pl.documents.model.Worker;
import pl.documents.model.enums.Relationship;
import pl.documents.repository.FamilyMemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FamilyMemberServiceTest
{
    @Test
    void updateFamilyMemberBadIdException()
    {
        var mockFamilyMemberRepo = mock(FamilyMemberRepository.class);
        when(mockFamilyMemberRepo.existsById(any(UUID.class))).thenReturn(false);
        FamilyMemberService service = new FamilyMemberService(mockFamilyMemberRepo);
        UUID id = UUID.randomUUID();
        var exception = catchThrowable(()->service.updateFamilyMember(id,null));
        assertThat(exception).isInstanceOf(BadIdException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Family member with id "+id+" doesn't exists");
    }
    @Test
    void updateFamilyMemberSuccessfulUpdate()
    {
        FamilyMemberRepository familyMemberRepository = inMemoryRepository;

        FamilyMember f1 = new FamilyMember(Relationship.CHILD, "Adam", "Nowak", LocalDate.of(2010,10,11), true, true, true, null,
        null, false, false, null, "Gliwice","31-123", "a", "b", "c", "1", "2");
        FamilyMember f2 = new FamilyMember(Relationship.SPOUSE, "Adam", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, null, "Gliwice","31-123", "a", "b", "c", "1", "2");
        UUID u1 = UUID.randomUUID();
        f1.setId(u1);
        f2.setId(new UUID(0,2));

        FamilyMember update = new FamilyMember(Relationship.CHILD, "Agata", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, null, "Gliwice","31-123", "a", "b", "c", "1", "2");
        FamilyMember update2 = new FamilyMember(Relationship.SPOUSE, "Dawid", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, null, "Gliwice","31-123", "a", "b", "c", "1", "2");
        familyMemberRepository.save(f1);
        familyMemberRepository.save(f2);
        var service = new FamilyMemberService(familyMemberRepository);
        try
        {
            service.updateFamilyMember(u1,update);
            service.updateFamilyMember(new UUID(0,2), update2);

        }
        catch (BadIdException e)
        {
            throw new RuntimeException(e);
        }
        Optional<FamilyMember> optionalFamilyMember= familyMemberRepository.findById(u1);
        FamilyMember familyMember = optionalFamilyMember.orElse(null);
        Optional<FamilyMember> optionalFamilyMember1= familyMemberRepository.findById(new UUID(0,2));
        FamilyMember familyMember1 = optionalFamilyMember1.orElse(null);

        assertEquals(familyMember.getWorker(), update.getWorker());
        assertEquals(familyMember1.getWorker(), update2.getWorker());

        assertEquals(familyMember.getName(), update.getName());
        assertEquals(familyMember1.getName(), update2.getName());

        assertEquals(familyMember.getBirthDate(), update.getBirthDate());
        assertEquals(familyMember1.getBirthDate(), update2.getBirthDate());

        assertEquals(familyMember.getCommunity(), update.getCommunity());
        assertEquals(familyMember1.getCommunity() , update2.getCommunity());

        assertEquals(familyMember.getDisabledZUSNumber(), update.getDisabledZUSNumber());
        assertEquals(familyMember1.getDisabledZUSNumber(), update2.getDisabledZUSNumber());

        assertEquals(familyMember.getDistrict(), update.getDistrict());
        assertEquals(familyMember1.getDistrict(), update2.getDistrict());

        assertEquals(familyMember.getDisabilityLevel(), update.getDisabilityLevel());
        assertEquals(familyMember1.getDisabilityLevel(), update2.getDisabilityLevel());

        assertEquals(familyMember.getFlatNumber(), update.getFlatNumber());
        assertEquals(familyMember1.getFlatNumber(), update2.getFlatNumber());

        assertEquals(familyMember.getHomeNumber(), update.getHomeNumber());
        assertEquals(familyMember1.getHomeNumber(), update2.getHomeNumber());

        assertEquals(familyMember.getLocation(), update.getLocation());
        assertEquals(familyMember1.getLocation(), update2.getLocation());

        assertEquals(familyMember.getPESEL(), update.getPESEL());
        assertEquals(familyMember1.getPESEL(), update2.getPESEL());

        assertEquals(familyMember.getPostCode(), update.getPostCode());
        assertEquals(familyMember1.getPostCode(), update2.getPostCode());

        assertEquals(familyMember.getRelationship(), update.getRelationship());
        assertEquals(familyMember1.getRelationship(), update2.getRelationship());

        assertEquals(familyMember.getStreet(), update.getStreet());
        assertEquals(familyMember1.getStreet(), update2.getStreet());

        assertEquals(familyMember.getSurname(), update.getSurname());
        assertEquals(familyMember1.getSurname(), update2.getSurname());


    }

    @Test
    void checkDataBadPESEL()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "Agata", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
            null, false, false, "98043025565", "Gliwice","31-123", "a", "b", "c", "1", "2");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "Agata", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836669", "Gliwice","31-123", "a", "b", "c", "1", "2");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad PESEL of family member!");
        assertThat(exception1).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad PESEL of family member!");
    }
    @Test
    void checkDataBadName()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-123", "a", "b", "c", "1", "2");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "      ", "Nowak", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31-123", "a", "b", "c", "1", "2");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad name of family member!");
        assertThat(exception1).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad name of family member!");
    }

    @Test
    void checkDataBadSurname()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "A", " ", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-123", "a", "b", "c", "1", "2");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "B", "", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31-123", "a", "b", "c", "1", "2");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad surname of family member!");
        assertThat(exception1).isInstanceOf(BadFamilyMemberException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad surname of family member!");
    }

    @Test
    void checkDataBadAddressPostalCode()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "A", "C", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-asd", "a", "b", "c", "1", "2");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "B", "V", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31345", "a", "b", "c", "1", "2");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct postal code!");
        assertThat(exception1).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct postal code!");
    }
    @Test
    void checkDataBadAddressHomeNumber()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "A", "C", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-123", "a", "b", "c", "1aa", "2");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "B", "V", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31-345", "a", "b", "c", "a10", "2");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct home number!");
        assertThat(exception1).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct home number!");
    }
    @Test
    void checkDataBadAddressFlatNumber()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "A", "C", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-123", "a", "b", "c", "1", "aaa");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "B", "V", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31-345", "a", "b", "c", "1", "2aa");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct flat number!");
        assertThat(exception1).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter a correct flat number!");
    }
    @Test
    void checkDataNoException()
    {
        FamilyMemberService service = new FamilyMemberService(null);

        FamilyMember familyMember = new FamilyMember(Relationship.CHILD, "A", "C", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "98043025566", "Gliwice","31-123", "a", "b", "c", "1", "a");
        FamilyMember familyMember1 = new FamilyMember(Relationship.CHILD, "B", "V", LocalDate.of(2010,10,13), true, true, true, null,
                null, false, false, "70091836668", "Gliwice","31-345", "a", "b", "c", "1", "2a");
        var exception = catchThrowable(()->service.checkData(familyMember));
        var exception1 = catchThrowable(()->service.checkData(familyMember1));
        assertThat(exception).doesNotThrowAnyException();
        assertThat(exception1).doesNotThrowAnyException();
    }

    private FamilyMemberRepository inMemoryRepository = new FamilyMemberRepository()
    {
        int counter = 0;
        UUID index = new UUID(0,counter);
        Map<UUID, FamilyMember> map  = new HashMap<>();
        @Override
        public List<FamilyMember> findAllByWorker(Worker worker)
        {
            return map.values().stream().filter(e->e.getWorker().getId().equals(worker.getId()))
                    .collect(Collectors.toList());
        }

        @Override
        public FamilyMember save(FamilyMember entity)
        {
            if(entity.getId().equals(new UUID(0,0)))
            {
                counter++;
                index = new UUID(0,counter);
                entity.setId(index);
            }
            map.put(entity.getId(),entity);
            return entity;
        }

        @Override
        public boolean existsById(UUID id)
        {
            return map.values().stream().anyMatch(e->e.getId().equals(id));
        }

        @Override
        public Optional<FamilyMember> findById(UUID id)
        {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public void deleteById(UUID id)
        {
            map.remove(id);
        }
    };
}