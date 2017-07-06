package semployees.service;


import org.springframework.data.domain.Sort;
import semployees.model.Organization;
import semployees.model.Semployee;
import semployees.model.SemployeeWrapper;
import semployees.model.TypeEmployee;

import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */

public interface SemployeeService {
   List<Semployee> findBytypeEmployee(TypeEmployee typeEmployee);
   void saveOrUpdate(SemployeeWrapper semployeeWrapper, TypeEmployee typeEmployee, Organization organization);
}
