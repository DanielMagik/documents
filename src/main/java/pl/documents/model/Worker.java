package pl.documents.model;

import pl.documents.model.enums.Pension;
import pl.documents.model.enums.SecurityClearance;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "WORKERS")
public class Worker
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fillLocation;
    private boolean isPolishCitizen;
    private String citizenship;
    private String documentNumber;
    private String documentType;
    private String taxOffice;
    private String authorizedName;
    private String authorizedSurname;
    private String authorizedContact;
    private String NIP;
    private String workplace;
    private String department;
    private Pension pension;
    private LocalDate employmentDate;
    private String bank;
    private String accountNumber;
    private SecurityClearance securityClearance;
    private String NFZ;
    private String annualEarningsZUS;
    private String annualEarningsFamily;
    private String pensionZUSNumber;
    private boolean willPit2;
    private boolean willParent;
    private boolean willReducedTask;
    private boolean willTaxReducingAmount;
    private boolean willHigherTask;
    private DayOfWeek higherTaskMonth;
    private boolean hasChildren;
    private boolean willSpecialPowersForFamily;
    private boolean willFinancingZFSS;
    private boolean willIncreasedCosts;
    private boolean willZUS;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private LocalDate disabledFrom;
    private LocalDate disabledTo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @OneToOne
    @JoinColumn(name = "ID_CANDIDATE",referencedColumnName = "id")
    private Candidate candidate;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<Address> addresses;

}
