package semployees.model;

/**
 * Created by Денис on 23.04.2017.
 */
public class OrganizationWrapper extends Organization {

   private Organization organization;

   private Long directorId;

   private Long contactId;

   public OrganizationWrapper(){}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
