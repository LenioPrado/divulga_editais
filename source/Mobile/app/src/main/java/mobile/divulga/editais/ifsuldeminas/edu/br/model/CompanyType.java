package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.List;

public class CompanyType implements Serializable {

    private static final long serialVersionUID = 1L;

    private int companyTypeId;

    private String acronyms;

    private String description;

    private List<Notice> notices;

    public CompanyType() {
    }

    public CompanyType(String acronyms, String description) {
        this.acronyms = acronyms;
        this.description = description;
    }

    public int getCompanyTypeId() {
        return this.companyTypeId;
    }

    public void setCompanyTypeId(int companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public String getAcronyms() {
        return this.acronyms;
    }

    public void setAcronyms(String acronyms) {
        this.acronyms = acronyms;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Notice> getNotices() {
        return this.notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public Notice addNotice(Notice notice) {
        getNotices().add(notice);
        notice.setCompanyType(this);

        return notice;
    }

    public Notice removeNotice(Notice notice) {
        getNotices().remove(notice);
        notice.setCompanyType(null);

        return notice;
    }

    @Override
    public String toString() {
        return description ;
    }
}