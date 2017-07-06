package semployees.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import semployees.dao.OrganizationDao;
import semployees.dao.SemployeeDao;
import semployees.model.Organization;
import semployees.model.OrganizationWrapper;
import semployees.service.OrganizationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Денис on 27.04.2017.
 */
@Component
public class OrganizationValidator implements Validator {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SemployeeDao semployeeDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Organization.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrganizationWrapper organizationWrapper = (OrganizationWrapper) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgName", "Required");
        if (organizationWrapper.getOrgName().length() < 3 || organizationWrapper.getOrgName().length() > 32) {
            errors.rejectValue("orgName", "Size.organization.orgName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgUrlAdress", "Required");
        if (organizationWrapper.getOrgUrlAdress().length() < 3 || organizationWrapper.getOrgUrlAdress().length() > 32) {
            errors.rejectValue("orgUrlAdress", "Size.organization.orgUrlAdress");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgAdress", "Required");
        if (organizationWrapper.getOrgAdress().length() < 3 || organizationWrapper.getOrgAdress().length() > 32) {
            errors.rejectValue("orgAdress", "Size.organization.orgAdress");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgTelephone", "Required");
        if (organizationWrapper.getOrgTelephone() != null) {
            if (organizationWrapper.getOrgTelephone().toString().length() != 5) {
                errors.rejectValue("orgTelephone", "Size.organization.orgTelephone");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgEmail", "Required");
        Pattern p = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher m = p.matcher(organizationWrapper.getOrgEmail());
        if (!m.matches() && organizationWrapper.getOrgEmail() != "") {
            errors.rejectValue("orgEmail", "Organization.orgEmail");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "directorId", "Required");

        if (organizationDao.findBySemployee1(semployeeDao.findById(organizationWrapper.getDirectorId())) != null
                && organizationWrapper.getId() == null
                ) {
            errors.rejectValue("directorId", "Exists.director");
        }

        if (organizationWrapper.getId() != null &&
        organizationDao.findById(organizationWrapper.getId()).getSemployee1().getId() != organizationWrapper.getDirectorId()){
            errors.rejectValue("directorId", "Exists.director");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactId", "Required");
    }
}
