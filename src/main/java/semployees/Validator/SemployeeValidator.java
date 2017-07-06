package semployees.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import semployees.model.Semployee;
import semployees.model.SemployeeWrapper;
import semployees.service.SemployeeService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Денис on 26.04.2017.
 */
@Component
public class SemployeeValidator implements Validator {

    @Autowired
    SemployeeService semployeeService;


    @Override
    public boolean supports(Class<?> aClass) {
        return Semployee.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SemployeeWrapper semployeeWrapper = (SemployeeWrapper) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "Required");
        if (semployeeWrapper.getLastname().length() < 5 || semployeeWrapper.getLastname().length() > 32) {
            errors.rejectValue("lastname", "Size.employee.lastname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "Required");
        if (semployeeWrapper.getFirstname().length() < 5 || semployeeWrapper.getFirstname().length() > 32) {
            errors.rejectValue("firstname", "Size.employee.firstname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middlename", "Required");
        if (semployeeWrapper.getMiddlename().length() < 5 || semployeeWrapper.getMiddlename().length() > 32) {
            errors.rejectValue("middlename", "Size.employee.middlename");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        Pattern p = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher m = p.matcher(semployeeWrapper.getEmail());
        if (!m.matches()) {
            errors.rejectValue("email", "Employee.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emplTelephone", "Required");
        if (semployeeWrapper.getEmplTelephone() != null) {
            if (semployeeWrapper.getEmplTelephone().toString().length() != 5) {
                errors.rejectValue("emplTelephone", "Size.employee.emplTelephone");
            }

        }

        if (semployeeWrapper.getTypeEmployeeId() != 1 && semployeeWrapper.getEmplTelephoneWork() == null) {

        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emplTelephoneWork", "Required");
            if (semployeeWrapper.getTypeEmployeeId() == 1 && semployeeWrapper.getEmplTelephoneWork() == null) {
                errors.rejectValue("emplTelephoneWork", "EmplTelephoneWork.Type1");
            }
            if (semployeeWrapper.getTypeEmployeeId() != 1 && semployeeWrapper.getEmplTelephoneWork() != null) {
                errors.rejectValue("emplTelephoneWork", "EmplTelephoneWork.Type2");
            }
            if (semployeeWrapper.getEmplTelephoneWork() != null) {
                if (semployeeWrapper.getEmplTelephoneWork().toString().length() != 5 && semployeeWrapper.getTypeEmployeeId() == 1) {
                    errors.rejectValue("emplTelephoneWork", "Size.employee.emplTelephoneWork");
                }
            }
        }
        if (semployeeWrapper.getTypeEmployeeId() != 1 && semployeeWrapper.getEmplTelephoneWork() == null) {

        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgId", "Required");
            if (semployeeWrapper.getTypeEmployeeId() == 1 && semployeeWrapper.getOrgId() == null) {
                errors.rejectValue("orgId", "Organization.Type1");
            }

            if (semployeeWrapper.getTypeEmployeeId() != 1 && semployeeWrapper.getOrgId() != null) {
                errors.rejectValue("orgId", "Organization.Type2");
            }
        }
    }
}
