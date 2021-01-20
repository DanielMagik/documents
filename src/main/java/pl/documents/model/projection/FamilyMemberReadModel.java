package pl.documents.model.projection;

import pl.documents.model.FamilyMember;
import pl.documents.model.enums.DisabilityLevel;
import pl.documents.model.enums.Relationship;

import java.time.LocalDate;

public class FamilyMemberReadModel
{
    private Relationship relationship;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private boolean insuredAtEmployee;
    private boolean legalGuardian;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private DisabilityLevel disabilityLevel;
    private boolean onExclusiveMaintenance;
    private boolean isSharedHousehold;
    private String PESEL;
    private String location;
    private String postCode;
    private String district;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;

    public FamilyMemberReadModel(FamilyMember source)
    {
        this.relationship=source.getRelationship();
        this.name = source.getName();
        this.surname=source.getSurname();
        this.birthDate=source.getBirthDate();
        this.insuredAtEmployee=source.isInsuredAtEmployee();
        this.legalGuardian=source.isLegalGuardian();
        this.isDisabled=source.isDisabled();
        this.disabledZUSNumber=source.getDisabledZUSNumber();
        this.disabilityLevel=source.getDisabilityLevel();
        this.onExclusiveMaintenance=source.isOnExclusiveMaintenance();
        this.isSharedHousehold=source.isSharedHousehold();
        this.PESEL=source.getPESEL();
        this.location=source.getLocation();
        this.postCode=source.getPostCode();
        this.district=source.getDistrict();
        this.community=source.getCommunity();
        this.street=source.getStreet();
        this.homeNumber=source.getHomeNumber();
        this.flatNumber=source.getFlatNumber();
    }

    public Relationship getRelationship()
    {
        return relationship;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public boolean isInsuredAtEmployee()
    {
        return insuredAtEmployee;
    }

    public boolean isLegalGuardian()
    {
        return legalGuardian;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public String getDisabledZUSNumber()
    {
        return disabledZUSNumber;
    }

    public DisabilityLevel getDisabilityLevel()
    {
        return disabilityLevel;
    }

    public boolean isOnExclusiveMaintenance()
    {
        return onExclusiveMaintenance;
    }

    public boolean isSharedHousehold()
    {
        return isSharedHousehold;
    }

    public String getPESEL()
    {
        return PESEL;
    }

    public String getLocation()
    {
        return location;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getCommunity()
    {
        return community;
    }

    public String getStreet()
    {
        return street;
    }

    public String getHomeNumber()
    {
        return homeNumber;
    }

    public String getFlatNumber()
    {
        return flatNumber;
    }

    public FamilyMember toFamilyMember()
    {
        FamilyMember familyMember = new FamilyMember(this.name,this.surname,this.birthDate);
        return familyMember;
    }
}
