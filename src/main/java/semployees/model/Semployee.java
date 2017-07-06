package semployees.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Денис on 13.04.2017.
 */
@Entity
@Table(name = "semployee")
public class Semployee {


   /* @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
    @SequenceGenerator(
            name="course_seq",
            sequenceName="course_sequence",
            allocationSize=20
    )*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "empl_lastname")
    private String lastname;

    @Column(name = "EMPL_FIRSTNAME")
    private String firstname;

    @Column(name = "EMPL_MIDDLENAME")
    private String middlename;

    @Column(name = "empl_email")
    private String email;

    @Column(name = "empl_telephone", nullable = true)
    private Integer emplTelephone;

    @Column(name = "empl_work_telephone", nullable = true)
    private Integer emplTelephoneWork;

    @Column(name = "empl_create_date")
    @Type(type="date")
    private Date emplCreateDate;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization3;

    @ManyToOne
    @JoinColumn(name = "empl_type")
    private TypeEmployee typeEmployee;

    @OneToOne(mappedBy = "semployee1")
    private Organization organization;

    @OneToOne(mappedBy = "semployee2")
    private Organization organization2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmplTelephone() {
        return emplTelephone;
    }

    public void setEmplTelephone(Integer emplTelephone) {
        this.emplTelephone = emplTelephone;
    }

    public Integer getEmplTelephoneWork() {
        return emplTelephoneWork;
    }

    public void setEmplTelephoneWork(Integer emplTelephoneWork) {
        this.emplTelephoneWork = emplTelephoneWork;
    }

    public Date getEmplCreateDate() {
        return emplCreateDate;
    }

    public void setEmplCreateDate(Date emplCreateDate) {
        this.emplCreateDate = emplCreateDate;
    }

    public TypeEmployee getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(TypeEmployee typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization2() {
        return organization2;
    }

    public void setOrganization2(Organization organization2) {
        this.organization2 = organization2;
    }

    public Organization getOrganization3() {
        return organization3;
    }

    public void setOrganization3(Organization organization3) {
        this.organization3 = organization3;
    }
}


