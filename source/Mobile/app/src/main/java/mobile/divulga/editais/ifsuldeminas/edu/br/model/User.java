package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;

    private String address;

    private String branch;

    private String city;

    private String cnae;

    private String cnpj;

    private String companyType;

    private String complement;

    private String email;

    private String fantasyName;

    private String neighborhood;

    private String number;

    private String password;

    private String phonePrimary;

    private String phoneSecondary;

    private String responsibleCpf;

    private String responsibleName;

    private String socialName;

    private String state;

    private String type;

    private String zipCode;

    private String token;

    private List<Notice> notices;

    private List<UsersCategory> usersCategories;

    private List<UsersNotice> usersNotices;

    private List<UsersRole> usersRoles;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnae() {
        return this.cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFantasyName() {
        return this.fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonePrimary() {
        return this.phonePrimary;
    }

    public void setPhonePrimary(String phonePrimary) {
        this.phonePrimary = phonePrimary;
    }

    public String getPhoneSecondary() {
        return this.phoneSecondary;
    }

    public void setPhoneSecondary(String phoneSecondary) {
        this.phoneSecondary = phoneSecondary;
    }

    public String getResponsibleCpf() {
        return this.responsibleCpf;
    }

    public void setResponsibleCpf(String responsibleCpf) {
        this.responsibleCpf = responsibleCpf;
    }

    public String getResponsibleName() {
        return this.responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getSocialName() {
        return this.socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Notice> getNotices() {
        return this.notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public Notice addNotice(Notice notice) {
        getNotices().add(notice);
        notice.setUser(this);

        return notice;
    }

    public Notice removeNotice(Notice notice) {
        getNotices().remove(notice);
        notice.setUser(null);

        return notice;
    }

    public List<UsersCategory> getUsersCategories() {
        return this.usersCategories;
    }

    public void setUsersCategories(List<UsersCategory> usersCategories) {
        this.usersCategories = usersCategories;
    }

    public UsersCategory addUsersCategory(UsersCategory usersCategory) {
        getUsersCategories().add(usersCategory);
        usersCategory.setUser(this);

        return usersCategory;
    }

    public UsersCategory removeUsersCategory(UsersCategory usersCategory) {
        getUsersCategories().remove(usersCategory);
        usersCategory.setUser(null);

        return usersCategory;
    }

    public List<UsersNotice> getUsersNotices() {
        return this.usersNotices;
    }

    public void setUsersNotices(List<UsersNotice> usersNotices) {
        this.usersNotices = usersNotices;
    }

    public UsersNotice addUsersNotice(UsersNotice usersNotice) {
        getUsersNotices().add(usersNotice);
        usersNotice.setUser(this);

        return usersNotice;
    }

    public UsersNotice removeUsersNotice(UsersNotice usersNotice) {
        getUsersNotices().remove(usersNotice);
        usersNotice.setUser(null);

        return usersNotice;
    }

    public List<UsersRole> getUsersRoles() {
        return this.usersRoles;
    }

    public void setUsersRoles(List<UsersRole> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public UsersRole addUsersRole(UsersRole usersRole) {
        getUsersRoles().add(usersRole);
        usersRole.setUser(this);

        return usersRole;
    }

    public UsersRole removeUsersRole(UsersRole usersRole) {
        getUsersRoles().remove(usersRole);
        usersRole.setUser(null);

        return usersRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", branch='" + branch + '\'' +
                ", city='" + city + '\'' +
                ", cnae='" + cnae + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", companyType='" + companyType + '\'' +
                ", complement='" + complement + '\'' +
                ", email='" + email + '\'' +
                ", fantasyName='" + fantasyName + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", phonePrimary='" + phonePrimary + '\'' +
                ", phoneSecondary='" + phoneSecondary + '\'' +
                ", responsibleCpf='" + responsibleCpf + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                ", socialName='" + socialName + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", notices=" + notices +
                ", usersCategories=" + usersCategories +
                ", usersNotices=" + usersNotices +
                ", usersRoles=" + usersRoles +
                '}';
    }
}