package net.nikmlk.companymanager.controller;

import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.model.CompanyProxy;
import net.nikmlk.companymanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CompanyController {
    private CompanyService companyService;

    @Autowired(required = true)
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "companies", method = RequestMethod.GET)
    public String listCompanies(){
        return "redirect:/companies/0";
    }

    @RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("company") Company company){
        if(company.getId() == 0){
            this.companyService.addCompany(company);
        }else {
            this.companyService.updateCompany(company);
        }

        return "redirect:/companies/" + company.getParrentId();
    }

    @RequestMapping(value = "/add/from/tree/", method = RequestMethod.POST)
    public String addCompanyFromTree(@ModelAttribute("proxy") CompanyProxy companyProxy){
        Company company = new Company(companyProxy);
        this.companyService.addCompany(company);

        return "redirect:/companydata/" + companyProxy.getSuperParrentId();
    }

    @RequestMapping(value = "/addcompany/{parrentid}/{superparrentid}")
    public String addChildCompany(@PathVariable("parrentid") int parrentId, @PathVariable("superparrentid") int superParrentId, Model model){
        CompanyProxy company = new CompanyProxy();
        company.setParrentId(parrentId);
        company.setSuperParrentId(superParrentId);
        model.addAttribute("proxy", company);

        return "addcompany";
    }

    @RequestMapping("/remove/{id}")
    public String removeCompany(@PathVariable("id") int id){

        int parrentID = this.companyService.getCompanyById(id).getParrentId();

        this.companyService.removeCompany(id);

        return "redirect:/companies/" + parrentID;
    }

    @RequestMapping("edit/{id}")
    public String editCompany(@PathVariable("id") int id, Model model){
        model.addAttribute("company", this.companyService.getCompanyById(id));

        return "edit";
    }

    @RequestMapping("companydata/{id}")
    public String companyData(@PathVariable("id") int id, Model model){
        Company company = this.companyService.getCompanyById(id);
        model.addAttribute("company", company);

        //Т.к. в Entity мы храним только ID родителя,
        // то для отображения полной информации нам нужно загрузить из базы имя родительской компании
        model.addAttribute("Parrent", company.getParrentId() == 0 ? "----" : this.companyService.getCompanyById(company.getParrentId()).getCompanyName());

        //Дерево компании
        model.addAttribute("tree", this.companyService.getTableOfChildCompanies(id, "", id).getValue());

        return "companydata";
    }

    @RequestMapping("companies/{id}")
    public String listCompaniesByParrentId(@PathVariable("id") int id, Model model) {
        Company company = new Company();
        company.setParrentId(id);
        model.addAttribute("company", company);

        //Название родительской компании в оглавлении списка
        model.addAttribute("parrentName", id != 0 ? this.companyService.getCompanyById(id).getCompanyName() : "Main Company");
        //Список дочерних компаний
        model.addAttribute("listCompanies", this.companyService.listCompaniesByParrentId(id));

        return "companies";
    }
}
