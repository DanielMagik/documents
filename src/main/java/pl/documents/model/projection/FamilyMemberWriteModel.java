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

    public FamilyMember toFamilyMember()
    {
        FamilyMember familyMember = new FamilyMember();
        familyMember.setRelationship(relationship);
        familyMember.setName(name);
        familyMember.setSurname(surname);
        familyMember.setBirthDate(birthDate);
        familyMember.setInsuredAtEmployee(insuredAtEmployee);
        familyMember.setLegalGuardian(legalGuardian);
        familyMember.setDisabled(isDisabled);
        familyMember.setDisabledZUSNumber(disabledZUSNumber);
        familyMember.setDisabilityLevel(disabilityLevel);
        familyMember.setOnExclusiveMaintenance(onExclusiveMaintenance);
        familyMember.setSharedHousehold(isSharedHousehold);
        familyMember.setPESEL(PESEL);
        familyMember.setLocation(location);
        familyMember.setPostCode(postCode);
        familyMember.setDistrict(district);
        familyMember.setCommunity(community);
        familyMember.setStreet(street);
        familyMember.setHomeNumber(homeNumber);
        familyMember.setFlatNumber(flatNumber);
        return familyMember;
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
