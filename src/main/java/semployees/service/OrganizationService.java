package semployees.service;

import semployees.model.Organization;
import semployees.model.OrganizationWrapper;
import semployees.model.Semployee;
import semployees.model.TypeEmployee;

/**
 * Created by Денис on 23.04.2017.
 */
public interface OrganizationService {
    void saveOrUpdate(OrganizationWrapper organizationWrapper, Semployee semployee, Semployee semployee1);
//    void deleteOrganization(Long id, TypeEmployee typeEmployee);
}
