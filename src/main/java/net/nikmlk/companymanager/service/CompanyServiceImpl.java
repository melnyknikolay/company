package net.nikmlk.companymanager.service;

import net.nikmlk.companymanager.dao.CompanyDao;
import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.util.CompanyDataStructure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyDao CompanyDao;

    public void setCompanyDao(CompanyDao CompanyDao) {
        this.CompanyDao = CompanyDao;
    }

    @Override
    @Transactional
    public void addCompany(Company company) {
        this.CompanyDao.addCompany(company);
    }

    @Override
    @Transactional
    public void updateCompany(Company company) {
        this.CompanyDao.updateCompany(company);
    }

    @Override
    @Transactional
    public void removeCompany(int id) {

        //Если удалить родительскую компанию,
        // то надо обнулить параметр "parrentId" у дочерних компаний
        List<Company> companies = this.listCompaniesByParrentId(id);
        if (!companies.isEmpty()){
            for (Company company: companies){
                company.setParrentId(0);
                this.updateCompany(company);
            }
        }

        this.CompanyDao.removeCompany(id);
    }

    @Override
    @Transactional
    public Company getCompanyById(int id) {
        return this.CompanyDao.getCompanyById(id);
    }

    @Override
    @Transactional
    public List<Company> listCompaniesByParrentId(int parrentId) {
        return this.CompanyDao.listCompaniesByParrentId(parrentId);
    }

    //переменная id - компания, для которой функция вернет дерево дочерних компаний
    //superParrent - id компании, верхушка дерева, не изменяется при рекурсии
    @Override
    @Transactional
    public CompanyDataStructure<Integer, String, Integer> getTableOfChildCompanies(int id, String separator, final int superParrent, int countChildCompanies) {
        int countChilds = ++countChildCompanies;
        Company company = this.getCompanyById(id);
        final String AddChild = "<td><a href=\"/addcompany/" + superParrent + "/" + company.getId() + "\">" + "Add child" + "</a></td>";
        final String Edit = "<td><a href=\"/editfromtree/" + superParrent + "/" + company.getId() + "\">" + "Edit" + "</a></td>";
        final String Delete = "<td><a href=\"/removefromtree/" + superParrent + "/" + company.getId() + "\">" + "Delete" + "</a></td>";
        final String toList = "<td><a href=\"/companies/" + company.getId() + "\">" + "toList" + "</a></td>";
        String dataConcat = "<tr><td>" + separator + "<a href=\"/companydata/" + company.getId() + "\">" + company.getCompanyName() + "</a>" + " | " + company.getEarning() + "K$";
        List<Company> listChildCompanies = this.CompanyDao.listCompaniesByParrentId(id);
        int sumEarning = 0;
        if (listChildCompanies.isEmpty()){
            return new CompanyDataStructure<Integer, String, Integer>(company.getEarning(), dataConcat + "</td>" + AddChild + Edit + Delete + toList + "</tr>", countChilds);
        }
        String tableConstructor = "";
        for (Company comp: listChildCompanies){
            CompanyDataStructure<Integer, String, Integer> companyDataStruktureChildListData = getTableOfChildCompanies(comp.getId(), separator + "---", superParrent, countChilds);
            sumEarning += companyDataStruktureChildListData.getKey();
            tableConstructor += companyDataStruktureChildListData.getValue();
            countChilds = companyDataStruktureChildListData.getCount();
        }
        dataConcat += " | " + (sumEarning + company.getEarning()) + "K$" + "</td>" + AddChild + Edit + Delete + toList + "</tr>";
        dataConcat += tableConstructor;
        return new CompanyDataStructure<Integer, String, Integer>((sumEarning + company.getEarning()), dataConcat, countChilds);
    }


}
