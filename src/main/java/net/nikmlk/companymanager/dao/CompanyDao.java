package net.nikmlk.companymanager.dao;

import net.nikmlk.companymanager.model.Company;

import java.util.List;

public interface CompanyDao {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(int id);

    Company getCompanyById(int id);

    List<Company> listCompaniesByParrentId(int parrentId);
}
