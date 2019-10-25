package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private int noticeId;

    private Date closingDate;

    private String fileName;

    private String number;

    private String object;

    private Date publishingDate;

    private String status;

    private Date tradingDate;

    private CompanyType companyType;

    private Modality modality;

    private User user;

    private List<NoticesCategory> noticesCategories;

    private List<UsersNotice> usersNotices;

    public Notice() {
    }

    public int getNoticeId() {
        return this.noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public Date getClosingDate() {
        return this.closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Date getPublishingDate() {
        return this.publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTradingDate() {
        return this.tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Modality getModality() {
        return this.modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NoticesCategory> getNoticesCategories() {
        return this.noticesCategories;
    }

    public void setNoticesCategories(List<NoticesCategory> noticesCategories) {
        this.noticesCategories = noticesCategories;
    }

    public NoticesCategory addNoticesCategory(NoticesCategory noticesCategory) {
        getNoticesCategories().add(noticesCategory);
        noticesCategory.setNotice(this);

        return noticesCategory;
    }

    public NoticesCategory removeNoticesCategory(NoticesCategory noticesCategory) {
        getNoticesCategories().remove(noticesCategory);
        noticesCategory.setNotice(null);

        return noticesCategory;
    }

    public List<UsersNotice> getUsersNotices() {
        return this.usersNotices;
    }

    public void setUsersNotices(List<UsersNotice> usersNotices) {
        this.usersNotices = usersNotices;
    }

    public UsersNotice addUsersNotice(UsersNotice usersNotice) {
        getUsersNotices().add(usersNotice);
        usersNotice.setNotice(this);

        return usersNotice;
    }

    public UsersNotice removeUsersNotice(UsersNotice usersNotice) {
        getUsersNotices().remove(usersNotice);
        usersNotice.setNotice(null);

        return usersNotice;
    }
}