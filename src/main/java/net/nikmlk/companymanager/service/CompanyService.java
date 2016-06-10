package net.nikmlk.companymanager.service;

import net.nikmlk.companymanager.model.Company;
import net.nikmlk.companymanager.util.Pair;

import java.util.List;

public interface CompanyService {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(int id);

    Company getCompanyById(int id);

    List<Company> listCompaniesByParrentId(int parrentId);

    Pair<Integer, String> getTableOfChildCompanies(int id, String separator);
}
