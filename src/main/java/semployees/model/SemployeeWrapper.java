package semployees.model;

/**
 * Created by Денис on 21.04.2017.
 */
public class SemployeeWrapper extends  Semployee{

    private Semployee semployee;

    private Long typeEmployeeId;

    private Long orgId;

    public SemployeeWrapper(){}

    public Semployee getSemployee() {
        return semployee;
    }

    public void setSemployee(Semployee semployee) {
        this.semployee = semployee;
    }

    public Long getTypeEmployeeId() {
        return typeEmployeeId;
    }

    public void setTypeEmployeeId(Long typeEmployeeId) {
        this.typeEmployeeId = typeEmployeeId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
