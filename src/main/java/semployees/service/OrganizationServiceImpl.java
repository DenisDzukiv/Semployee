package semployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semployees.dao.OrganizationDao;
import semployees.model.Organization;
import semployees.model.OrganizationWrapper;
import semployees.model.Semployee;
import semployees.model.TypeEmployee;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Денис on 23.04.2017.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    OrganizationDao organizationDao;

    @Override
    public void saveOrUpdate(OrganizationWrapper organizationWrapper4, Semployee semployee,Semployee semployee1){
        Organization organization = new Organization();

        if (organizationDao.findById(organizationWrapper4.getId()) == null) {
            organization.setOrgName(organizationWrapper4.getOrgName());
            organization.setOrgUrlAdress(organizationWrapper4.getOrgUrlAdress());
            organization.setOrgAdress(organizationWrapper4.getOrgAdress());
            organization.setOrgTelephone(organizationWrapper4.getOrgTelephone());
            organization.setOrgEmail(organizationWrapper4.getOrgEmail());
            organization.setSemployee1(semployee);
            organization.setSemployee2(semployee1);
            Date dateCreate = new Date();
            organization.setOrgCreateDate(dateCreate);
            organizationDao.save(organization);
        } else {
            organizationDao.updateOrganization(organizationWrapper4.getOrgName(),
                    organizationWrapper4.getOrgUrlAdress(),
                    organizationWrapper4.getOrgAdress(),
                    organizationWrapper4.getOrgTelephone(),
                    organizationWrapper4.getOrgEmail(),
                    semployee,
                    semployee1,
                    organizationWrapper4.getId());
        }
    }

   /* public void deleteOrganization(Long id, TypeEmployee typeEmployee){

    }*/

}
