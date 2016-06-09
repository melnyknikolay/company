package net.nikmlk.companymanager.dao;

import net.nikmlk.companymanager.model.Company;

import java.util.List;

public interface CompanyDao {
    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void removeCompany(int id);

    public Company getCompanyById(int id);

    public List<Company> listCompanies();

    public List<Company> listCompaniesByParrentId(int parrentId);
}
