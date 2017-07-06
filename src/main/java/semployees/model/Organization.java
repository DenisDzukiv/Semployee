package semployees.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */
@Entity
@Table(name = "sorganization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_ur_adress")
    private String orgUrlAdress;


    @Column(name = "org_adress")
    private String orgAdress;

    @Column(name = "org_telephone")
    private Integer orgTelephone;

    @Column(name = "org_email")
    private String orgEmail;

    @OneToMany(mappedBy = "organization3")
    private List<Semployee> semployeeList;


    @OneToOne
    @JoinColumn(name = "emp_director")
    private Semployee semployee1;

    @OneToOne
    @JoinColumn(name = "empl_contact")
    private Semployee semployee2;

    @Column(name = "org_create_date")
    @Type(type="date")
    private Date orgCreateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Semployee getSemployee1() {
        return semployee1;
    }

    public void setSemployee1(Semployee semployee1) {
        this.semployee1 = semployee1;
    }

    public Semployee getSemployee2() {
        return semployee2;
    }

    public void setSemployee2(Semployee semployee2) {
        this.semployee2 = semployee2;
    }

    public String getOrgUrlAdress() {
        return orgUrlAdress;
    }

    public void setOrgUrlAdress(String orgUrlAdress) {
        this.orgUrlAdress = orgUrlAdress;
    }

    public String getOrgAdress() {
        return orgAdress;
    }

    public void setOrgAdress(String orgAdress) {
        this.orgAdress = orgAdress;
    }

    public Integer getOrgTelephone() {
        return orgTelephone;
    }

    public void setOrgTelephone(Integer orgTelephone) {
        this.orgTelephone = orgTelephone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public Date getOrgCreateDate() {
        return orgCreateDate;
    }

    public void setOrgCreateDate(Date orgCreateDate) {
        this.orgCreateDate = orgCreateDate;
    }

    @Override
    public String toString() {
        return  orgName;
    }
}
