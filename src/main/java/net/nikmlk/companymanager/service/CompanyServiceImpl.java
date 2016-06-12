package net.nikmlk.companymanager.service;

import net.nikmlk.companymanager.dao.CompanyDao;
import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.util.Pair;
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
    public Pair<Integer, String> getTableOfChildCompanies(int id, String separator, final int superParrent) {
        Company company = this.getCompanyById(id);
        String dataConcat = "<tr><td>" + separator + "<a href=\"/companies/" + company.getId() + "\">" + company.getCompanyName() + "</a>" + " | " + company.getEarning() + "K$";
        List<Company> listChildCompanies = this.CompanyDao.listCompaniesByParrentId(id);
        int sumEarning = 0;
        if (listChildCompanies.isEmpty()){
            return new Pair<Integer, String>(company.getEarning(), dataConcat + "</td>" + "<td><a href=\"/addcompany/" + company.getId() + "/" + superParrent + "\">" + "Add child" + "</a></td>" + "</tr>");
        }
        String tableConstructor = "";
        for (Company comp: listChildCompanies){
            Pair<Integer, String> pairChildListData = getTableOfChildCompanies(comp.getId(), separator + "---", superParrent);
            sumEarning += pairChildListData.getKey();
            tableConstructor += pairChildListData.getValue();
        }
        dataConcat += " | " + (sumEarning + company.getEarning()) + "K$" + "</td>" + "<td><a href=\"/addcompany/" + company.getId() + "/" + superParrent + "\">" + "Add child" + "</a></td>" + "</tr>";
        dataConcat += tableConstructor;
        return new Pair<Integer, String>((sumEarning + company.getEarning()), dataConcat);
    }


}
