package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private int categoryId;

    private String description;

    private List<NoticesCategory> noticesCategories;

    private List<UsersCategory> usersCategories;

    public Category() {
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NoticesCategory> getNoticesCategories() {
        return this.noticesCategories;
    }

    public void setNoticesCategories(List<NoticesCategory> noticesCategories) {
        this.noticesCategories = noticesCategories;
    }

    public NoticesCategory addNoticesCategory(NoticesCategory noticesCategory) {
        getNoticesCategories().add(noticesCategory);
        noticesCategory.setCategory(this);

        return noticesCategory;
    }

    public NoticesCategory removeNoticesCategory(NoticesCategory noticesCategory) {
        getNoticesCategories().remove(noticesCategory);
        noticesCategory.setCategory(null);

        return noticesCategory;
    }

    public List<UsersCategory> getUsersCategories() {
        return this.usersCategories;
    }

    public void setUsersCategories(List<UsersCategory> usersCategories) {
        this.usersCategories = usersCategories;
    }

    public UsersCategory addUsersCategory(UsersCategory usersCategory) {
        getUsersCategories().add(usersCategory);
        usersCategory.setCategory(this);

        return usersCategory;
    }

    public UsersCategory removeUsersCategory(UsersCategory usersCategory) {
        getUsersCategories().remove(usersCategory);
        usersCategory.setCategory(null);

        return usersCategory;
    }
}