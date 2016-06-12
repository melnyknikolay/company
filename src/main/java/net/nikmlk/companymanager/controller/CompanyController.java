package net.nikmlk.companymanager.controller;

import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.model.CompanyProxy;
import net.nikmlk.companymanager.service.CompanyService;
import net.nikmlk.companymanager.util.CompanyDataStructure;
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

    @RequestMapping("companies/{id}")
    public String listCompaniesByParrentId(@PathVariable("id") int id, Model model) {
        Company company = new Company();
        company.setParrentId(id);
        model.addAttribute("company", company);

        //ID родительской компании по отношению к текущей компании (для поднятия на уровень выше во вложениях списков компаний)
        model.addAttribute("ID", id != 0 ? this.companyService.getCompanyById(id).getParrentId() : 0);
        //Название компании в оглавлении списка
        model.addAttribute("parrentName", id != 0 ? this.companyService.getCompanyById(id).getCompanyName() : "Main Company");
        //Список дочерних компаний
        model.addAttribute("listCompanies", this.companyService.listCompaniesByParrentId(id));

        return "companies";
    }




    @RequestMapping(value = "/add/fromlist", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("company") Company company){
        if(company.getId() == 0){
            this.companyService.addCompany(company);
        }else {
            this.companyService.updateCompany(company);
        }

        return "redirect:/companies/" + company.getParrentId();
    }

    @RequestMapping(value = "/add/fromtree", method = RequestMethod.POST)
    public String addCompanyFromTree(@ModelAttribute("proxy") CompanyProxy companyProxy){
        Company company = new Company(companyProxy);
        if(company.getId() == 0){
            this.companyService.addCompany(company);
        }else {
            this.companyService.updateCompany(company);
        }

        return "redirect:/companydata/" + companyProxy.getSuperParrentId();
    }

    @RequestMapping(value = "/addcompany/{superParrent}/{parrent}")
    public String addChildCompany(@PathVariable("superParrent") int superParrent, @PathVariable("parrent") int parrent, Model model){
        CompanyProxy companyProxy = new CompanyProxy();
        companyProxy.setParrentId(parrent);
        companyProxy.setSuperParrentId(superParrent);
        model.addAttribute("proxy", companyProxy);

        return "addcompany";
    }






    @RequestMapping("/remove/{id}")
    public String removeCompany(@PathVariable("id") int id){

        int parrentID = this.companyService.getCompanyById(id).getParrentId();

        this.companyService.removeCompany(id);

        return "redirect:/companies/" + parrentID;
    }

    @RequestMapping("/removefromtree/{superParrent}/{id}")
    public String removeCompany(@PathVariable("superParrent") int superParrent, @PathVariable("id") int id){

        this.companyService.removeCompany(id);

        return "redirect:/companydata/" + superParrent;
    }




    @RequestMapping("edit/{id}")
    public String editCompany(@PathVariable("id") int id, Model model){
        model.addAttribute("company", this.companyService.getCompanyById(id));

        return "edit";
    }

    @RequestMapping("editfromtree/{superParrent}/{id}")
    public String editCompanyFromTree(@PathVariable("superParrent") int superParrent, @PathVariable("id") int id, Model model){
        model.addAttribute("proxy", new CompanyProxy(this.companyService.getCompanyById(id), superParrent));

        return "editfromtree";
    }





    @RequestMapping("companydata/{id}")
    public String companyData(@PathVariable("id") int id, Model model){
        if (id == 0){
            return "redirect:/companies/";
        }
        Company company = this.companyService.getCompanyById(id);
        model.addAttribute("company", company);

        //ID родительской компании по отношению к текущей компании (для поднятия на уровень выше во вложениях списков компаний)
        model.addAttribute("ID", id != 0 ? this.companyService.getCompanyById(id).getParrentId() : 0);
        //Т.к. в Entity мы храним только ID родителя,
        // то для отображения полной информации нам нужно загрузить из базы имя родительской компании
        model.addAttribute("Parrent", company.getParrentId() == 0 ? "----" : this.companyService.getCompanyById(company.getParrentId()).getCompanyName());

        CompanyDataStructure companyDataStructure = this.companyService.getTableOfChildCompanies(id, "", id, -1);

        //Дерево компании
        model.addAttribute("tree", companyDataStructure.getValue());
        model.addAttribute("counrChilds", companyDataStructure.getCount());

        return "companydata";
    }
}
