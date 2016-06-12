package net.nikmlk.companymanager.model;

public class CompanyProxy {
    private int id;
    private String companyName;
    private int earning;
    private int parrentId;
    private int superParrentId;

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

    public int getSuperParrentId() {
        return superParrentId;
    }

    public void setSuperParrentId(int superParrentId) {
        this.superParrentId = superParrentId;
    }
}
