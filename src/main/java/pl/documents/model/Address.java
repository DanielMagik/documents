package pl.documents.model;

import pl.documents.model.enums.AddressType;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private AddressType addressType;
    private String province;
    private String district;
    private String location;
    private String postalCode;
    private String postOffice;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;
}
