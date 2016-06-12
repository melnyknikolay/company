package net.nikmlk.companymanager.service;

import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.util.CompanyDataStructure;

import java.util.List;

public interface CompanyService {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(int id);

    Company getCompanyById(int id);

    List<Company> listCompaniesByParrentId(int parrentId);

    CompanyDataStructure<Integer, String, Integer> getTableOfChildCompanies(int id, String separator, final int superParrent, int countChildCompanies);
}
