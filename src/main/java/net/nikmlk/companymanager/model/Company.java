package net.nikmlk.companymanager.model;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "EARNINGS")
    private int earning;

    @Column(name = "PARRENT_ID")
    private int parrentId;

    public Company() {
    }

    public Company(CompanyProxy companyProxy) {
        this.id = companyProxy.getId();
        this.companyName = companyProxy.getCompanyName();
        this.earning = companyProxy.getEarning();
        this.parrentId = companyProxy.getParrentId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEarning() {
        return earning;
    }

    public void setEarning(int earning) {
        this.earning = earning;
    }

    public int getParrentId() {
        return parrentId;
    }

    public void setParrentId(int parrentId) {
        this.parrentId = parrentId;
    }
}
