package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.io.Serializable;

public class NoticesCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private int noticeCategoryId;

    private Category category;

    private Notice notice;

    public int getNoticeCategoryId() {
        return this.noticeCategoryId;
    }

    public void setNoticeCategoryId(int noticeCategoryId) {
        this.noticeCategoryId = noticeCategoryId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Notice getNotice() {
        return this.notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
