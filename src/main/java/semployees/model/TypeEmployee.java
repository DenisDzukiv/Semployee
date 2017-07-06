package semployees.model;

import javax.persistence.*;
import java.util.List;


/**
 * Created by Денис on 14.04.2017.
 */
@Entity
@Table(name = "stype_employee")
public class TypeEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "typeEmployee")
    private List<Semployee> semployeeList;

    @Column(name = "stype")
    private String stype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Semployee> getSemployeeList() {
        return semployeeList;
    }

    public void setSemployeeList(List<Semployee> semployeeList) {
        this.semployeeList = semployeeList;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    @Override
    public String toString() {
        return  stype;
    }
}
