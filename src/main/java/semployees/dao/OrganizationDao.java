package semployees.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import semployees.model.Organization;
import semployees.model.Semployee;

import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */
public interface OrganizationDao extends JpaRepository<Organization, Long> {
    //public interface OrganizationDao {
    List<Organization> findAll();

    Organization findById(Long id);

    Organization findBySemployee1(Semployee semployee);

    // void save(Organization organization);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Organization o set o.orgName =:orgName, o.orgUrlAdress = :orgUrlAdress, o.orgAdress = :orgAdress," +
            " o.orgTelephone =:orgTelephone, o.orgEmail =:orgEmail, " +
            "o.semployee1 =:semployee1, " +
            "o.semployee2 =:semployee2" +
            "  where o.id =:id")
    void updateOrganization(@Param("orgName") String orgName,
                            @Param("orgUrlAdress") String orgUrlAdress,
                            @Param("orgAdress") String orgAdress,
                            @Param("orgTelephone") Integer orgTelephone,
                            @Param("orgEmail") String orgEmail,
                           @Param("semployee1") Semployee semployee1,
                           @Param("semployee2") Semployee semployee2,
                            @Param("id") Long id);
    @Modifying
    @Transactional
    @Query("delete from Organization e where e.id =:id")
    void deleteOrganization(@Param("id") Long id);




}
