package pl.documents.model;

import pl.documents.model.enums.DisabilityLevel;
import pl.documents.model.enums.Relationship;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FAMILY_MEMBERS")
public class FamilyMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Relationship relationship;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private boolean insuredAtEmployee;
    private boolean legalGuardian;
    private String province;
    private String location;
    private String postCode;
    private String postOffice;
    private String district;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private DisabilityLevel disabilityLevel;
    private boolean onExclusiveMaintenance;
    private boolean isSharedHousehold;
    private String PESEL;
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public FamilyMember()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
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

    public String getPostOffice()
    {
        return postOffice;
    }

    public void setPostOffice(String postOffice)
    {
        this.postOffice = postOffice;
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

    public Worker getWorker()
    {
        return worker;
    }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }
}
