package net.nikmlk.companymanager.controller;

import javafx.util.Pair;
import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CompanyController {
    private CompanyService companyService;

    @Autowired(required = true)
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "companies", method = RequestMethod.GET)
    public String listCompanies(Model model){
        model.addAttribute("company", new Company());
        model.addAttribute("listCompanies", this.companyService.listCompanies());

        return "companies";
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
        String parrent;
        if (company.getParrentId() == 0){
            parrent = "NULL";
        }else {
            parrent = this.companyService.getCompanyById(company.getParrentId()).getCompanyName();
        }
        model.addAttribute("Parrent", parrent);

        //Дерево компаний я вывожу до второго уровня вложенности,
        // в таком виде, как требуется в задании
        String tree = this.companyService.getTableOfChildCompanies(company.getId(), "--").getValue();
        /*List<Company> companies = this.companyService.listCompaniesByParrentId(id);
        if (!companies.isEmpty()){
            int summEarning = company.getEarning();
            String innerTree = "";
            for (Company cmp: companies){
                summEarning += cmp.getEarning();
                innerTree +=("---" + cmp.getCompanyName() + " | " + cmp.getEarning() + "K$" + " | ");
                List<Company> innnerList = this.companyService.listCompaniesByParrentId(cmp.getId());
                if (!innnerList.isEmpty()){
                    int summEarningOfInnerList = cmp.getEarning();
                    String innerTree2 = "";
                    for (Company comp: innnerList){
                        summEarningOfInnerList += comp.getEarning();
                        innerTree2 += "-----" + comp.getCompanyName() + " | " + comp.getEarning() + "K$" + "<br/>";
                    }
                    innerTree += summEarningOfInnerList + "K$" + "<br/>";
                    innerTree += innerTree2;
                }else innerTree += "<br/>";
            }
            tree += summEarning + "K$" + "<br/>" + innerTree;
        }else tree +=  "<br/>";*/

        model.addAttribute("tree", tree);

        return "companydata";
    }

    @RequestMapping("companies/{id}")
    public String listCompaniesByParrentId(@PathVariable("id") int id, Model model) {
        Company company = new Company();
        company.setParrentId(id);
        model.addAttribute("company", company);

        //Название родительской компании в оглавлении списка
        if (id != 0){
            model.addAttribute("parrentName", this.companyService.getCompanyById(id).getCompanyName());
        }else{
            model.addAttribute("parrentName", "Main Companies");
        }


        model.addAttribute("listCompanies", this.companyService.listCompaniesByParrentId(id));

        return "companies";
    }
}
