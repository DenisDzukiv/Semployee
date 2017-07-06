package semployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import semployees.dao.SemployeeDao;
import semployees.model.Organization;
import semployees.model.Semployee;
import semployees.model.SemployeeWrapper;
import semployees.model.TypeEmployee;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */
@Service
public class SemployeeServiceImpl implements SemployeeService {

    @Autowired
    SemployeeDao semployeeDao;

    @Override
    public List<Semployee> findBytypeEmployee(TypeEmployee typeEmployee) {
        List<Semployee> lisTypeEmployee = semployeeDao.findBytypeEmployee(typeEmployee);
        return lisTypeEmployee;
    }

    @Override
    public void saveOrUpdate(SemployeeWrapper semployeeWrapper, TypeEmployee typeEmployee, Organization organization) {

        if (semployeeDao.findById(semployeeWrapper.getId()) == null) {
            Semployee semployee = new Semployee();
            semployee.setLastname(semployeeWrapper.getLastname());
            semployee.setFirstname(semployeeWrapper.getFirstname());
            semployee.setMiddlename(semployeeWrapper.getMiddlename());
            semployee.setEmail(semployeeWrapper.getEmail());
            semployee.setEmplTelephone(semployeeWrapper.getEmplTelephone());
            semployee.setEmplTelephoneWork(semployeeWrapper.getEmplTelephoneWork());
            semployee.setTypeEmployee(typeEmployee);
            semployee.setOrganization3(organization);
            Date dateCreate = new Date();
            semployee.setEmplCreateDate(dateCreate);
            semployeeDao.save(semployee);
        } else {
            semployeeDao.updateSemployee(semployeeWrapper.getLastname(),
                    semployeeWrapper.getFirstname(),
                    semployeeWrapper.getMiddlename(),
                    semployeeWrapper.getEmail(),
                    semployeeWrapper.getEmplTelephone(),
                    semployeeWrapper.getEmplTelephoneWork(),
                    organization,
                    typeEmployee,
                    semployeeWrapper.getId());
        }
    }


}
