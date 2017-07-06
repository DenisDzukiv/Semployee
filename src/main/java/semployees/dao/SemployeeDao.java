package semployees.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import semployees.model.Organization;
import semployees.model.Semployee;
import semployees.model.TypeEmployee;

import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */
public interface SemployeeDao extends JpaRepository<Semployee, Long> {
    List<Semployee> findAll();
    List<Semployee> findBytypeEmployee(TypeEmployee typeEmployee);
    List<Semployee> findBytypeEmployeeOrderByLastnameAsc(TypeEmployee typeEmployee);
    Semployee findById(Long id);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Semployee e set e.lastname =:lastname, e.firstname =:firstname," +
            "e.middlename =:middlename, e.email =:email, e.emplTelephone =:emplTelephone," +
            "e.emplTelephoneWork =:emplTelephoneWork, e.organization3 =:organization3, e.typeEmployee =:typeEmployee" +
            "   where e.id =:id")
    void updateSemployee(@Param("lastname") String lastname,
                         @Param("firstname") String firstname,
                         @Param("middlename") String middlename,
                         @Param("email") String email,
                         @Param("emplTelephone") Integer emplTelephone,
                         @Param("emplTelephoneWork") Integer emplTelephoneWork,
                         @Param("organization3") Organization organization3,
                         @Param("typeEmployee") TypeEmployee typeEmployee,
                         @Param("id") Long id);
    @Modifying
    @Transactional
    @Query("delete from Semployee e where e.id =:id")
    void deleteSemployee(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("update Semployee e set e.organization3 = null, e.typeEmployee =:typeEmployee, e.emplTelephoneWork = null " +
            "where e.organization3 =:organization3")
    void updateDeleteOrganization(@Param("typeEmployee") TypeEmployee typeEmployee, @Param("organization3") Organization organization3);

}
