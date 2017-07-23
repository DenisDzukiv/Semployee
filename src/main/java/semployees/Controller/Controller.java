package semployees.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import semployees.Validator.OrganizationValidator;
import semployees.Validator.SemployeeValidator;
import semployees.dao.OrganizationDao;
import semployees.dao.SemployeeDao;
import semployees.dao.TypeEmployeeDao;
import semployees.model.*;
import semployees.service.OrganizationService;
import semployees.service.SemployeeService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Денис on 14.04.2017.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    SemployeeService semployeeService;

    @Autowired
    SemployeeDao semployeeDao;

    @Autowired
    TypeEmployeeDao typeEmployeeDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    SemployeeValidator semployeeValidator;

    @Autowired
    OrganizationValidator organizationValidator;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        String display = "none";
        List<Semployee> semployeeListAll = semployeeDao.findAll();
        model.addAttribute("typeEmployeesMap", listTypeEmployee());
        model.addAttribute("semployeeListAll", semployeeListAll);
        model.addAttribute("displayNoneAscLastName", display);
        model.addAttribute("displayNoneDescLastName", display);
        model.addAttribute("displayNoneDescDate", display);
        model.addAttribute("displayNoneAscDate", display);
        return "index";
    }

    @RequestMapping(value = "/cardEmployee", method = RequestMethod.GET)
    public String cardEmployee(@RequestParam Long emplId, Model model) {
        Semployee semployee = semployeeDao.findById(emplId);
        SemployeeWrapper semployeeWrapper = new SemployeeWrapper();
        semployeeWrapper.setSemployee(semployee);
        semployeeWrapper.setTypeEmployeeId(semployee.getTypeEmployee().getId());

        model.addAttribute("doit", "Edit Employee");
        model.addAttribute("id", emplId);
        model.addAttribute("organizationMap", listOrganization());
        model.addAttribute("typeEmployeesMap", listTypeEmployee());
        model.addAttribute("semployeeWrapper", semployeeWrapper);
        return "emplForm";
    }

    @RequestMapping(value = "/cardEmployee", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("emplForm") SemployeeWrapper emplForm, BindingResult result,
                                 Model model) {
        semployeeValidator.validate(emplForm, result);
        if (result.hasErrors()) {
            model.addAttribute("doit", "Edit Employee");
            model.addAttribute("id", emplForm.getId());
            model.addAttribute("valid", "validate");
            model.addAttribute("organizationMap", listOrganization());
            model.addAttribute("typeEmployeesMap", listTypeEmployee());
            model.addAttribute("semployeeWrapper", validSemplWrapper(emplForm));
            return "redirect:/emplForm";
        } else {
            saveOrUpdateEmployee(emplForm);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String typeEmployee(@RequestParam Long stypeId, Model model) {
        TypeEmployee typeEmployee = typeEmployeeDao.findById(stypeId);
        List<Semployee> semployeeListAll = semployeeService.findBytypeEmployee(typeEmployee);
        model.addAttribute("options", stypeId);
        model.addAttribute("typeEmployeesMap", listTypeEmployee());
        model.addAttribute("semployeeListAll", semployeeListAll);
        return "index";
    }

    @RequestMapping(value = "/emplForm", method = RequestMethod.GET)
    public String diskForm(Model model) {
        model.addAttribute("doit", "Create Employee");
        model.addAttribute("organizationMap", listOrganization());
        model.addAttribute("typeEmployeesMap", listTypeEmployee());
        model.addAttribute("emplForm", new SemployeeWrapper());
        return "/emplForm";
    }

    @RequestMapping(value = "/emplForm", method = RequestMethod.POST)
    public String saveEmpl(@ModelAttribute("emplForm") SemployeeWrapper emplForm, BindingResult result,
                           Model model) {
        semployeeValidator.validate(emplForm, result);
        if (result.hasErrors()) {
            model.addAttribute("doit", "Create Employee");
            model.addAttribute("valid", "validate");
            model.addAttribute("organizationMap", listOrganization());
            model.addAttribute("typeEmployeesMap", listTypeEmployee());
            model.addAttribute("semployeeWrapper", validSemplWrapper(emplForm));
            return "/emplForm";
        } else {
            saveOrUpdateEmployee(emplForm);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/deleteSemployee", method = RequestMethod.GET)
    public String deleteEmpl(@RequestParam Long id){
        if (id != null) {
            semployeeDao.deleteSemployee(id);
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/organizationForm", method = RequestMethod.GET)
    public String organizationForm(Model model) {
        model.addAttribute("doit", "Create Organization");
        model.addAttribute("organizationForm", new OrganizationWrapper());
        return "organizationForm";
    }

    @RequestMapping(value = "/listDirector", method = RequestMethod.GET)
    public String listDirector(@RequestParam int typeEmpl, Model model) {
        TypeEmployee typeEmployee = typeEmployeeDao.findById(new Long(1)); //найти только рабочих
        List<Semployee> semployeeListWork = semployeeService.findBytypeEmployee(typeEmployee);
        model.addAttribute("semployeeListWork", semployeeListWork);
        model.addAttribute("typeEmpl", typeEmpl);
        return "listDirector";
    }

    @RequestMapping(value = "/organizationForm", method = RequestMethod.POST)
    public String saveOrganization(@ModelAttribute("organizationForm") OrganizationWrapper organizationWrapper, BindingResult result,
                                   Model model) {
        organizationValidator.validate(organizationWrapper, result);
        if (result.hasErrors()) {
            model.addAttribute("doit", "Create Organization");
            model.addAttribute("valid", "validate");
            model.addAttribute("organizationWrapper", validorgWrapper(organizationWrapper));
            return "/organizationForm";
        } else {
            saveOrUpdateOrganization(organizationWrapper);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/cardOrganization", method = RequestMethod.GET)
    public String cardOrganization(@RequestParam Long orgId, Model model) {
        Organization organization = organizationDao.findById(orgId);
        OrganizationWrapper organizationWrapper = new OrganizationWrapper();
        organizationWrapper.setOrganization(organization);
        organizationWrapper.setDirectorId(organization.getId());
        organizationWrapper.setContactId(organization.getSemployee2().getId());
        model.addAttribute("doit", "Edit Organization");
        model.addAttribute("id", orgId);
        model.addAttribute("organizationWrapper", organizationWrapper);
        return "organizationForm";
    }

    @RequestMapping(value = "/cardOrganization", method = RequestMethod.POST)
    public String updateOrganization(@ModelAttribute("organizationForm") OrganizationWrapper organizationWrapper, BindingResult result,
                                     Model model) {
        organizationValidator.validate(organizationWrapper, result);
        if (result.hasErrors()) {
            model.addAttribute("doit", "Edit Organization");
            model.addAttribute("id", organizationWrapper.getId());
            model.addAttribute("organizationWrapper", validorgWrapper(organizationWrapper));
            return "/organizationForm";
        } else {
            saveOrUpdateOrganization(organizationWrapper);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrg(@RequestParam Long id){
        if (id != null) {
            semployeeDao.updateDeleteOrganization(typeEmployeeDao.findById(new Long(2)), organizationDao.findById(id));
            organizationDao.deleteOrganization(id);
        }
        return "redirect:/index";
    }

    //Фильтры сортировки
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public String sort(@RequestParam Long stypeId, @RequestParam String valueSorted,  @RequestParam String ascDesc, Model model){
        String displayAscDate = "none";
        String displayDescDate = "none";
        String displayAscLastname = "none";
        String displayDescLastname = "none";
        TypeEmployee typeEmployee = new TypeEmployee();
        List<Semployee> semployeeListAll = new ArrayList<>();
        if (stypeId != null) {
            typeEmployee = typeEmployeeDao.findById(stypeId);
            semployeeListAll = semployeeService.findBytypeEmployee(typeEmployee);
        }
        else {
            semployeeListAll = semployeeDao.findAll();
        }
        List<Semployee> semployeeList = new ArrayList<>();
        if (valueSorted.equals("emplCreateDate")){
            if(ascDesc.equals("asc")){
                semployeeList = semployeeListAll.stream().sorted(
                        (Semployee s1, Semployee s2) -> s1.getEmplCreateDate().compareTo(s2.getEmplCreateDate())
                ).collect(Collectors.toList());

                displayAscDate = null;
            }
            else {
                semployeeList = semployeeListAll.stream().sorted(
                        (Semployee s1, Semployee s2) -> - s1.getEmplCreateDate().compareTo(s2.getEmplCreateDate())
                ).collect(Collectors.toList());
                displayDescDate = null;
            }
        }
        else if (valueSorted.equals("lastname")) {
            if(ascDesc.equals("asc")){
                semployeeList = semployeeListAll.stream().sorted(
                        (Semployee s1, Semployee s2) -> s1.getLastname().compareTo(s2.getLastname())
                ).collect(Collectors.toList());
                displayAscLastname = null;
            }
            else {
                semployeeList = semployeeListAll.stream().sorted(
                        (Semployee s1, Semployee s2) -> - s1.getLastname().compareTo(s2.getLastname())
                ).collect(Collectors.toList());
                displayDescLastname = null;
            }
        }
        model.addAttribute("displayNoneAscLastName", displayAscLastname);
        model.addAttribute("displayNoneDescLastName", displayDescLastname);
        model.addAttribute("displayNoneDescDate", displayDescDate);
        model.addAttribute("displayNoneAscDate", displayAscDate);

        model.addAttribute("options", stypeId);
        model.addAttribute("typeEmployeesMap", listTypeEmployee());
        model.addAttribute("semployeeListAll", semployeeList);
        return "index";
    }

    @ExceptionHandler(Error.class)
    public ModelAndView handler(Exception ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }


    public Map<Long, String> listTypeEmployee() {
        List<TypeEmployee> typeEmployeesAll = typeEmployeeDao.findAll();
        Map<Long, String> typeEmployeesMap = new LinkedHashMap<>();
        for (TypeEmployee typeEmployees : typeEmployeesAll) {
            typeEmployeesMap.put(typeEmployees.getId(), typeEmployees.getStype());
        }
        return typeEmployeesMap;
    }

    public Map<Long, String> listOrganization() {
        List<Organization> organizationList = organizationDao.findAll();
        Map<Long, String> organizationMap = new LinkedHashMap<>();
        for (Organization organization1 : organizationList) {
            organizationMap.put(organization1.getId(), organization1.getOrgName());
        }
        return organizationMap;
    }

    private void saveOrUpdateEmployee(SemployeeWrapper emplForm) {
        TypeEmployee typeEmployee = typeEmployeeDao.findById(emplForm.getTypeEmployeeId());
        Organization organization = new Organization();
        if (emplForm.getTypeEmployeeId() == 1) {
            organization = organizationDao.findById(emplForm.getOrgId());
        } else organization = null;
        semployeeService.saveOrUpdate(emplForm, typeEmployee, organization);
    }

    private void saveOrUpdateOrganization(OrganizationWrapper organizationWrapper) {
        Semployee semployee = semployeeDao.findById(organizationWrapper.getDirectorId());
        Semployee semployee1 = semployeeDao.findById(organizationWrapper.getContactId());
        organizationService.saveOrUpdate(organizationWrapper, semployee, semployee1);
    }

    public OrganizationWrapper validorgWrapper(OrganizationWrapper organizationWrapper) {
        Organization organization = new Organization();
        organization.setOrgName(organizationWrapper.getOrgName());
        organization.setOrgAdress(organizationWrapper.getOrgAdress());
        organization.setOrgUrlAdress(organizationWrapper.getOrgUrlAdress());
        organization.setOrgEmail(organizationWrapper.getOrgEmail());
        organization.setOrgTelephone(organizationWrapper.getOrgTelephone());
        organization.setSemployee1(semployeeDao.findById(organizationWrapper.getDirectorId()));
        organization.setSemployee2(semployeeDao.findById(organizationWrapper.getContactId()));

        OrganizationWrapper organizationWrapper1 = new OrganizationWrapper();
        organizationWrapper1.setOrganization(organization);
        return organizationWrapper1;
    }

    public SemployeeWrapper validSemplWrapper(SemployeeWrapper emplForm) {
        SemployeeWrapper semployeeWrapper = new SemployeeWrapper();
        Semployee semployee = new Semployee();
        semployee.setLastname(emplForm.getLastname());
        semployee.setFirstname(emplForm.getFirstname());
        semployee.setMiddlename(emplForm.getMiddlename());
        semployee.setEmail(emplForm.getEmail());
        semployee.setEmplTelephone(emplForm.getEmplTelephone());
        semployee.setEmplTelephoneWork(emplForm.getEmplTelephoneWork());
        semployee.setTypeEmployee(typeEmployeeDao.findById(emplForm.getTypeEmployeeId()));
        semployee.setOrganization3(organizationDao.findById(emplForm.getOrgId()));
        semployeeWrapper.setSemployee(semployee);
        return semployeeWrapper;
    }

}
