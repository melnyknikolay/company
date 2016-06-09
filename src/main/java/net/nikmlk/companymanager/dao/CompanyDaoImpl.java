package net.nikmlk.companymanager.dao;

import net.nikmlk.companymanager.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCompany(Company company) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(company);
    }

    @Override
    public void updateCompany(Company company) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(company);
    }

    @Override
    public void removeCompany(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, new Integer(id));

        if(company !=null){
            session.delete(company);
        }
    }

    @Override
    public Company getCompanyById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, new Integer(id));

        return company;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> listCompanies() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Company> companyList = session.createQuery("from Company").list();

        return companyList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> listCompaniesByParrentId(int parrentId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Company> companyList = session.createQuery("from Company c where c.parrentId = :parrentId").setParameter("parrentId", parrentId).list();

        return companyList;
    }
}
