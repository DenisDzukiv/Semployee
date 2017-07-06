package semployees.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semployees.model.TypeEmployee;

import java.util.List;

/**
 * Created by Денис on 14.04.2017.
 */
public interface TypeEmployeeDao extends JpaRepository<TypeEmployee, Long> {
    List<TypeEmployee> findAll();
    TypeEmployee findById(Long id);
}
