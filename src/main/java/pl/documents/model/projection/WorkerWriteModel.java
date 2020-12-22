package pl.documents.model.projection;

import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerWriteModel
{
    private String email;
    private String phoneNumber;
    private String fillLocation;
    private Sex sex;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDate birthDate;
    private String profession;
    private String specialty;
    private String title;
    private String qualifications;
    private String optionalData;
    private boolean isPolishCitizen;
    private String citizenship;
    private String documentNumber;
    private String documentType;//TODO Czy to nie powinien być enum?
    private String taxOffice;
    private String authorizedName;
    private String authorizedSurname;
    private String authorizedContact;
    private boolean willSpecialPowersForFamily;
    private String NIP;
    private boolean willPIT2;
    private String workplace;
    private String department;
    private Pension pension;
    private LocalDate employmentDate;
    private String bank;
    private String accountNumber;
    private SecurityClearance securityClearance;
    private String NFZ;
    private String pensionZUSNumber;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private LocalDate disabledFrom;
    private LocalDate disabledTo;
    private Medicover medicover;
    private ContractType contractType;
    private IncomePerPerson incomePerPerson;
    private boolean ZFSS1;
    private boolean ZFSS2;
    private boolean ZFSS3;
    private boolean ZFSS4;
    private boolean ZFSS5;
    private boolean ZFSS6;
    private boolean ZFSS7;
    private boolean hasChildren;
    private boolean willParent;
    private ChildUnderFourPermissions childUnderFourPermissions;
    private ChildUnderFourteenPermissions childUnderFourteenPermissions;
    private boolean willReducedTask;
    private boolean methodOfTaxation;
    private String annualEarningsFamily;
    private boolean willTaxReducingAmount;
    private boolean willHigherTask;
    private Month higherTaskMonth;
    private boolean willIncreasedCosts;
    private boolean willZUS;
    private String annualEarningsZUS;

    private Set<AddressWriteModel> addresses = null;
    private Set<EducationWriteModel> education = null;
    private Set<EmploymentWriteModel> employments = null;
    private Set<FamilyMemberWriteModel> familyMembers = null;


    public Worker toWorker()
    {
        Worker worker = new Worker(email, phoneNumber,fillLocation,sex, firstName, secondName, surname, birthDate, profession, specialty, title, qualifications,  optionalData, isPolishCitizen, citizenship, documentNumber, documentType, taxOffice, authorizedName, authorizedSurname, authorizedContact, willSpecialPowersForFamily, NIP, willPIT2, workplace, department,pension,  employmentDate,bank, accountNumber, securityClearance,  NFZ, pensionZUSNumber, isDisabled, disabledZUSNumber, disabledFrom, disabledTo, medicover, contractType, incomePerPerson, ZFSS1, ZFSS2, ZFSS3,ZFSS4,ZFSS5,ZFSS6, ZFSS7, hasChildren, willParent, childUnderFourPermissions, childUnderFourteenPermissions, willReducedTask, methodOfTaxation, annualEarningsFamily, willTaxReducingAmount, willHigherTask, higherTaskMonth,willIncreasedCosts,willZUS, annualEarningsZUS);
        if(addresses!=null)
        {
            worker.setAddresses(addresses.stream()
                    .map(AddressWriteModel::toAddress).collect(Collectors.toSet()));
        }
        if(education!=null)
        {
            worker.setEducation(education.stream()
                    .map(EducationWriteModel::toEducation).collect(Collectors.toSet()));
        }
        if(employments!=null)
        {
            worker.setEmployments(employments.stream()
                    .map(EmploymentWriteModel::toEmployment).collect(Collectors.toSet()));
        }
        if(familyMembers!=null)
        {
            worker.setFamilyMembers(familyMembers.stream()
                    .map(FamilyMemberWriteModel::toFamilyMember).collect(Collectors.toSet()));
        }
        return worker;
    }

}
