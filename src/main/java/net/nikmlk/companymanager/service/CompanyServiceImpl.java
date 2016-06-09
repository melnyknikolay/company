package net.nikmlk.companymanager.service;

import net.nikmlk.companymanager.dao.CompanyDao;
import net.nikmlk.companymanager.model.Company;
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
    public List<Company> listCompanies() {
        return this.CompanyDao.listCompanies();
    }

    @Override
    @Transactional
    public List<Company> listCompaniesByParrentId(int parrentId) {
        return this.CompanyDao.listCompaniesByParrentId(parrentId);
    }


}
