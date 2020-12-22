package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.DisabilityLevel;
import pl.documents.model.enums.Relationship;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "FAMILY_MEMBERS")
public class FamilyMember
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    /**
     * Relacja członka rodziny z pracownikiem
     */
    private Relationship relationship;
    /**
     * Imię członka rodziny
     */
    private String name;
    /**
     * Nazwisko członka rodziny
     */
    private String surname;
    /**
     * Data urodzenia członka rodziny
     */
    private LocalDate birthDate;
    /**
     * Czy członek rodziny ma być ubezpieczony u pracownika
     * TRUE-TAK, FALSE-NIE
     */
    private boolean insuredAtEmployee;
    /**
     * Czy pracownik jest opiekunem prawnym/rodzicem dziecka
     * TRUE-TAK, FALSE-NIE
     */
    private boolean legalGuardian;//ToDo czy to na pewno jest potrzebne?
    /**
     * Czy członek rodziny jest niepełnosprwny?
     */
    private boolean isDisabled;
    /**
     * Nr decyzji ZUS, jeśli członek rodziny jest niepełnosprawny
     */
    private String disabledZUSNumber;
    /**
     * Stopień niepełnosprawności członka rodziny
     */
    private DisabilityLevel disabilityLevel;
    /**
     * Czy członek rodziny pozostaje na wyłącznym utrzymaniu ubezpieczonego?
     * TRUE-TAK, FALSE-NIE
     */
    private boolean onExclusiveMaintenance;
    /**
     * Czy członek rodziny pozostaje wraz z pracownikiem w wspólnym gospodarstwie domowym?
     */
    private boolean isSharedHousehold;
    /**
     * PESEL członka rodziny
     */
    private String PESEL;
    /**
     * Miejscowość członka rodziny
     */
    private String location;
    /**
     * Kod pocztowy członka rodziny
     */
    private String postCode;
    /**
     * Powiat członka rodziny
     */
    private String district;
    /**
     * Gmina członka rodziny
     */
    private String community;
    /**
     * Ulica członka rodziny
     */
    private String street;
    /**
     * Numer domu członka rodziny
     */
    private String homeNumber;
    /**
     * Numer mieszkania
     */
    private String flatNumber;

    /**
     * Pracownik przypisany do danego członka rodziny
     */
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public FamilyMember()
    {
    }
    public void updateForm(final FamilyMember source)
    {
        this.relationship = source.relationship;
        this.name = source.name;
        this.surname = source.surname;
        this.birthDate = source.birthDate;
        this.insuredAtEmployee = source.insuredAtEmployee;
        this.legalGuardian = source.legalGuardian;
        this.isDisabled = source.isDisabled;
        this.disabledZUSNumber = source.disabledZUSNumber;
        this.disabilityLevel = source.disabilityLevel;
        this.onExclusiveMaintenance = source.onExclusiveMaintenance;
        this.isSharedHousehold = source.isSharedHousehold;
        this.PESEL = source.PESEL;
        this.location = source.location;
        this.postCode = source.postCode;
        this.district = source.district;
        this.community = source.community;
        this.street = source.street;
        this.homeNumber = source.homeNumber;
        this.flatNumber = source.flatNumber;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }

    public Relationship getRelationship()
    {
        return relationship;
    }

    public void setRelationship(Relationship relationship)
    {
        this.relationship = relationship;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public boolean isInsuredAtEmployee()
    {
        return insuredAtEmployee;
    }

    public void setInsuredAtEmployee(boolean insuredAtEmployee)
    {
        this.insuredAtEmployee = insuredAtEmployee;
    }

    public boolean isLegalGuardian()
    {
        return legalGuardian;
    }

    public void setLegalGuardian(boolean legalGuardian)
    {
        this.legalGuardian = legalGuardian;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public void setDisabled(boolean disabled)
    {
        isDisabled = disabled;
    }

    public String getDisabledZUSNumber()
    {
        return disabledZUSNumber;
    }

    public void setDisabledZUSNumber(String disabledZUSNumber)
    {
        this.disabledZUSNumber = disabledZUSNumber;
    }

    public DisabilityLevel getDisabilityLevel()
    {
        return disabilityLevel;
    }

    public void setDisabilityLevel(DisabilityLevel disabilityLevel)
    {
        this.disabilityLevel = disabilityLevel;
    }

    public boolean isOnExclusiveMaintenance()
    {
        return onExclusiveMaintenance;
    }

    public void setOnExclusiveMaintenance(boolean onExclusiveMaintenance)
    {
        this.onExclusiveMaintenance = onExclusiveMaintenance;
    }

    public boolean isSharedHousehold()
    {
        return isSharedHousehold;
    }

    public void setSharedHousehold(boolean sharedHousehold)
    {
        isSharedHousehold = sharedHousehold;
    }

    public String getPESEL()
    {
        return PESEL;
    }

    public void setPESEL(String PESEL)
    {
        this.PESEL = PESEL;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getCommunity()
    {
        return community;
    }

    public void setCommunity(String community)
    {
        this.community = community;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHomeNumber()
    {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber)
    {
        this.homeNumber = homeNumber;
    }

    public String getFlatNumber()
    {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber)
    {
        this.flatNumber = flatNumber;
    }
}
