package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;

public class UsersCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userCategoryId;

    private Category category;

    private User user;

    public UsersCategory() {
    }

    public int getUserCategoryId() {
        return this.userCategoryId;
    }

    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}