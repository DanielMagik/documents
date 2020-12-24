package pl.documents.model.projection;

import pl.documents.model.FamilyMember;
import pl.documents.model.enums.DisabilityLevel;
import pl.documents.model.enums.Relationship;

import java.time.LocalDate;

public class FamilyMemberWriteModel
{
    private Relationship relationship;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private boolean insuredAtEmployee;
    private boolean legalGuardian;//ToDo czy to na pewno jest potrzebne?
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

    public FamilyMemberWriteModel(Relationship relationship, String name, String surname, LocalDate birthDate, boolean insuredAtEmployee, boolean legalGuardian, boolean isDisabled, String disabledZUSNumber, DisabilityLevel disabilityLevel, boolean onExclusiveMaintenance, boolean isSharedHousehold, String PESEL, String location, String postCode, String district, String community, String street, String homeNumber, String flatNumber)
    {
        this.relationship = relationship;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.insuredAtEmployee = insuredAtEmployee;
        this.legalGuardian = legalGuardian;
        this.isDisabled = isDisabled;
        this.disabledZUSNumber = disabledZUSNumber;
        this.disabilityLevel = disabilityLevel;
        this.onExclusiveMaintenance = onExclusiveMaintenance;
        this.isSharedHousehold = isSharedHousehold;
        this.PESEL = PESEL;
        this.location = location;
        this.postCode = postCode;
        this.district = district;
        this.community = community;
        this.street = street;
        this.homeNumber = homeNumber;
        this.flatNumber = flatNumber;
    }

    public FamilyMember toFamilyMember()
    {
        FamilyMember familyMember = new FamilyMember(relationship,name,surname,birthDate,insuredAtEmployee,legalGuardian,isDisabled,disabledZUSNumber,disabilityLevel,
                onExclusiveMaintenance,isSharedHousehold,PESEL,location,postCode,district,community,street,homeNumber,flatNumber);
        return familyMember;
    }

    public void setRelationship(Relationship relationship)
    {
        this.relationship = relationship;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public void setInsuredAtEmployee(boolean insuredAtEmployee)
    {
        this.insuredAtEmployee = insuredAtEmployee;
    }

    public void setLegalGuardian(boolean legalGuardian)
    {
        this.legalGuardian = legalGuardian;
    }

    public void setDisabled(boolean disabled)
    {
        isDisabled = disabled;
    }

    public void setDisabledZUSNumber(String disabledZUSNumber)
    {
        this.disabledZUSNumber = disabledZUSNumber;
    }

    public void setDisabilityLevel(DisabilityLevel disabilityLevel)
    {
        this.disabilityLevel = disabilityLevel;
    }

    public void setOnExclusiveMaintenance(boolean onExclusiveMaintenance)
    {
        this.onExclusiveMaintenance = onExclusiveMaintenance;
    }

    public void setSharedHousehold(boolean sharedHousehold)
    {
        isSharedHousehold = sharedHousehold;
    }

    public void setPESEL(String PESEL)
    {
        this.PESEL = PESEL;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public void setCommunity(String community)
    {
        this.community = community;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setHomeNumber(String homeNumber)
    {
        this.homeNumber = homeNumber;
    }

    public void setFlatNumber(String flatNumber)
    {
        this.flatNumber = flatNumber;
    }
}
