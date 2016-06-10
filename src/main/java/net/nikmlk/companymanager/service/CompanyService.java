package net.nikmlk.companymanager.service;

import javafx.util.Pair;
import net.nikmlk.companymanager.model.Company;

import java.util.List;

public interface CompanyService {
    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void removeCompany(int id);

    public Company getCompanyById(int id);

    public List<Company> listCompanies();

    public List<Company> listCompaniesByParrentId(int parrentId);

    Pair<Integer,String> getTableOfChildCompanies(int id, String separator);
}
